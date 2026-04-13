#!/usr/bin/env python3
"""
Refine a punch-hole frame asset into:
1. a cleaned frame PNG with reduced white halo on bezel edges
2. a bitmap screen mask PNG inferred from the true transparent screen opening

This is a development-time preprocessor for fixed-template composition. It does
not perform any runtime recognition inside the Android app.
"""

from __future__ import annotations

import argparse
from collections import deque
from pathlib import Path
from typing import Iterable

from PIL import Image, ImageFilter


def parse_args() -> argparse.Namespace:
    parser = argparse.ArgumentParser(description="Refine a punch-hole frame asset")
    parser.add_argument("--input", required=True, help="Source frame PNG path")
    parser.add_argument("--frame-output", required=True, help="Output refined frame PNG path")
    parser.add_argument("--mask-output", required=True, help="Output screen mask PNG path")
    parser.add_argument("--shrink", type=int, default=1, help="Mask inward shrink in pixels")
    parser.add_argument(
        "--center-x",
        type=int,
        default=None,
        help="Optional flood-fill seed X, defaults to image center",
    )
    parser.add_argument(
        "--center-y",
        type=int,
        default=None,
        help="Optional flood-fill seed Y, defaults to image center",
    )
    return parser.parse_args()


def main() -> None:
    args = parse_args()

    source_path = Path(args.input)
    frame_output_path = Path(args.frame_output)
    mask_output_path = Path(args.mask_output)

    frame_output_path.parent.mkdir(parents=True, exist_ok=True)
    mask_output_path.parent.mkdir(parents=True, exist_ok=True)

    source_image = Image.open(source_path).convert("RGBA")
    raw_screen_mask = infer_screen_mask(
        source_image,
        seed_x=args.center_x,
        seed_y=args.center_y,
    )
    screen_mask = shrink_mask(raw_screen_mask, iterations=max(0, args.shrink))
    refined_frame = clean_frame_halo(source_image, raw_screen_mask)

    refined_frame.save(frame_output_path)
    screen_mask.convert("RGBA").save(mask_output_path)

    bbox = screen_mask.getbbox()
    print(f"refined_frame={frame_output_path}")
    print(f"screen_mask={mask_output_path}")
    print(f"mask_bbox={bbox}")


def infer_screen_mask(image: Image.Image, seed_x: int | None, seed_y: int | None) -> Image.Image:
    width, height = image.size
    alpha = image.getchannel("A")
    alpha_bytes = alpha.tobytes()

    start = find_seed(alpha_bytes, width, height, seed_x, seed_y)
    visited = bytearray(width * height)
    queue = deque([start])
    visited[start[1] * width + start[0]] = 1

    while queue:
        x, y = queue.popleft()
        for nx, ny in neighbors4(x, y, width, height):
            offset = ny * width + nx
            if visited[offset]:
                continue
            if alpha_bytes[offset] > 10:
                continue
            visited[offset] = 1
            queue.append((nx, ny))

    mask = Image.new("L", (width, height), 0)
    mask.putdata([255 if value else 0 for value in visited])
    return mask


def shrink_mask(mask: Image.Image, iterations: int) -> Image.Image:
    current = mask
    for _ in range(iterations):
        current = current.filter(ImageFilter.MinFilter(3))
    return current


def clean_frame_halo(source_image: Image.Image, raw_screen_mask: Image.Image) -> Image.Image:
    width, height = source_image.size
    cleaned = source_image.copy()
    pixels = cleaned.load()
    alpha = cleaned.getchannel("A")
    alpha_bytes = alpha.tobytes()
    screen_bytes = raw_screen_mask.tobytes()

    edge_flags = bytearray(width * height)

    for y in range(height):
        for x in range(width):
            offset = y * width + x
            if alpha_bytes[offset] == 0:
                continue
            if touches_screen_or_outside(x, y, width, height, screen_bytes, alpha_bytes):
                edge_flags[offset] = 1

    for y in range(height):
        for x in range(width):
            offset = y * width + x
            if not edge_flags[offset]:
                continue

            r, g, b, a = pixels[x, y]
            luminance = rgb_luminance(r, g, b)
            saturation = max(r, g, b) - min(r, g, b)

            if luminance < 170 or saturation > 42:
                continue

            replacement = sample_neighbor_color(
                x=x,
                y=y,
                width=width,
                height=height,
                pixels=pixels,
                alpha_bytes=alpha_bytes,
                edge_flags=edge_flags,
            )
            if replacement is None:
                continue

            nr, ng, nb = replacement
            pixels[x, y] = (
                blend_channel(nr, r, foreground_ratio=0.16),
                blend_channel(ng, g, foreground_ratio=0.16),
                blend_channel(nb, b, foreground_ratio=0.16),
                a,
            )

    return cleaned


def find_seed(alpha_bytes: bytes, width: int, height: int, seed_x: int | None, seed_y: int | None) -> tuple[int, int]:
    candidates = []
    if seed_x is not None and seed_y is not None:
        candidates.append((seed_x, seed_y))
    candidates.append((width // 2, height // 2))

    for cx, cy in candidates:
        if 0 <= cx < width and 0 <= cy < height and alpha_bytes[cy * width + cx] <= 10:
            return cx, cy

    for radius in range(1, max(width, height)):
        min_x = max(0, width // 2 - radius)
        max_x = min(width - 1, width // 2 + radius)
        min_y = max(0, height // 2 - radius)
        max_y = min(height - 1, height // 2 + radius)
        perimeter: list[tuple[int, int]] = []
        for x in range(min_x, max_x + 1):
            perimeter.append((x, min_y))
            perimeter.append((x, max_y))
        for y in range(min_y + 1, max_y):
            perimeter.append((min_x, y))
            perimeter.append((max_x, y))
        for px, py in perimeter:
            if alpha_bytes[py * width + px] <= 10:
                return px, py

    raise RuntimeError("Unable to find a transparent seed inside the frame screen opening")


def touches_screen_or_outside(
    x: int,
    y: int,
    width: int,
    height: int,
    screen_bytes: bytes,
    alpha_bytes: bytes,
) -> bool:
    for nx, ny in neighbors8(x, y, width, height):
        neighbor_offset = ny * width + nx
        if screen_bytes[neighbor_offset] > 0:
            return True
        if alpha_bytes[neighbor_offset] == 0 and screen_bytes[neighbor_offset] == 0:
            return True
    return False


def sample_neighbor_color(
    x: int,
    y: int,
    width: int,
    height: int,
    pixels,
    alpha_bytes: bytes,
    edge_flags: bytes,
) -> tuple[int, int, int] | None:
    candidates: list[tuple[int, int, int]] = []

    for radius in range(1, 4):
        for ny in range(max(0, y - radius), min(height, y + radius + 1)):
            for nx in range(max(0, x - radius), min(width, x + radius + 1)):
                offset = ny * width + nx
                if alpha_bytes[offset] == 0:
                    continue
                if edge_flags[offset]:
                    continue
                r, g, b, _ = pixels[nx, ny]
                if rgb_luminance(r, g, b) > 215 and max(r, g, b) - min(r, g, b) <= 42:
                    continue
                candidates.append((r, g, b))
        if candidates:
            break

    if not candidates:
        return None

    candidates.sort(key=lambda rgb: rgb_luminance(*rgb))
    mid = candidates[len(candidates) // 2]
    return mid


def rgb_luminance(r: int, g: int, b: int) -> float:
    return (0.299 * r) + (0.587 * g) + (0.114 * b)


def blend_channel(background: int, foreground: int, foreground_ratio: float) -> int:
    value = (background * (1.0 - foreground_ratio)) + (foreground * foreground_ratio)
    return max(0, min(255, int(round(value))))


def neighbors4(x: int, y: int, width: int, height: int) -> Iterable[tuple[int, int]]:
    if x > 0:
        yield x - 1, y
    if x + 1 < width:
        yield x + 1, y
    if y > 0:
        yield x, y - 1
    if y + 1 < height:
        yield x, y + 1


def neighbors8(x: int, y: int, width: int, height: int) -> Iterable[tuple[int, int]]:
    for ny in range(max(0, y - 1), min(height, y + 2)):
        for nx in range(max(0, x - 1), min(width, x + 2)):
            if nx == x and ny == y:
                continue
            yield nx, ny


if __name__ == "__main__":
    main()
