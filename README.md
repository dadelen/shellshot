# ShellShot

ShellShot 是一个面向 Android 15/16 自用侧载场景的带壳截图 App。它通过前台服务监听 `MediaStore` 中的新图片，识别疑似系统截图后，自动把截图嵌入本地模板、保存到 `Pictures/ShellShot/`，并尽量删除原图。

## 主要能力

- 前台服务常驻监听截图
- 基于目录、文件名、时间、尺寸的截图识别
- `assets/templates/` 模板系统
- `Bitmap + Canvas` 带壳合成
- MediaStore 保存结果图
- 原图删除与失败兜底日志
- Compose UI 展示权限、模板、最近结果和日志

## 运行环境

- Android Studio 近期稳定版
- Android SDK `compileSdk = 36`
- 建议 JDK 17 或 Android Studio 自带 JDK

## 首次运行

1. 用 Android Studio 打开项目。
2. 等待 Gradle 同步完成。
3. 安装到 Android 15/16 真机。
4. 进入 App 后按页面提示授予以下能力：
   - 通知权限
   - 图片读取权限
   - 所有文件访问
   - 媒体管理权限
   - 忽略电池优化
5. 点击“启动监听服务”。

## 模板资源

- 模板索引：`app/src/main/assets/templates/template_list.json`
- 默认模板配置：`app/src/main/assets/templates/default_phone.json`
- 默认模板图片：
  - `app/src/main/assets/templates/default_phone/frame.png`
  - `app/src/main/assets/templates/default_phone/preview.png`

## 调试建议

- 先确认通知栏里已经出现前台服务通知。
- 截图后查看 App 的“日志”页，确认是否识别、合成、保存或删除失败。
- 如果删除失败，通常是 ROM 或权限限制；App 会保留结果图并记录失败原因。

