package androidx.datastore.preferences.protobuf;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.datastore.preferences.protobuf.ArrayDecoders;
import androidx.datastore.preferences.protobuf.ByteString;
import androidx.datastore.preferences.protobuf.Internal;
import androidx.datastore.preferences.protobuf.MapEntryLite;
import androidx.datastore.preferences.protobuf.WireFormat;
import androidx.datastore.preferences.protobuf.Writer;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;
import sun.misc.Unsafe;

@CheckReturnValue
/* loaded from: classes13.dex */
final class MessageSchema<T> implements Schema<T> {
    private static final int ENFORCE_UTF8_MASK = 536870912;
    private static final int FIELD_TYPE_MASK = 267386880;
    private static final int INTS_PER_FIELD = 3;
    private static final int NO_PRESENCE_SENTINEL = 1048575;
    private static final int OFFSET_BITS = 20;
    private static final int OFFSET_MASK = 1048575;
    static final int ONEOF_TYPE_OFFSET = 51;
    private static final int REQUIRED_MASK = 268435456;
    private final int[] buffer;
    private final int checkInitializedCount;
    private final MessageLite defaultInstance;
    private final ExtensionSchema<?> extensionSchema;
    private final boolean hasExtensions;
    private final int[] intArray;
    private final ListFieldSchema listFieldSchema;
    private final boolean lite;
    private final MapFieldSchema mapFieldSchema;
    private final int maxFieldNumber;
    private final int minFieldNumber;
    private final NewInstanceSchema newInstanceSchema;
    private final Object[] objects;
    private final boolean proto3;
    private final int repeatedFieldOffsetStart;
    private final UnknownFieldSchema<?, ?> unknownFieldSchema;
    private final boolean useCachedSizeField;
    private static final int[] EMPTY_INT_ARRAY = new int[0];
    private static final Unsafe UNSAFE = UnsafeUtil.getUnsafe();

    private MessageSchema(int[] buffer, Object[] objects, int minFieldNumber, int maxFieldNumber, MessageLite defaultInstance, boolean proto3, boolean useCachedSizeField, int[] intArray, int checkInitialized, int mapFieldPositions, NewInstanceSchema newInstanceSchema, ListFieldSchema listFieldSchema, UnknownFieldSchema<?, ?> unknownFieldSchema, ExtensionSchema<?> extensionSchema, MapFieldSchema mapFieldSchema) {
        this.buffer = buffer;
        this.objects = objects;
        this.minFieldNumber = minFieldNumber;
        this.maxFieldNumber = maxFieldNumber;
        this.lite = defaultInstance instanceof GeneratedMessageLite;
        this.proto3 = proto3;
        this.hasExtensions = extensionSchema != null && extensionSchema.hasExtensions(defaultInstance);
        this.useCachedSizeField = useCachedSizeField;
        this.intArray = intArray;
        this.checkInitializedCount = checkInitialized;
        this.repeatedFieldOffsetStart = mapFieldPositions;
        this.newInstanceSchema = newInstanceSchema;
        this.listFieldSchema = listFieldSchema;
        this.unknownFieldSchema = unknownFieldSchema;
        this.extensionSchema = extensionSchema;
        this.defaultInstance = defaultInstance;
        this.mapFieldSchema = mapFieldSchema;
    }

    static <T> MessageSchema<T> newSchema(Class<T> messageClass, MessageInfo messageInfo, NewInstanceSchema newInstanceSchema, ListFieldSchema listFieldSchema, UnknownFieldSchema<?, ?> unknownFieldSchema, ExtensionSchema<?> extensionSchema, MapFieldSchema mapFieldSchema) {
        if (messageInfo instanceof RawMessageInfo) {
            return newSchemaForRawMessageInfo((RawMessageInfo) messageInfo, newInstanceSchema, listFieldSchema, unknownFieldSchema, extensionSchema, mapFieldSchema);
        }
        return newSchemaForMessageInfo((StructuralMessageInfo) messageInfo, newInstanceSchema, listFieldSchema, unknownFieldSchema, extensionSchema, mapFieldSchema);
    }

    /* JADX WARN: Removed duplicated region for block: B:104:0x039d  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x03a0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static <T> androidx.datastore.preferences.protobuf.MessageSchema<T> newSchemaForRawMessageInfo(androidx.datastore.preferences.protobuf.RawMessageInfo r42, androidx.datastore.preferences.protobuf.NewInstanceSchema r43, androidx.datastore.preferences.protobuf.ListFieldSchema r44, androidx.datastore.preferences.protobuf.UnknownFieldSchema<?, ?> r45, androidx.datastore.preferences.protobuf.ExtensionSchema<?> r46, androidx.datastore.preferences.protobuf.MapFieldSchema r47) {
        /*
            Method dump skipped, instructions count: 1177
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.datastore.preferences.protobuf.MessageSchema.newSchemaForRawMessageInfo(androidx.datastore.preferences.protobuf.RawMessageInfo, androidx.datastore.preferences.protobuf.NewInstanceSchema, androidx.datastore.preferences.protobuf.ListFieldSchema, androidx.datastore.preferences.protobuf.UnknownFieldSchema, androidx.datastore.preferences.protobuf.ExtensionSchema, androidx.datastore.preferences.protobuf.MapFieldSchema):androidx.datastore.preferences.protobuf.MessageSchema");
    }

    private static java.lang.reflect.Field reflectField(Class<?> messageClass, String fieldName) {
        try {
            return messageClass.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            java.lang.reflect.Field[] fields = messageClass.getDeclaredFields();
            for (java.lang.reflect.Field field : fields) {
                if (fieldName.equals(field.getName())) {
                    return field;
                }
            }
            throw new RuntimeException("Field " + fieldName + " for " + messageClass.getName() + " not found. Known fields are " + Arrays.toString(fields));
        }
    }

    static <T> MessageSchema<T> newSchemaForMessageInfo(StructuralMessageInfo messageInfo, NewInstanceSchema newInstanceSchema, ListFieldSchema listFieldSchema, UnknownFieldSchema<?, ?> unknownFieldSchema, ExtensionSchema<?> extensionSchema, MapFieldSchema mapFieldSchema) {
        int minFieldNumber;
        int maxFieldNumber;
        int[] mapFieldPositions;
        int[] repeatedFieldOffsets;
        int[] repeatedFieldOffsets2;
        boolean isProto3 = messageInfo.getSyntax() == ProtoSyntax.PROTO3;
        FieldInfo[] fis = messageInfo.getFields();
        if (fis.length == 0) {
            minFieldNumber = 0;
            maxFieldNumber = 0;
        } else {
            int minFieldNumber2 = fis[0].getFieldNumber();
            minFieldNumber = minFieldNumber2;
            maxFieldNumber = fis[fis.length - 1].getFieldNumber();
        }
        int numEntries = fis.length;
        int[] buffer = new int[numEntries * 3];
        Object[] objects = new Object[numEntries * 2];
        int mapFieldCount = 0;
        int repeatedFieldCount = 0;
        for (FieldInfo fi : fis) {
            if (fi.getType() == FieldType.MAP) {
                mapFieldCount++;
            } else if (fi.getType().id() >= 18 && fi.getType().id() <= 49) {
                repeatedFieldCount++;
            }
        }
        int[] mapFieldPositions2 = mapFieldCount > 0 ? new int[mapFieldCount] : null;
        int[] repeatedFieldOffsets3 = repeatedFieldCount > 0 ? new int[repeatedFieldCount] : null;
        int mapFieldCount2 = 0;
        int[] checkInitialized = messageInfo.getCheckInitialized();
        if (checkInitialized == null) {
            checkInitialized = EMPTY_INT_ARRAY;
        }
        int repeatedFieldCount2 = 0;
        int checkInitializedIndex = 0;
        int checkInitializedIndex2 = 0;
        int fieldIndex = 0;
        while (checkInitializedIndex2 < fis.length) {
            FieldInfo fi2 = fis[checkInitializedIndex2];
            int fieldNumber = fi2.getFieldNumber();
            storeFieldData(fi2, buffer, fieldIndex, objects);
            FieldInfo[] fis2 = fis;
            if (checkInitializedIndex < checkInitialized.length && checkInitialized[checkInitializedIndex] == fieldNumber) {
                checkInitialized[checkInitializedIndex] = fieldIndex;
                checkInitializedIndex++;
            }
            int numEntries2 = numEntries;
            if (fi2.getType() == FieldType.MAP) {
                mapFieldPositions2[mapFieldCount2] = fieldIndex;
                mapFieldCount2++;
                repeatedFieldOffsets2 = repeatedFieldOffsets3;
            } else if (fi2.getType().id() < 18 || fi2.getType().id() > 49) {
                repeatedFieldOffsets2 = repeatedFieldOffsets3;
            } else {
                repeatedFieldOffsets2 = repeatedFieldOffsets3;
                repeatedFieldOffsets2[repeatedFieldCount2] = (int) UnsafeUtil.objectFieldOffset(fi2.getField());
                repeatedFieldCount2++;
            }
            checkInitializedIndex2++;
            fieldIndex += 3;
            repeatedFieldOffsets3 = repeatedFieldOffsets2;
            fis = fis2;
            numEntries = numEntries2;
        }
        int[] repeatedFieldOffsets4 = repeatedFieldOffsets3;
        if (mapFieldPositions2 != null) {
            mapFieldPositions = mapFieldPositions2;
        } else {
            mapFieldPositions = EMPTY_INT_ARRAY;
        }
        if (repeatedFieldOffsets4 != null) {
            repeatedFieldOffsets = repeatedFieldOffsets4;
        } else {
            repeatedFieldOffsets = EMPTY_INT_ARRAY;
        }
        int[] combined = new int[checkInitialized.length + mapFieldPositions.length + repeatedFieldOffsets.length];
        System.arraycopy(checkInitialized, 0, combined, 0, checkInitialized.length);
        System.arraycopy(mapFieldPositions, 0, combined, checkInitialized.length, mapFieldPositions.length);
        System.arraycopy(repeatedFieldOffsets, 0, combined, checkInitialized.length + mapFieldPositions.length, repeatedFieldOffsets.length);
        MessageLite defaultInstance = messageInfo.getDefaultInstance();
        int length = checkInitialized.length;
        int length2 = checkInitialized.length;
        int checkInitializedIndex3 = mapFieldPositions.length;
        int fieldIndex2 = length2 + checkInitializedIndex3;
        return new MessageSchema<>(buffer, objects, minFieldNumber, maxFieldNumber, defaultInstance, isProto3, true, combined, length, fieldIndex2, newInstanceSchema, listFieldSchema, unknownFieldSchema, extensionSchema, mapFieldSchema);
    }

    private static void storeFieldData(FieldInfo fi, int[] buffer, int bufferIndex, Object[] objects) {
        int fieldOffset;
        int typeId;
        int typeId2;
        int presenceFieldOffset;
        int presenceFieldOffset2;
        OneofInfo oneof = fi.getOneof();
        if (oneof != null) {
            typeId = fi.getType().id() + 51;
            fieldOffset = (int) UnsafeUtil.objectFieldOffset(oneof.getValueField());
            typeId2 = (int) UnsafeUtil.objectFieldOffset(oneof.getCaseField());
            presenceFieldOffset = 0;
        } else {
            FieldType type = fi.getType();
            fieldOffset = (int) UnsafeUtil.objectFieldOffset(fi.getField());
            int typeId3 = type.id();
            if (!type.isList() && !type.isMap()) {
                java.lang.reflect.Field presenceField = fi.getPresenceField();
                if (presenceField == null) {
                    presenceFieldOffset2 = 1048575;
                } else {
                    presenceFieldOffset2 = (int) UnsafeUtil.objectFieldOffset(presenceField);
                }
                presenceFieldOffset = Integer.numberOfTrailingZeros(fi.getPresenceMask());
                typeId = typeId3;
                typeId2 = presenceFieldOffset2;
            } else if (fi.getCachedSizeField() == null) {
                typeId = typeId3;
                typeId2 = 0;
                presenceFieldOffset = 0;
            } else {
                int presenceFieldOffset3 = (int) UnsafeUtil.objectFieldOffset(fi.getCachedSizeField());
                typeId = typeId3;
                typeId2 = presenceFieldOffset3;
                presenceFieldOffset = 0;
            }
        }
        buffer[bufferIndex] = fi.getFieldNumber();
        buffer[bufferIndex + 1] = (fi.isEnforceUtf8() ? ENFORCE_UTF8_MASK : 0) | (fi.isRequired() ? REQUIRED_MASK : 0) | (typeId << 20) | fieldOffset;
        buffer[bufferIndex + 2] = (presenceFieldOffset << 20) | typeId2;
        Object messageFieldClass = fi.getMessageFieldClass();
        if (fi.getMapDefaultEntry() != null) {
            objects[(bufferIndex / 3) * 2] = fi.getMapDefaultEntry();
            if (messageFieldClass != null) {
                objects[((bufferIndex / 3) * 2) + 1] = messageFieldClass;
                return;
            } else {
                if (fi.getEnumVerifier() != null) {
                    objects[((bufferIndex / 3) * 2) + 1] = fi.getEnumVerifier();
                    return;
                }
                return;
            }
        }
        if (messageFieldClass != null) {
            objects[((bufferIndex / 3) * 2) + 1] = messageFieldClass;
        } else if (fi.getEnumVerifier() != null) {
            objects[((bufferIndex / 3) * 2) + 1] = fi.getEnumVerifier();
        }
    }

    @Override // androidx.datastore.preferences.protobuf.Schema
    public T newInstance() {
        return (T) this.newInstanceSchema.newInstance(this.defaultInstance);
    }

    @Override // androidx.datastore.preferences.protobuf.Schema
    public boolean equals(T message, T other) {
        int bufferLength = this.buffer.length;
        for (int pos = 0; pos < bufferLength; pos += 3) {
            if (!equals(message, other, pos)) {
                return false;
            }
        }
        Object messageUnknown = this.unknownFieldSchema.getFromMessage(message);
        Object otherUnknown = this.unknownFieldSchema.getFromMessage(other);
        if (!messageUnknown.equals(otherUnknown)) {
            return false;
        }
        if (this.hasExtensions) {
            FieldSet<?> messageExtensions = this.extensionSchema.getExtensions(message);
            FieldSet<?> otherExtensions = this.extensionSchema.getExtensions(other);
            return messageExtensions.equals(otherExtensions);
        }
        return true;
    }

    private boolean equals(T message, T other, int pos) {
        int typeAndOffset = typeAndOffsetAt(pos);
        long offset = offset(typeAndOffset);
        switch (type(typeAndOffset)) {
            case 0:
                if (arePresentForEquals(message, other, pos) && Double.doubleToLongBits(UnsafeUtil.getDouble(message, offset)) == Double.doubleToLongBits(UnsafeUtil.getDouble(other, offset))) {
                    break;
                }
                break;
            case 1:
                if (arePresentForEquals(message, other, pos) && Float.floatToIntBits(UnsafeUtil.getFloat(message, offset)) == Float.floatToIntBits(UnsafeUtil.getFloat(other, offset))) {
                    break;
                }
                break;
            case 2:
                if (arePresentForEquals(message, other, pos) && UnsafeUtil.getLong(message, offset) == UnsafeUtil.getLong(other, offset)) {
                    break;
                }
                break;
            case 3:
                if (arePresentForEquals(message, other, pos) && UnsafeUtil.getLong(message, offset) == UnsafeUtil.getLong(other, offset)) {
                    break;
                }
                break;
            case 4:
                if (arePresentForEquals(message, other, pos) && UnsafeUtil.getInt(message, offset) == UnsafeUtil.getInt(other, offset)) {
                    break;
                }
                break;
            case 5:
                if (arePresentForEquals(message, other, pos) && UnsafeUtil.getLong(message, offset) == UnsafeUtil.getLong(other, offset)) {
                    break;
                }
                break;
            case 6:
                if (arePresentForEquals(message, other, pos) && UnsafeUtil.getInt(message, offset) == UnsafeUtil.getInt(other, offset)) {
                    break;
                }
                break;
            case 7:
                if (arePresentForEquals(message, other, pos) && UnsafeUtil.getBoolean(message, offset) == UnsafeUtil.getBoolean(other, offset)) {
                    break;
                }
                break;
            case 8:
                if (arePresentForEquals(message, other, pos) && SchemaUtil.safeEquals(UnsafeUtil.getObject(message, offset), UnsafeUtil.getObject(other, offset))) {
                    break;
                }
                break;
            case 9:
                if (arePresentForEquals(message, other, pos) && SchemaUtil.safeEquals(UnsafeUtil.getObject(message, offset), UnsafeUtil.getObject(other, offset))) {
                    break;
                }
                break;
            case 10:
                if (arePresentForEquals(message, other, pos) && SchemaUtil.safeEquals(UnsafeUtil.getObject(message, offset), UnsafeUtil.getObject(other, offset))) {
                    break;
                }
                break;
            case 11:
                if (arePresentForEquals(message, other, pos) && UnsafeUtil.getInt(message, offset) == UnsafeUtil.getInt(other, offset)) {
                    break;
                }
                break;
            case 12:
                if (arePresentForEquals(message, other, pos) && UnsafeUtil.getInt(message, offset) == UnsafeUtil.getInt(other, offset)) {
                    break;
                }
                break;
            case 13:
                if (arePresentForEquals(message, other, pos) && UnsafeUtil.getInt(message, offset) == UnsafeUtil.getInt(other, offset)) {
                    break;
                }
                break;
            case 14:
                if (arePresentForEquals(message, other, pos) && UnsafeUtil.getLong(message, offset) == UnsafeUtil.getLong(other, offset)) {
                    break;
                }
                break;
            case 15:
                if (arePresentForEquals(message, other, pos) && UnsafeUtil.getInt(message, offset) == UnsafeUtil.getInt(other, offset)) {
                    break;
                }
                break;
            case 16:
                if (arePresentForEquals(message, other, pos) && UnsafeUtil.getLong(message, offset) == UnsafeUtil.getLong(other, offset)) {
                    break;
                }
                break;
            case 17:
                if (arePresentForEquals(message, other, pos) && SchemaUtil.safeEquals(UnsafeUtil.getObject(message, offset), UnsafeUtil.getObject(other, offset))) {
                    break;
                }
                break;
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
            case 56:
            case 57:
            case 58:
            case 59:
            case LockFreeTaskQueueCore.FROZEN_SHIFT /* 60 */:
            case LockFreeTaskQueueCore.CLOSED_SHIFT /* 61 */:
            case 62:
            case 63:
            case 64:
            case 65:
            case 66:
            case 67:
            case 68:
                if (isOneofCaseEqual(message, other, pos) && SchemaUtil.safeEquals(UnsafeUtil.getObject(message, offset), UnsafeUtil.getObject(other, offset))) {
                    break;
                }
                break;
        }
        return true;
    }

    @Override // androidx.datastore.preferences.protobuf.Schema
    public int hashCode(T message) {
        int hashCode = 0;
        int bufferLength = this.buffer.length;
        for (int pos = 0; pos < bufferLength; pos += 3) {
            int typeAndOffset = typeAndOffsetAt(pos);
            int entryNumber = numberAt(pos);
            long offset = offset(typeAndOffset);
            switch (type(typeAndOffset)) {
                case 0:
                    hashCode = (hashCode * 53) + Internal.hashLong(Double.doubleToLongBits(UnsafeUtil.getDouble(message, offset)));
                    break;
                case 1:
                    hashCode = (hashCode * 53) + Float.floatToIntBits(UnsafeUtil.getFloat(message, offset));
                    break;
                case 2:
                    hashCode = (hashCode * 53) + Internal.hashLong(UnsafeUtil.getLong(message, offset));
                    break;
                case 3:
                    hashCode = (hashCode * 53) + Internal.hashLong(UnsafeUtil.getLong(message, offset));
                    break;
                case 4:
                    hashCode = (hashCode * 53) + UnsafeUtil.getInt(message, offset);
                    break;
                case 5:
                    hashCode = (hashCode * 53) + Internal.hashLong(UnsafeUtil.getLong(message, offset));
                    break;
                case 6:
                    hashCode = (hashCode * 53) + UnsafeUtil.getInt(message, offset);
                    break;
                case 7:
                    hashCode = (hashCode * 53) + Internal.hashBoolean(UnsafeUtil.getBoolean(message, offset));
                    break;
                case 8:
                    int protoHash = hashCode * 53;
                    int hashCode2 = protoHash + ((String) UnsafeUtil.getObject(message, offset)).hashCode();
                    hashCode = hashCode2;
                    break;
                case 9:
                    int protoHash2 = 37;
                    Object submessage = UnsafeUtil.getObject(message, offset);
                    if (submessage != null) {
                        protoHash2 = submessage.hashCode();
                    }
                    hashCode = (hashCode * 53) + protoHash2;
                    break;
                case 10:
                    hashCode = (hashCode * 53) + UnsafeUtil.getObject(message, offset).hashCode();
                    break;
                case 11:
                    hashCode = (hashCode * 53) + UnsafeUtil.getInt(message, offset);
                    break;
                case 12:
                    hashCode = (hashCode * 53) + UnsafeUtil.getInt(message, offset);
                    break;
                case 13:
                    hashCode = (hashCode * 53) + UnsafeUtil.getInt(message, offset);
                    break;
                case 14:
                    hashCode = (hashCode * 53) + Internal.hashLong(UnsafeUtil.getLong(message, offset));
                    break;
                case 15:
                    hashCode = (hashCode * 53) + UnsafeUtil.getInt(message, offset);
                    break;
                case 16:
                    int protoHash3 = hashCode * 53;
                    int hashCode3 = protoHash3 + Internal.hashLong(UnsafeUtil.getLong(message, offset));
                    hashCode = hashCode3;
                    break;
                case 17:
                    int protoHash4 = 37;
                    Object submessage2 = UnsafeUtil.getObject(message, offset);
                    if (submessage2 != null) {
                        protoHash4 = submessage2.hashCode();
                    }
                    hashCode = (hashCode * 53) + protoHash4;
                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_HORIZONTAL_BIAS /* 29 */:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_EDITOR_ABSOLUTEX /* 49 */:
                    hashCode = (hashCode * 53) + UnsafeUtil.getObject(message, offset).hashCode();
                    break;
                case 50:
                    hashCode = (hashCode * 53) + UnsafeUtil.getObject(message, offset).hashCode();
                    break;
                case 51:
                    if (isOneofPresent(message, entryNumber, pos)) {
                        hashCode = (hashCode * 53) + Internal.hashLong(Double.doubleToLongBits(oneofDoubleAt(message, offset)));
                        break;
                    } else {
                        break;
                    }
                case 52:
                    if (isOneofPresent(message, entryNumber, pos)) {
                        hashCode = (hashCode * 53) + Float.floatToIntBits(oneofFloatAt(message, offset));
                        break;
                    } else {
                        break;
                    }
                case 53:
                    if (isOneofPresent(message, entryNumber, pos)) {
                        hashCode = (hashCode * 53) + Internal.hashLong(oneofLongAt(message, offset));
                        break;
                    } else {
                        break;
                    }
                case 54:
                    if (isOneofPresent(message, entryNumber, pos)) {
                        hashCode = (hashCode * 53) + Internal.hashLong(oneofLongAt(message, offset));
                        break;
                    } else {
                        break;
                    }
                case 55:
                    if (isOneofPresent(message, entryNumber, pos)) {
                        hashCode = (hashCode * 53) + oneofIntAt(message, offset);
                        break;
                    } else {
                        break;
                    }
                case 56:
                    if (isOneofPresent(message, entryNumber, pos)) {
                        hashCode = (hashCode * 53) + Internal.hashLong(oneofLongAt(message, offset));
                        break;
                    } else {
                        break;
                    }
                case 57:
                    if (isOneofPresent(message, entryNumber, pos)) {
                        hashCode = (hashCode * 53) + oneofIntAt(message, offset);
                        break;
                    } else {
                        break;
                    }
                case 58:
                    if (isOneofPresent(message, entryNumber, pos)) {
                        hashCode = (hashCode * 53) + Internal.hashBoolean(oneofBooleanAt(message, offset));
                        break;
                    } else {
                        break;
                    }
                case 59:
                    if (isOneofPresent(message, entryNumber, pos)) {
                        hashCode = (hashCode * 53) + ((String) UnsafeUtil.getObject(message, offset)).hashCode();
                        break;
                    } else {
                        break;
                    }
                case LockFreeTaskQueueCore.FROZEN_SHIFT /* 60 */:
                    if (isOneofPresent(message, entryNumber, pos)) {
                        hashCode = (hashCode * 53) + UnsafeUtil.getObject(message, offset).hashCode();
                        break;
                    } else {
                        break;
                    }
                case LockFreeTaskQueueCore.CLOSED_SHIFT /* 61 */:
                    if (isOneofPresent(message, entryNumber, pos)) {
                        hashCode = (hashCode * 53) + UnsafeUtil.getObject(message, offset).hashCode();
                        break;
                    } else {
                        break;
                    }
                case 62:
                    if (isOneofPresent(message, entryNumber, pos)) {
                        hashCode = (hashCode * 53) + oneofIntAt(message, offset);
                        break;
                    } else {
                        break;
                    }
                case 63:
                    if (isOneofPresent(message, entryNumber, pos)) {
                        hashCode = (hashCode * 53) + oneofIntAt(message, offset);
                        break;
                    } else {
                        break;
                    }
                case 64:
                    if (isOneofPresent(message, entryNumber, pos)) {
                        hashCode = (hashCode * 53) + oneofIntAt(message, offset);
                        break;
                    } else {
                        break;
                    }
                case 65:
                    if (isOneofPresent(message, entryNumber, pos)) {
                        hashCode = (hashCode * 53) + Internal.hashLong(oneofLongAt(message, offset));
                        break;
                    } else {
                        break;
                    }
                case 66:
                    if (isOneofPresent(message, entryNumber, pos)) {
                        hashCode = (hashCode * 53) + oneofIntAt(message, offset);
                        break;
                    } else {
                        break;
                    }
                case 67:
                    if (isOneofPresent(message, entryNumber, pos)) {
                        hashCode = (hashCode * 53) + Internal.hashLong(oneofLongAt(message, offset));
                        break;
                    } else {
                        break;
                    }
                case 68:
                    if (isOneofPresent(message, entryNumber, pos)) {
                        hashCode = (hashCode * 53) + UnsafeUtil.getObject(message, offset).hashCode();
                        break;
                    } else {
                        break;
                    }
            }
        }
        int pos2 = hashCode * 53;
        int hashCode4 = pos2 + this.unknownFieldSchema.getFromMessage(message).hashCode();
        if (this.hasExtensions) {
            return (hashCode4 * 53) + this.extensionSchema.getExtensions(message).hashCode();
        }
        return hashCode4;
    }

    @Override // androidx.datastore.preferences.protobuf.Schema
    public void mergeFrom(T message, T other) {
        checkMutable(message);
        if (other == null) {
            throw new NullPointerException();
        }
        for (int i = 0; i < this.buffer.length; i += 3) {
            mergeSingleField(message, other, i);
        }
        SchemaUtil.mergeUnknownFields(this.unknownFieldSchema, message, other);
        if (this.hasExtensions) {
            SchemaUtil.mergeExtensions(this.extensionSchema, message, other);
        }
    }

    private void mergeSingleField(T message, T other, int pos) {
        int typeAndOffset = typeAndOffsetAt(pos);
        long offset = offset(typeAndOffset);
        int number = numberAt(pos);
        switch (type(typeAndOffset)) {
            case 0:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putDouble(message, offset, UnsafeUtil.getDouble(other, offset));
                    setFieldPresent(message, pos);
                    break;
                }
                break;
            case 1:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putFloat(message, offset, UnsafeUtil.getFloat(other, offset));
                    setFieldPresent(message, pos);
                    break;
                }
                break;
            case 2:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putLong(message, offset, UnsafeUtil.getLong(other, offset));
                    setFieldPresent(message, pos);
                    break;
                }
                break;
            case 3:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putLong(message, offset, UnsafeUtil.getLong(other, offset));
                    setFieldPresent(message, pos);
                    break;
                }
                break;
            case 4:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putInt(message, offset, UnsafeUtil.getInt(other, offset));
                    setFieldPresent(message, pos);
                    break;
                }
                break;
            case 5:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putLong(message, offset, UnsafeUtil.getLong(other, offset));
                    setFieldPresent(message, pos);
                    break;
                }
                break;
            case 6:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putInt(message, offset, UnsafeUtil.getInt(other, offset));
                    setFieldPresent(message, pos);
                    break;
                }
                break;
            case 7:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putBoolean(message, offset, UnsafeUtil.getBoolean(other, offset));
                    setFieldPresent(message, pos);
                    break;
                }
                break;
            case 8:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putObject(message, offset, UnsafeUtil.getObject(other, offset));
                    setFieldPresent(message, pos);
                    break;
                }
                break;
            case 9:
                mergeMessage(message, other, pos);
                break;
            case 10:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putObject(message, offset, UnsafeUtil.getObject(other, offset));
                    setFieldPresent(message, pos);
                    break;
                }
                break;
            case 11:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putInt(message, offset, UnsafeUtil.getInt(other, offset));
                    setFieldPresent(message, pos);
                    break;
                }
                break;
            case 12:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putInt(message, offset, UnsafeUtil.getInt(other, offset));
                    setFieldPresent(message, pos);
                    break;
                }
                break;
            case 13:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putInt(message, offset, UnsafeUtil.getInt(other, offset));
                    setFieldPresent(message, pos);
                    break;
                }
                break;
            case 14:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putLong(message, offset, UnsafeUtil.getLong(other, offset));
                    setFieldPresent(message, pos);
                    break;
                }
                break;
            case 15:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putInt(message, offset, UnsafeUtil.getInt(other, offset));
                    setFieldPresent(message, pos);
                    break;
                }
                break;
            case 16:
                if (isFieldPresent(other, pos)) {
                    UnsafeUtil.putLong(message, offset, UnsafeUtil.getLong(other, offset));
                    setFieldPresent(message, pos);
                    break;
                }
                break;
            case 17:
                mergeMessage(message, other, pos);
                break;
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_HORIZONTAL_BIAS /* 29 */:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
            case 41:
            case 42:
            case 43:
            case 44:
            case 45:
            case 46:
            case 47:
            case 48:
            case ConstraintLayout.LayoutParams.Table.LAYOUT_EDITOR_ABSOLUTEX /* 49 */:
                this.listFieldSchema.mergeListsAt(message, other, offset);
                break;
            case 50:
                SchemaUtil.mergeMap(this.mapFieldSchema, message, other, offset);
                break;
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
            case 56:
            case 57:
            case 58:
            case 59:
                if (isOneofPresent(other, number, pos)) {
                    UnsafeUtil.putObject(message, offset, UnsafeUtil.getObject(other, offset));
                    setOneofPresent(message, number, pos);
                    break;
                }
                break;
            case LockFreeTaskQueueCore.FROZEN_SHIFT /* 60 */:
                mergeOneofMessage(message, other, pos);
                break;
            case LockFreeTaskQueueCore.CLOSED_SHIFT /* 61 */:
            case 62:
            case 63:
            case 64:
            case 65:
            case 66:
            case 67:
                if (isOneofPresent(other, number, pos)) {
                    UnsafeUtil.putObject(message, offset, UnsafeUtil.getObject(other, offset));
                    setOneofPresent(message, number, pos);
                    break;
                }
                break;
            case 68:
                mergeOneofMessage(message, other, pos);
                break;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void mergeMessage(T targetParent, T sourceParent, int pos) {
        if (!isFieldPresent(sourceParent, pos)) {
            return;
        }
        int typeAndOffset = typeAndOffsetAt(pos);
        long offset = offset(typeAndOffset);
        Object source = UNSAFE.getObject(sourceParent, offset);
        if (source == null) {
            throw new IllegalStateException("Source subfield " + numberAt(pos) + " is present but null: " + sourceParent);
        }
        Schema messageFieldSchema = getMessageFieldSchema(pos);
        if (!isFieldPresent(targetParent, pos)) {
            if (!isMutable(source)) {
                UNSAFE.putObject(targetParent, offset, source);
            } else {
                Object copyOfSource = messageFieldSchema.newInstance();
                messageFieldSchema.mergeFrom(copyOfSource, source);
                UNSAFE.putObject(targetParent, offset, copyOfSource);
            }
            setFieldPresent(targetParent, pos);
            return;
        }
        Object target = UNSAFE.getObject(targetParent, offset);
        if (!isMutable(target)) {
            Object newInstance = messageFieldSchema.newInstance();
            messageFieldSchema.mergeFrom(newInstance, target);
            UNSAFE.putObject(targetParent, offset, newInstance);
            target = newInstance;
        }
        messageFieldSchema.mergeFrom(target, source);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void mergeOneofMessage(T targetParent, T sourceParent, int pos) {
        int number = numberAt(pos);
        if (!isOneofPresent(sourceParent, number, pos)) {
            return;
        }
        long offset = offset(typeAndOffsetAt(pos));
        Object source = UNSAFE.getObject(sourceParent, offset);
        if (source == null) {
            throw new IllegalStateException("Source subfield " + numberAt(pos) + " is present but null: " + sourceParent);
        }
        Schema messageFieldSchema = getMessageFieldSchema(pos);
        if (!isOneofPresent(targetParent, number, pos)) {
            if (!isMutable(source)) {
                UNSAFE.putObject(targetParent, offset, source);
            } else {
                Object copyOfSource = messageFieldSchema.newInstance();
                messageFieldSchema.mergeFrom(copyOfSource, source);
                UNSAFE.putObject(targetParent, offset, copyOfSource);
            }
            setOneofPresent(targetParent, number, pos);
            return;
        }
        Object target = UNSAFE.getObject(targetParent, offset);
        if (!isMutable(target)) {
            Object newInstance = messageFieldSchema.newInstance();
            messageFieldSchema.mergeFrom(newInstance, target);
            UNSAFE.putObject(targetParent, offset, newInstance);
            target = newInstance;
        }
        messageFieldSchema.mergeFrom(target, source);
    }

    @Override // androidx.datastore.preferences.protobuf.Schema
    public int getSerializedSize(T message) {
        return this.proto3 ? getSerializedSizeProto3(message) : getSerializedSizeProto2(message);
    }

    private int getSerializedSizeProto2(T message) {
        int size;
        int size2;
        int size3 = 0;
        Unsafe unsafe = UNSAFE;
        int currentPresenceFieldOffset = 1048575;
        int currentPresenceField = 0;
        for (int i = 0; i < this.buffer.length; i += 3) {
            int typeAndOffset = typeAndOffsetAt(i);
            int number = numberAt(i);
            int fieldType = type(typeAndOffset);
            int presenceMaskAndOffset = 0;
            int presenceMask = 0;
            if (fieldType > 17) {
                if (this.useCachedSizeField && fieldType >= FieldType.DOUBLE_LIST_PACKED.id() && fieldType <= FieldType.SINT64_LIST_PACKED.id()) {
                    presenceMaskAndOffset = this.buffer[i + 2] & 1048575;
                }
            } else {
                presenceMaskAndOffset = this.buffer[i + 2];
                int presenceFieldOffset = presenceMaskAndOffset & 1048575;
                presenceMask = 1 << (presenceMaskAndOffset >>> 20);
                if (presenceFieldOffset != currentPresenceFieldOffset) {
                    currentPresenceFieldOffset = presenceFieldOffset;
                    currentPresenceField = unsafe.getInt(message, presenceFieldOffset);
                }
            }
            long offset = offset(typeAndOffset);
            switch (fieldType) {
                case 0:
                    size = size3;
                    int size4 = currentPresenceField & presenceMask;
                    if (size4 != 0) {
                        size3 = size + CodedOutputStream.computeDoubleSize(number, 0.0d);
                        break;
                    }
                    break;
                case 1:
                    size = size3;
                    int size5 = currentPresenceField & presenceMask;
                    if (size5 != 0) {
                        size3 = size + CodedOutputStream.computeFloatSize(number, 0.0f);
                        break;
                    }
                    break;
                case 2:
                    size = size3;
                    int size6 = currentPresenceField & presenceMask;
                    if (size6 != 0) {
                        size3 = size + CodedOutputStream.computeInt64Size(number, unsafe.getLong(message, offset));
                        break;
                    }
                    break;
                case 3:
                    size = size3;
                    int size7 = currentPresenceField & presenceMask;
                    if (size7 != 0) {
                        size3 = size + CodedOutputStream.computeUInt64Size(number, unsafe.getLong(message, offset));
                        break;
                    }
                    break;
                case 4:
                    size = size3;
                    int size8 = currentPresenceField & presenceMask;
                    if (size8 != 0) {
                        size3 = size + CodedOutputStream.computeInt32Size(number, unsafe.getInt(message, offset));
                        break;
                    }
                    break;
                case 5:
                    size = size3;
                    int size9 = currentPresenceField & presenceMask;
                    if (size9 != 0) {
                        size3 = size + CodedOutputStream.computeFixed64Size(number, 0L);
                        break;
                    }
                    break;
                case 6:
                    size = size3;
                    int size10 = currentPresenceField & presenceMask;
                    if (size10 != 0) {
                        size3 = size + CodedOutputStream.computeFixed32Size(number, 0);
                        break;
                    }
                    break;
                case 7:
                    size = size3;
                    int size11 = currentPresenceField & presenceMask;
                    if (size11 != 0) {
                        size3 = size + CodedOutputStream.computeBoolSize(number, true);
                        break;
                    }
                    break;
                case 8:
                    size = size3;
                    int size12 = currentPresenceField & presenceMask;
                    if (size12 != 0) {
                        Object value = unsafe.getObject(message, offset);
                        if (value instanceof ByteString) {
                            size2 = size + CodedOutputStream.computeBytesSize(number, (ByteString) value);
                        } else {
                            size2 = size + CodedOutputStream.computeStringSize(number, (String) value);
                        }
                        size3 = size2;
                        break;
                    }
                    break;
                case 9:
                    size = size3;
                    int size13 = currentPresenceField & presenceMask;
                    if (size13 != 0) {
                        size3 = size + SchemaUtil.computeSizeMessage(number, unsafe.getObject(message, offset), getMessageFieldSchema(i));
                        break;
                    }
                    break;
                case 10:
                    size = size3;
                    int size14 = currentPresenceField & presenceMask;
                    if (size14 != 0) {
                        size3 = size + CodedOutputStream.computeBytesSize(number, (ByteString) unsafe.getObject(message, offset));
                        break;
                    }
                    break;
                case 11:
                    size = size3;
                    int size15 = currentPresenceField & presenceMask;
                    if (size15 != 0) {
                        size3 = size + CodedOutputStream.computeUInt32Size(number, unsafe.getInt(message, offset));
                        break;
                    }
                    break;
                case 12:
                    size = size3;
                    int size16 = currentPresenceField & presenceMask;
                    if (size16 != 0) {
                        size3 = size + CodedOutputStream.computeEnumSize(number, unsafe.getInt(message, offset));
                        break;
                    }
                    break;
                case 13:
                    size = size3;
                    int size17 = currentPresenceField & presenceMask;
                    if (size17 != 0) {
                        size3 = size + CodedOutputStream.computeSFixed32Size(number, 0);
                        break;
                    }
                    break;
                case 14:
                    size = size3;
                    int size18 = currentPresenceField & presenceMask;
                    if (size18 != 0) {
                        size3 = size + CodedOutputStream.computeSFixed64Size(number, 0L);
                        break;
                    }
                    break;
                case 15:
                    size = size3;
                    int size19 = currentPresenceField & presenceMask;
                    if (size19 != 0) {
                        size3 = size + CodedOutputStream.computeSInt32Size(number, unsafe.getInt(message, offset));
                        break;
                    }
                    break;
                case 16:
                    size = size3;
                    int size20 = currentPresenceField & presenceMask;
                    if (size20 != 0) {
                        size3 = size + CodedOutputStream.computeSInt64Size(number, unsafe.getLong(message, offset));
                        break;
                    }
                    break;
                case 17:
                    size = size3;
                    int size21 = currentPresenceField & presenceMask;
                    if (size21 != 0) {
                        size3 = size + CodedOutputStream.computeGroupSize(number, (MessageLite) unsafe.getObject(message, offset), getMessageFieldSchema(i));
                        break;
                    }
                    break;
                case 18:
                    size3 += SchemaUtil.computeSizeFixed64List(number, (List) unsafe.getObject(message, offset), false);
                    continue;
                case 19:
                    size3 += SchemaUtil.computeSizeFixed32List(number, (List) unsafe.getObject(message, offset), false);
                    continue;
                case 20:
                    size3 += SchemaUtil.computeSizeInt64List(number, (List) unsafe.getObject(message, offset), false);
                    continue;
                case 21:
                    size3 += SchemaUtil.computeSizeUInt64List(number, (List) unsafe.getObject(message, offset), false);
                    continue;
                case 22:
                    size3 += SchemaUtil.computeSizeInt32List(number, (List) unsafe.getObject(message, offset), false);
                    continue;
                case 23:
                    size3 += SchemaUtil.computeSizeFixed64List(number, (List) unsafe.getObject(message, offset), false);
                    continue;
                case 24:
                    size3 += SchemaUtil.computeSizeFixed32List(number, (List) unsafe.getObject(message, offset), false);
                    continue;
                case 25:
                    size3 += SchemaUtil.computeSizeBoolList(number, (List) unsafe.getObject(message, offset), false);
                    continue;
                case 26:
                    size3 += SchemaUtil.computeSizeStringList(number, (List) unsafe.getObject(message, offset));
                    continue;
                case 27:
                    size3 += SchemaUtil.computeSizeMessageList(number, (List) unsafe.getObject(message, offset), getMessageFieldSchema(i));
                    continue;
                case 28:
                    size3 += SchemaUtil.computeSizeByteStringList(number, (List) unsafe.getObject(message, offset));
                    continue;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_HORIZONTAL_BIAS /* 29 */:
                    size3 += SchemaUtil.computeSizeUInt32List(number, (List) unsafe.getObject(message, offset), false);
                    continue;
                case 30:
                    size3 += SchemaUtil.computeSizeEnumList(number, (List) unsafe.getObject(message, offset), false);
                    continue;
                case 31:
                    size3 += SchemaUtil.computeSizeFixed32List(number, (List) unsafe.getObject(message, offset), false);
                    continue;
                case 32:
                    size3 += SchemaUtil.computeSizeFixed64List(number, (List) unsafe.getObject(message, offset), false);
                    continue;
                case 33:
                    size3 += SchemaUtil.computeSizeSInt32List(number, (List) unsafe.getObject(message, offset), false);
                    continue;
                case 34:
                    size3 += SchemaUtil.computeSizeSInt64List(number, (List) unsafe.getObject(message, offset), false);
                    continue;
                case 35:
                    size = size3;
                    int fieldSize = SchemaUtil.computeSizeFixed64ListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, presenceMaskAndOffset, fieldSize);
                        }
                        size3 = size + CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize) + fieldSize;
                        break;
                    }
                    break;
                case 36:
                    size = size3;
                    int fieldSize2 = SchemaUtil.computeSizeFixed32ListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize2 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, presenceMaskAndOffset, fieldSize2);
                        }
                        size3 = size + CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize2) + fieldSize2;
                        break;
                    }
                    break;
                case 37:
                    size = size3;
                    int fieldSize3 = SchemaUtil.computeSizeInt64ListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize3 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, presenceMaskAndOffset, fieldSize3);
                        }
                        size3 = size + CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize3) + fieldSize3;
                        break;
                    }
                    break;
                case 38:
                    size = size3;
                    int fieldSize4 = SchemaUtil.computeSizeUInt64ListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize4 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, presenceMaskAndOffset, fieldSize4);
                        }
                        size3 = size + CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize4) + fieldSize4;
                        break;
                    }
                    break;
                case 39:
                    size = size3;
                    int fieldSize5 = SchemaUtil.computeSizeInt32ListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize5 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, presenceMaskAndOffset, fieldSize5);
                        }
                        size3 = size + CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize5) + fieldSize5;
                        break;
                    }
                    break;
                case 40:
                    size = size3;
                    int fieldSize6 = SchemaUtil.computeSizeFixed64ListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize6 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, presenceMaskAndOffset, fieldSize6);
                        }
                        size3 = size + CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize6) + fieldSize6;
                        break;
                    }
                    break;
                case 41:
                    size = size3;
                    int fieldSize7 = SchemaUtil.computeSizeFixed32ListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize7 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, presenceMaskAndOffset, fieldSize7);
                        }
                        size3 = size + CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize7) + fieldSize7;
                        break;
                    }
                    break;
                case 42:
                    size = size3;
                    int fieldSize8 = SchemaUtil.computeSizeBoolListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize8 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, presenceMaskAndOffset, fieldSize8);
                        }
                        size3 = size + CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize8) + fieldSize8;
                        break;
                    }
                    break;
                case 43:
                    size = size3;
                    int fieldSize9 = SchemaUtil.computeSizeUInt32ListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize9 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, presenceMaskAndOffset, fieldSize9);
                        }
                        size3 = size + CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize9) + fieldSize9;
                        break;
                    }
                    break;
                case 44:
                    size = size3;
                    int fieldSize10 = SchemaUtil.computeSizeEnumListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize10 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, presenceMaskAndOffset, fieldSize10);
                        }
                        size3 = size + CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize10) + fieldSize10;
                        break;
                    }
                    break;
                case 45:
                    size = size3;
                    int fieldSize11 = SchemaUtil.computeSizeFixed32ListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize11 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, presenceMaskAndOffset, fieldSize11);
                        }
                        size3 = size + CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize11) + fieldSize11;
                        break;
                    }
                    break;
                case 46:
                    size = size3;
                    int fieldSize12 = SchemaUtil.computeSizeFixed64ListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize12 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, presenceMaskAndOffset, fieldSize12);
                        }
                        size3 = size + CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize12) + fieldSize12;
                        break;
                    }
                    break;
                case 47:
                    size = size3;
                    int fieldSize13 = SchemaUtil.computeSizeSInt32ListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize13 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, presenceMaskAndOffset, fieldSize13);
                        }
                        size3 = size + CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize13) + fieldSize13;
                        break;
                    }
                    break;
                case 48:
                    size = size3;
                    int fieldSize14 = SchemaUtil.computeSizeSInt64ListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize14 > 0) {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, presenceMaskAndOffset, fieldSize14);
                        }
                        size3 = size + CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize14) + fieldSize14;
                        break;
                    }
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_EDITOR_ABSOLUTEX /* 49 */:
                    size3 += SchemaUtil.computeSizeGroupList(number, (List) unsafe.getObject(message, offset), getMessageFieldSchema(i));
                    continue;
                case 50:
                    size3 += this.mapFieldSchema.getSerializedSize(number, unsafe.getObject(message, offset), getMapFieldDefaultEntry(i));
                    continue;
                case 51:
                    if (!isOneofPresent(message, number, i)) {
                        size = size3;
                        break;
                    } else {
                        size3 += CodedOutputStream.computeDoubleSize(number, 0.0d);
                        break;
                    }
                case 52:
                    if (!isOneofPresent(message, number, i)) {
                        size = size3;
                        break;
                    } else {
                        size3 += CodedOutputStream.computeFloatSize(number, 0.0f);
                        break;
                    }
                case 53:
                    if (!isOneofPresent(message, number, i)) {
                        size = size3;
                        break;
                    } else {
                        size3 += CodedOutputStream.computeInt64Size(number, oneofLongAt(message, offset));
                        break;
                    }
                case 54:
                    if (!isOneofPresent(message, number, i)) {
                        size = size3;
                        break;
                    } else {
                        size3 += CodedOutputStream.computeUInt64Size(number, oneofLongAt(message, offset));
                        break;
                    }
                case 55:
                    if (!isOneofPresent(message, number, i)) {
                        size = size3;
                        break;
                    } else {
                        size3 += CodedOutputStream.computeInt32Size(number, oneofIntAt(message, offset));
                        break;
                    }
                case 56:
                    if (!isOneofPresent(message, number, i)) {
                        size = size3;
                        break;
                    } else {
                        size3 += CodedOutputStream.computeFixed64Size(number, 0L);
                        break;
                    }
                case 57:
                    if (!isOneofPresent(message, number, i)) {
                        size = size3;
                        break;
                    } else {
                        size3 += CodedOutputStream.computeFixed32Size(number, 0);
                        break;
                    }
                case 58:
                    if (!isOneofPresent(message, number, i)) {
                        size = size3;
                        break;
                    } else {
                        size3 += CodedOutputStream.computeBoolSize(number, true);
                        break;
                    }
                case 59:
                    if (!isOneofPresent(message, number, i)) {
                        size = size3;
                        break;
                    } else {
                        Object value2 = unsafe.getObject(message, offset);
                        if (value2 instanceof ByteString) {
                            size3 += CodedOutputStream.computeBytesSize(number, (ByteString) value2);
                            break;
                        } else {
                            size3 += CodedOutputStream.computeStringSize(number, (String) value2);
                            break;
                        }
                    }
                case LockFreeTaskQueueCore.FROZEN_SHIFT /* 60 */:
                    if (!isOneofPresent(message, number, i)) {
                        size = size3;
                        break;
                    } else {
                        size3 += SchemaUtil.computeSizeMessage(number, unsafe.getObject(message, offset), getMessageFieldSchema(i));
                        break;
                    }
                case LockFreeTaskQueueCore.CLOSED_SHIFT /* 61 */:
                    if (!isOneofPresent(message, number, i)) {
                        size = size3;
                        break;
                    } else {
                        size3 += CodedOutputStream.computeBytesSize(number, (ByteString) unsafe.getObject(message, offset));
                        break;
                    }
                case 62:
                    if (!isOneofPresent(message, number, i)) {
                        size = size3;
                        break;
                    } else {
                        size3 += CodedOutputStream.computeUInt32Size(number, oneofIntAt(message, offset));
                        break;
                    }
                case 63:
                    if (!isOneofPresent(message, number, i)) {
                        size = size3;
                        break;
                    } else {
                        size3 += CodedOutputStream.computeEnumSize(number, oneofIntAt(message, offset));
                        break;
                    }
                case 64:
                    if (!isOneofPresent(message, number, i)) {
                        size = size3;
                        break;
                    } else {
                        size3 += CodedOutputStream.computeSFixed32Size(number, 0);
                        break;
                    }
                case 65:
                    if (!isOneofPresent(message, number, i)) {
                        size = size3;
                        break;
                    } else {
                        size3 += CodedOutputStream.computeSFixed64Size(number, 0L);
                        break;
                    }
                case 66:
                    if (!isOneofPresent(message, number, i)) {
                        size = size3;
                        break;
                    } else {
                        size3 += CodedOutputStream.computeSInt32Size(number, oneofIntAt(message, offset));
                        break;
                    }
                case 67:
                    if (!isOneofPresent(message, number, i)) {
                        size = size3;
                        break;
                    } else {
                        size3 += CodedOutputStream.computeSInt64Size(number, oneofLongAt(message, offset));
                        break;
                    }
                case 68:
                    if (!isOneofPresent(message, number, i)) {
                        size = size3;
                        break;
                    } else {
                        size3 += CodedOutputStream.computeGroupSize(number, (MessageLite) unsafe.getObject(message, offset), getMessageFieldSchema(i));
                        break;
                    }
                default:
                    size = size3;
                    break;
            }
            size3 = size;
        }
        int size22 = size3 + getUnknownFieldsSerializedSize(this.unknownFieldSchema, message);
        if (this.hasExtensions) {
            return size22 + this.extensionSchema.getExtensions(message).getSerializedSize();
        }
        return size22;
    }

    private int getSerializedSizeProto3(T message) {
        int cachedSizeOffset;
        Unsafe unsafe = UNSAFE;
        int size = 0;
        for (int i = 0; i < this.buffer.length; i += 3) {
            int typeAndOffset = typeAndOffsetAt(i);
            int fieldType = type(typeAndOffset);
            int number = numberAt(i);
            long offset = offset(typeAndOffset);
            if (fieldType >= FieldType.DOUBLE_LIST_PACKED.id() && fieldType <= FieldType.SINT64_LIST_PACKED.id()) {
                cachedSizeOffset = this.buffer[i + 2] & 1048575;
            } else {
                cachedSizeOffset = 0;
            }
            switch (fieldType) {
                case 0:
                    if (isFieldPresent(message, i)) {
                        size += CodedOutputStream.computeDoubleSize(number, 0.0d);
                        break;
                    } else {
                        break;
                    }
                case 1:
                    if (isFieldPresent(message, i)) {
                        size += CodedOutputStream.computeFloatSize(number, 0.0f);
                        break;
                    } else {
                        break;
                    }
                case 2:
                    if (isFieldPresent(message, i)) {
                        size += CodedOutputStream.computeInt64Size(number, UnsafeUtil.getLong(message, offset));
                        break;
                    } else {
                        break;
                    }
                case 3:
                    if (isFieldPresent(message, i)) {
                        size += CodedOutputStream.computeUInt64Size(number, UnsafeUtil.getLong(message, offset));
                        break;
                    } else {
                        break;
                    }
                case 4:
                    if (isFieldPresent(message, i)) {
                        size += CodedOutputStream.computeInt32Size(number, UnsafeUtil.getInt(message, offset));
                        break;
                    } else {
                        break;
                    }
                case 5:
                    if (isFieldPresent(message, i)) {
                        size += CodedOutputStream.computeFixed64Size(number, 0L);
                        break;
                    } else {
                        break;
                    }
                case 6:
                    if (isFieldPresent(message, i)) {
                        size += CodedOutputStream.computeFixed32Size(number, 0);
                        break;
                    } else {
                        break;
                    }
                case 7:
                    if (isFieldPresent(message, i)) {
                        size += CodedOutputStream.computeBoolSize(number, true);
                        break;
                    } else {
                        break;
                    }
                case 8:
                    if (isFieldPresent(message, i)) {
                        Object value = UnsafeUtil.getObject(message, offset);
                        if (value instanceof ByteString) {
                            size += CodedOutputStream.computeBytesSize(number, (ByteString) value);
                            break;
                        } else {
                            size += CodedOutputStream.computeStringSize(number, (String) value);
                            break;
                        }
                    } else {
                        break;
                    }
                case 9:
                    if (isFieldPresent(message, i)) {
                        size += SchemaUtil.computeSizeMessage(number, UnsafeUtil.getObject(message, offset), getMessageFieldSchema(i));
                        break;
                    } else {
                        break;
                    }
                case 10:
                    if (isFieldPresent(message, i)) {
                        size += CodedOutputStream.computeBytesSize(number, (ByteString) UnsafeUtil.getObject(message, offset));
                        break;
                    } else {
                        break;
                    }
                case 11:
                    if (isFieldPresent(message, i)) {
                        size += CodedOutputStream.computeUInt32Size(number, UnsafeUtil.getInt(message, offset));
                        break;
                    } else {
                        break;
                    }
                case 12:
                    if (isFieldPresent(message, i)) {
                        size += CodedOutputStream.computeEnumSize(number, UnsafeUtil.getInt(message, offset));
                        break;
                    } else {
                        break;
                    }
                case 13:
                    if (isFieldPresent(message, i)) {
                        size += CodedOutputStream.computeSFixed32Size(number, 0);
                        break;
                    } else {
                        break;
                    }
                case 14:
                    if (isFieldPresent(message, i)) {
                        size += CodedOutputStream.computeSFixed64Size(number, 0L);
                        break;
                    } else {
                        break;
                    }
                case 15:
                    if (isFieldPresent(message, i)) {
                        size += CodedOutputStream.computeSInt32Size(number, UnsafeUtil.getInt(message, offset));
                        break;
                    } else {
                        break;
                    }
                case 16:
                    if (isFieldPresent(message, i)) {
                        size += CodedOutputStream.computeSInt64Size(number, UnsafeUtil.getLong(message, offset));
                        break;
                    } else {
                        break;
                    }
                case 17:
                    if (isFieldPresent(message, i)) {
                        size += CodedOutputStream.computeGroupSize(number, (MessageLite) UnsafeUtil.getObject(message, offset), getMessageFieldSchema(i));
                        break;
                    } else {
                        break;
                    }
                case 18:
                    size += SchemaUtil.computeSizeFixed64List(number, listAt(message, offset), false);
                    break;
                case 19:
                    size += SchemaUtil.computeSizeFixed32List(number, listAt(message, offset), false);
                    break;
                case 20:
                    size += SchemaUtil.computeSizeInt64List(number, listAt(message, offset), false);
                    break;
                case 21:
                    size += SchemaUtil.computeSizeUInt64List(number, listAt(message, offset), false);
                    break;
                case 22:
                    size += SchemaUtil.computeSizeInt32List(number, listAt(message, offset), false);
                    break;
                case 23:
                    size += SchemaUtil.computeSizeFixed64List(number, listAt(message, offset), false);
                    break;
                case 24:
                    size += SchemaUtil.computeSizeFixed32List(number, listAt(message, offset), false);
                    break;
                case 25:
                    size += SchemaUtil.computeSizeBoolList(number, listAt(message, offset), false);
                    break;
                case 26:
                    size += SchemaUtil.computeSizeStringList(number, listAt(message, offset));
                    break;
                case 27:
                    size += SchemaUtil.computeSizeMessageList(number, listAt(message, offset), getMessageFieldSchema(i));
                    break;
                case 28:
                    size += SchemaUtil.computeSizeByteStringList(number, listAt(message, offset));
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_HORIZONTAL_BIAS /* 29 */:
                    size += SchemaUtil.computeSizeUInt32List(number, listAt(message, offset), false);
                    break;
                case 30:
                    size += SchemaUtil.computeSizeEnumList(number, listAt(message, offset), false);
                    break;
                case 31:
                    size += SchemaUtil.computeSizeFixed32List(number, listAt(message, offset), false);
                    break;
                case 32:
                    size += SchemaUtil.computeSizeFixed64List(number, listAt(message, offset), false);
                    break;
                case 33:
                    size += SchemaUtil.computeSizeSInt32List(number, listAt(message, offset), false);
                    break;
                case 34:
                    size += SchemaUtil.computeSizeSInt64List(number, listAt(message, offset), false);
                    break;
                case 35:
                    int fieldSize = SchemaUtil.computeSizeFixed64ListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize <= 0) {
                        break;
                    } else {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, cachedSizeOffset, fieldSize);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize) + fieldSize;
                        break;
                    }
                case 36:
                    int fieldSize2 = SchemaUtil.computeSizeFixed32ListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize2 <= 0) {
                        break;
                    } else {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, cachedSizeOffset, fieldSize2);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize2) + fieldSize2;
                        break;
                    }
                case 37:
                    int fieldSize3 = SchemaUtil.computeSizeInt64ListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize3 <= 0) {
                        break;
                    } else {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, cachedSizeOffset, fieldSize3);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize3) + fieldSize3;
                        break;
                    }
                case 38:
                    int fieldSize4 = SchemaUtil.computeSizeUInt64ListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize4 <= 0) {
                        break;
                    } else {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, cachedSizeOffset, fieldSize4);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize4) + fieldSize4;
                        break;
                    }
                case 39:
                    int fieldSize5 = SchemaUtil.computeSizeInt32ListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize5 <= 0) {
                        break;
                    } else {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, cachedSizeOffset, fieldSize5);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize5) + fieldSize5;
                        break;
                    }
                case 40:
                    int fieldSize6 = SchemaUtil.computeSizeFixed64ListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize6 <= 0) {
                        break;
                    } else {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, cachedSizeOffset, fieldSize6);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize6) + fieldSize6;
                        break;
                    }
                case 41:
                    int fieldSize7 = SchemaUtil.computeSizeFixed32ListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize7 <= 0) {
                        break;
                    } else {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, cachedSizeOffset, fieldSize7);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize7) + fieldSize7;
                        break;
                    }
                case 42:
                    int fieldSize8 = SchemaUtil.computeSizeBoolListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize8 <= 0) {
                        break;
                    } else {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, cachedSizeOffset, fieldSize8);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize8) + fieldSize8;
                        break;
                    }
                case 43:
                    int fieldSize9 = SchemaUtil.computeSizeUInt32ListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize9 <= 0) {
                        break;
                    } else {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, cachedSizeOffset, fieldSize9);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize9) + fieldSize9;
                        break;
                    }
                case 44:
                    int fieldSize10 = SchemaUtil.computeSizeEnumListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize10 <= 0) {
                        break;
                    } else {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, cachedSizeOffset, fieldSize10);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize10) + fieldSize10;
                        break;
                    }
                case 45:
                    int fieldSize11 = SchemaUtil.computeSizeFixed32ListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize11 <= 0) {
                        break;
                    } else {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, cachedSizeOffset, fieldSize11);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize11) + fieldSize11;
                        break;
                    }
                case 46:
                    int fieldSize12 = SchemaUtil.computeSizeFixed64ListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize12 <= 0) {
                        break;
                    } else {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, cachedSizeOffset, fieldSize12);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize12) + fieldSize12;
                        break;
                    }
                case 47:
                    int fieldSize13 = SchemaUtil.computeSizeSInt32ListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize13 <= 0) {
                        break;
                    } else {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, cachedSizeOffset, fieldSize13);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize13) + fieldSize13;
                        break;
                    }
                case 48:
                    int fieldSize14 = SchemaUtil.computeSizeSInt64ListNoTag((List) unsafe.getObject(message, offset));
                    if (fieldSize14 <= 0) {
                        break;
                    } else {
                        if (this.useCachedSizeField) {
                            unsafe.putInt(message, cachedSizeOffset, fieldSize14);
                        }
                        size += CodedOutputStream.computeTagSize(number) + CodedOutputStream.computeUInt32SizeNoTag(fieldSize14) + fieldSize14;
                        break;
                    }
                case ConstraintLayout.LayoutParams.Table.LAYOUT_EDITOR_ABSOLUTEX /* 49 */:
                    size += SchemaUtil.computeSizeGroupList(number, listAt(message, offset), getMessageFieldSchema(i));
                    break;
                case 50:
                    size += this.mapFieldSchema.getSerializedSize(number, UnsafeUtil.getObject(message, offset), getMapFieldDefaultEntry(i));
                    break;
                case 51:
                    if (isOneofPresent(message, number, i)) {
                        size += CodedOutputStream.computeDoubleSize(number, 0.0d);
                        break;
                    } else {
                        break;
                    }
                case 52:
                    if (isOneofPresent(message, number, i)) {
                        size += CodedOutputStream.computeFloatSize(number, 0.0f);
                        break;
                    } else {
                        break;
                    }
                case 53:
                    if (isOneofPresent(message, number, i)) {
                        size += CodedOutputStream.computeInt64Size(number, oneofLongAt(message, offset));
                        break;
                    } else {
                        break;
                    }
                case 54:
                    if (isOneofPresent(message, number, i)) {
                        size += CodedOutputStream.computeUInt64Size(number, oneofLongAt(message, offset));
                        break;
                    } else {
                        break;
                    }
                case 55:
                    if (isOneofPresent(message, number, i)) {
                        size += CodedOutputStream.computeInt32Size(number, oneofIntAt(message, offset));
                        break;
                    } else {
                        break;
                    }
                case 56:
                    if (isOneofPresent(message, number, i)) {
                        size += CodedOutputStream.computeFixed64Size(number, 0L);
                        break;
                    } else {
                        break;
                    }
                case 57:
                    if (isOneofPresent(message, number, i)) {
                        size += CodedOutputStream.computeFixed32Size(number, 0);
                        break;
                    } else {
                        break;
                    }
                case 58:
                    if (isOneofPresent(message, number, i)) {
                        size += CodedOutputStream.computeBoolSize(number, true);
                        break;
                    } else {
                        break;
                    }
                case 59:
                    if (isOneofPresent(message, number, i)) {
                        Object value2 = UnsafeUtil.getObject(message, offset);
                        if (value2 instanceof ByteString) {
                            size += CodedOutputStream.computeBytesSize(number, (ByteString) value2);
                            break;
                        } else {
                            size += CodedOutputStream.computeStringSize(number, (String) value2);
                            break;
                        }
                    } else {
                        break;
                    }
                case LockFreeTaskQueueCore.FROZEN_SHIFT /* 60 */:
                    if (isOneofPresent(message, number, i)) {
                        size += SchemaUtil.computeSizeMessage(number, UnsafeUtil.getObject(message, offset), getMessageFieldSchema(i));
                        break;
                    } else {
                        break;
                    }
                case LockFreeTaskQueueCore.CLOSED_SHIFT /* 61 */:
                    if (isOneofPresent(message, number, i)) {
                        size += CodedOutputStream.computeBytesSize(number, (ByteString) UnsafeUtil.getObject(message, offset));
                        break;
                    } else {
                        break;
                    }
                case 62:
                    if (isOneofPresent(message, number, i)) {
                        size += CodedOutputStream.computeUInt32Size(number, oneofIntAt(message, offset));
                        break;
                    } else {
                        break;
                    }
                case 63:
                    if (isOneofPresent(message, number, i)) {
                        size += CodedOutputStream.computeEnumSize(number, oneofIntAt(message, offset));
                        break;
                    } else {
                        break;
                    }
                case 64:
                    if (isOneofPresent(message, number, i)) {
                        size += CodedOutputStream.computeSFixed32Size(number, 0);
                        break;
                    } else {
                        break;
                    }
                case 65:
                    if (isOneofPresent(message, number, i)) {
                        size += CodedOutputStream.computeSFixed64Size(number, 0L);
                        break;
                    } else {
                        break;
                    }
                case 66:
                    if (isOneofPresent(message, number, i)) {
                        size += CodedOutputStream.computeSInt32Size(number, oneofIntAt(message, offset));
                        break;
                    } else {
                        break;
                    }
                case 67:
                    if (isOneofPresent(message, number, i)) {
                        size += CodedOutputStream.computeSInt64Size(number, oneofLongAt(message, offset));
                        break;
                    } else {
                        break;
                    }
                case 68:
                    if (isOneofPresent(message, number, i)) {
                        size += CodedOutputStream.computeGroupSize(number, (MessageLite) UnsafeUtil.getObject(message, offset), getMessageFieldSchema(i));
                        break;
                    } else {
                        break;
                    }
            }
        }
        return size + getUnknownFieldsSerializedSize(this.unknownFieldSchema, message);
    }

    private <UT, UB> int getUnknownFieldsSerializedSize(UnknownFieldSchema<UT, UB> schema, T message) {
        UT unknowns = schema.getFromMessage(message);
        return schema.getSerializedSize(unknowns);
    }

    private static List<?> listAt(Object message, long offset) {
        return (List) UnsafeUtil.getObject(message, offset);
    }

    @Override // androidx.datastore.preferences.protobuf.Schema
    public void writeTo(T message, Writer writer) throws IOException {
        if (writer.fieldOrder() == Writer.FieldOrder.DESCENDING) {
            writeFieldsInDescendingOrder(message, writer);
        } else if (this.proto3) {
            writeFieldsInAscendingOrderProto3(message, writer);
        } else {
            writeFieldsInAscendingOrderProto2(message, writer);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void writeFieldsInAscendingOrderProto2(T message, Writer writer) throws IOException {
        int i;
        int currentPresenceField;
        int bufferLength;
        Map.Entry nextExtension;
        Iterator<? extends Map.Entry<?, ?>> extensionIterator = null;
        Map.Entry nextExtension2 = null;
        if (this.hasExtensions) {
            FieldSet<?> extensions = this.extensionSchema.getExtensions(message);
            if (!extensions.isEmpty()) {
                extensionIterator = extensions.iterator();
                nextExtension2 = extensionIterator.next();
            }
        }
        int currentPresenceFieldOffset = 1048575;
        int currentPresenceField2 = 0;
        int bufferLength2 = this.buffer.length;
        Unsafe unsafe = UNSAFE;
        int pos = 0;
        while (pos < bufferLength2) {
            int typeAndOffset = typeAndOffsetAt(pos);
            int number = numberAt(pos);
            int fieldType = type(typeAndOffset);
            int presenceMask = 0;
            Iterator<? extends Map.Entry<?, ?>> extensionIterator2 = extensionIterator;
            if (fieldType <= 17) {
                int presenceMaskAndOffset = this.buffer[pos + 2];
                int presenceFieldOffset = 1048575 & presenceMaskAndOffset;
                if (presenceFieldOffset == currentPresenceFieldOffset) {
                    nextExtension = nextExtension2;
                    i = 1;
                } else {
                    currentPresenceFieldOffset = presenceFieldOffset;
                    nextExtension = nextExtension2;
                    i = 1;
                    currentPresenceField2 = unsafe.getInt(message, presenceFieldOffset);
                }
                presenceMask = i << (presenceMaskAndOffset >>> 20);
                nextExtension2 = nextExtension;
            } else {
                i = 1;
            }
            while (nextExtension2 != null && this.extensionSchema.extensionNumber(nextExtension2) <= number) {
                this.extensionSchema.serializeExtension(writer, nextExtension2);
                nextExtension2 = extensionIterator2.hasNext() ? extensionIterator2.next() : null;
            }
            Map.Entry nextExtension3 = nextExtension2;
            long offset = offset(typeAndOffset);
            int currentPresenceFieldOffset2 = currentPresenceFieldOffset;
            switch (fieldType) {
                case 0:
                    currentPresenceField = currentPresenceField2;
                    bufferLength = bufferLength2;
                    if ((currentPresenceField & presenceMask) == 0) {
                        break;
                    } else {
                        writer.writeDouble(number, doubleAt(message, offset));
                        break;
                    }
                case 1:
                    currentPresenceField = currentPresenceField2;
                    bufferLength = bufferLength2;
                    if ((currentPresenceField & presenceMask) == 0) {
                        break;
                    } else {
                        writer.writeFloat(number, floatAt(message, offset));
                        break;
                    }
                case 2:
                    currentPresenceField = currentPresenceField2;
                    bufferLength = bufferLength2;
                    if ((currentPresenceField & presenceMask) == 0) {
                        break;
                    } else {
                        writer.writeInt64(number, unsafe.getLong(message, offset));
                        break;
                    }
                case 3:
                    currentPresenceField = currentPresenceField2;
                    bufferLength = bufferLength2;
                    if ((currentPresenceField & presenceMask) == 0) {
                        break;
                    } else {
                        writer.writeUInt64(number, unsafe.getLong(message, offset));
                        break;
                    }
                case 4:
                    currentPresenceField = currentPresenceField2;
                    bufferLength = bufferLength2;
                    if ((currentPresenceField & presenceMask) == 0) {
                        break;
                    } else {
                        writer.writeInt32(number, unsafe.getInt(message, offset));
                        break;
                    }
                case 5:
                    currentPresenceField = currentPresenceField2;
                    bufferLength = bufferLength2;
                    if ((currentPresenceField & presenceMask) == 0) {
                        break;
                    } else {
                        writer.writeFixed64(number, unsafe.getLong(message, offset));
                        break;
                    }
                case 6:
                    currentPresenceField = currentPresenceField2;
                    bufferLength = bufferLength2;
                    if ((currentPresenceField & presenceMask) == 0) {
                        break;
                    } else {
                        writer.writeFixed32(number, unsafe.getInt(message, offset));
                        break;
                    }
                case 7:
                    currentPresenceField = currentPresenceField2;
                    bufferLength = bufferLength2;
                    if ((currentPresenceField & presenceMask) == 0) {
                        break;
                    } else {
                        writer.writeBool(number, booleanAt(message, offset));
                        break;
                    }
                case 8:
                    currentPresenceField = currentPresenceField2;
                    bufferLength = bufferLength2;
                    if ((currentPresenceField & presenceMask) == 0) {
                        break;
                    } else {
                        writeString(number, unsafe.getObject(message, offset), writer);
                        break;
                    }
                case 9:
                    currentPresenceField = currentPresenceField2;
                    bufferLength = bufferLength2;
                    if ((currentPresenceField & presenceMask) == 0) {
                        break;
                    } else {
                        Object value = unsafe.getObject(message, offset);
                        writer.writeMessage(number, value, getMessageFieldSchema(pos));
                        break;
                    }
                case 10:
                    currentPresenceField = currentPresenceField2;
                    bufferLength = bufferLength2;
                    if ((currentPresenceField & presenceMask) == 0) {
                        break;
                    } else {
                        writer.writeBytes(number, (ByteString) unsafe.getObject(message, offset));
                        break;
                    }
                case 11:
                    currentPresenceField = currentPresenceField2;
                    bufferLength = bufferLength2;
                    if ((currentPresenceField & presenceMask) == 0) {
                        break;
                    } else {
                        writer.writeUInt32(number, unsafe.getInt(message, offset));
                        break;
                    }
                case 12:
                    currentPresenceField = currentPresenceField2;
                    bufferLength = bufferLength2;
                    if ((currentPresenceField & presenceMask) == 0) {
                        break;
                    } else {
                        writer.writeEnum(number, unsafe.getInt(message, offset));
                        break;
                    }
                case 13:
                    currentPresenceField = currentPresenceField2;
                    bufferLength = bufferLength2;
                    if ((currentPresenceField & presenceMask) == 0) {
                        break;
                    } else {
                        writer.writeSFixed32(number, unsafe.getInt(message, offset));
                        break;
                    }
                case 14:
                    currentPresenceField = currentPresenceField2;
                    bufferLength = bufferLength2;
                    if ((currentPresenceField & presenceMask) == 0) {
                        break;
                    } else {
                        writer.writeSFixed64(number, unsafe.getLong(message, offset));
                        break;
                    }
                case 15:
                    currentPresenceField = currentPresenceField2;
                    bufferLength = bufferLength2;
                    if ((currentPresenceField & presenceMask) == 0) {
                        break;
                    } else {
                        writer.writeSInt32(number, unsafe.getInt(message, offset));
                        break;
                    }
                case 16:
                    currentPresenceField = currentPresenceField2;
                    bufferLength = bufferLength2;
                    if ((currentPresenceField & presenceMask) == 0) {
                        break;
                    } else {
                        writer.writeSInt64(number, unsafe.getLong(message, offset));
                        break;
                    }
                case 17:
                    currentPresenceField = currentPresenceField2;
                    bufferLength = bufferLength2;
                    if ((currentPresenceField & presenceMask) == 0) {
                        break;
                    } else {
                        writer.writeGroup(number, unsafe.getObject(message, offset), getMessageFieldSchema(pos));
                        break;
                    }
                case 18:
                    currentPresenceField = currentPresenceField2;
                    bufferLength = bufferLength2;
                    int currentPresenceField3 = numberAt(pos);
                    SchemaUtil.writeDoubleList(currentPresenceField3, (List) unsafe.getObject(message, offset), writer, false);
                    break;
                case 19:
                    currentPresenceField = currentPresenceField2;
                    bufferLength = bufferLength2;
                    int currentPresenceField4 = numberAt(pos);
                    SchemaUtil.writeFloatList(currentPresenceField4, (List) unsafe.getObject(message, offset), writer, false);
                    break;
                case 20:
                    currentPresenceField = currentPresenceField2;
                    bufferLength = bufferLength2;
                    int currentPresenceField5 = numberAt(pos);
                    SchemaUtil.writeInt64List(currentPresenceField5, (List) unsafe.getObject(message, offset), writer, false);
                    break;
                case 21:
                    currentPresenceField = currentPresenceField2;
                    bufferLength = bufferLength2;
                    int currentPresenceField6 = numberAt(pos);
                    SchemaUtil.writeUInt64List(currentPresenceField6, (List) unsafe.getObject(message, offset), writer, false);
                    break;
                case 22:
                    currentPresenceField = currentPresenceField2;
                    bufferLength = bufferLength2;
                    int currentPresenceField7 = numberAt(pos);
                    SchemaUtil.writeInt32List(currentPresenceField7, (List) unsafe.getObject(message, offset), writer, false);
                    break;
                case 23:
                    currentPresenceField = currentPresenceField2;
                    bufferLength = bufferLength2;
                    int currentPresenceField8 = numberAt(pos);
                    SchemaUtil.writeFixed64List(currentPresenceField8, (List) unsafe.getObject(message, offset), writer, false);
                    break;
                case 24:
                    currentPresenceField = currentPresenceField2;
                    bufferLength = bufferLength2;
                    int currentPresenceField9 = numberAt(pos);
                    SchemaUtil.writeFixed32List(currentPresenceField9, (List) unsafe.getObject(message, offset), writer, false);
                    break;
                case 25:
                    currentPresenceField = currentPresenceField2;
                    bufferLength = bufferLength2;
                    int currentPresenceField10 = numberAt(pos);
                    SchemaUtil.writeBoolList(currentPresenceField10, (List) unsafe.getObject(message, offset), writer, false);
                    break;
                case 26:
                    currentPresenceField = currentPresenceField2;
                    bufferLength = bufferLength2;
                    SchemaUtil.writeStringList(numberAt(pos), (List) unsafe.getObject(message, offset), writer);
                    break;
                case 27:
                    currentPresenceField = currentPresenceField2;
                    bufferLength = bufferLength2;
                    SchemaUtil.writeMessageList(numberAt(pos), (List) unsafe.getObject(message, offset), writer, getMessageFieldSchema(pos));
                    break;
                case 28:
                    currentPresenceField = currentPresenceField2;
                    bufferLength = bufferLength2;
                    SchemaUtil.writeBytesList(numberAt(pos), (List) unsafe.getObject(message, offset), writer);
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_HORIZONTAL_BIAS /* 29 */:
                    currentPresenceField = currentPresenceField2;
                    bufferLength = bufferLength2;
                    int currentPresenceField11 = numberAt(pos);
                    SchemaUtil.writeUInt32List(currentPresenceField11, (List) unsafe.getObject(message, offset), writer, false);
                    break;
                case 30:
                    currentPresenceField = currentPresenceField2;
                    bufferLength = bufferLength2;
                    int currentPresenceField12 = numberAt(pos);
                    SchemaUtil.writeEnumList(currentPresenceField12, (List) unsafe.getObject(message, offset), writer, false);
                    break;
                case 31:
                    currentPresenceField = currentPresenceField2;
                    bufferLength = bufferLength2;
                    int currentPresenceField13 = numberAt(pos);
                    SchemaUtil.writeSFixed32List(currentPresenceField13, (List) unsafe.getObject(message, offset), writer, false);
                    break;
                case 32:
                    currentPresenceField = currentPresenceField2;
                    bufferLength = bufferLength2;
                    int currentPresenceField14 = numberAt(pos);
                    SchemaUtil.writeSFixed64List(currentPresenceField14, (List) unsafe.getObject(message, offset), writer, false);
                    break;
                case 33:
                    currentPresenceField = currentPresenceField2;
                    bufferLength = bufferLength2;
                    int currentPresenceField15 = numberAt(pos);
                    SchemaUtil.writeSInt32List(currentPresenceField15, (List) unsafe.getObject(message, offset), writer, false);
                    break;
                case 34:
                    currentPresenceField = currentPresenceField2;
                    bufferLength = bufferLength2;
                    int currentPresenceField16 = numberAt(pos);
                    SchemaUtil.writeSInt64List(currentPresenceField16, (List) unsafe.getObject(message, offset), writer, false);
                    break;
                case 35:
                    currentPresenceField = currentPresenceField2;
                    bufferLength = bufferLength2;
                    SchemaUtil.writeDoubleList(numberAt(pos), (List) unsafe.getObject(message, offset), writer, i);
                    break;
                case 36:
                    currentPresenceField = currentPresenceField2;
                    bufferLength = bufferLength2;
                    SchemaUtil.writeFloatList(numberAt(pos), (List) unsafe.getObject(message, offset), writer, i);
                    break;
                case 37:
                    currentPresenceField = currentPresenceField2;
                    bufferLength = bufferLength2;
                    SchemaUtil.writeInt64List(numberAt(pos), (List) unsafe.getObject(message, offset), writer, i);
                    break;
                case 38:
                    currentPresenceField = currentPresenceField2;
                    bufferLength = bufferLength2;
                    SchemaUtil.writeUInt64List(numberAt(pos), (List) unsafe.getObject(message, offset), writer, i);
                    break;
                case 39:
                    currentPresenceField = currentPresenceField2;
                    bufferLength = bufferLength2;
                    SchemaUtil.writeInt32List(numberAt(pos), (List) unsafe.getObject(message, offset), writer, i);
                    break;
                case 40:
                    currentPresenceField = currentPresenceField2;
                    bufferLength = bufferLength2;
                    SchemaUtil.writeFixed64List(numberAt(pos), (List) unsafe.getObject(message, offset), writer, i);
                    break;
                case 41:
                    currentPresenceField = currentPresenceField2;
                    bufferLength = bufferLength2;
                    SchemaUtil.writeFixed32List(numberAt(pos), (List) unsafe.getObject(message, offset), writer, i);
                    break;
                case 42:
                    currentPresenceField = currentPresenceField2;
                    bufferLength = bufferLength2;
                    SchemaUtil.writeBoolList(numberAt(pos), (List) unsafe.getObject(message, offset), writer, i);
                    break;
                case 43:
                    currentPresenceField = currentPresenceField2;
                    bufferLength = bufferLength2;
                    SchemaUtil.writeUInt32List(numberAt(pos), (List) unsafe.getObject(message, offset), writer, i);
                    break;
                case 44:
                    currentPresenceField = currentPresenceField2;
                    bufferLength = bufferLength2;
                    SchemaUtil.writeEnumList(numberAt(pos), (List) unsafe.getObject(message, offset), writer, i);
                    break;
                case 45:
                    currentPresenceField = currentPresenceField2;
                    bufferLength = bufferLength2;
                    SchemaUtil.writeSFixed32List(numberAt(pos), (List) unsafe.getObject(message, offset), writer, i);
                    break;
                case 46:
                    currentPresenceField = currentPresenceField2;
                    bufferLength = bufferLength2;
                    SchemaUtil.writeSFixed64List(numberAt(pos), (List) unsafe.getObject(message, offset), writer, i);
                    break;
                case 47:
                    currentPresenceField = currentPresenceField2;
                    bufferLength = bufferLength2;
                    SchemaUtil.writeSInt32List(numberAt(pos), (List) unsafe.getObject(message, offset), writer, i);
                    break;
                case 48:
                    currentPresenceField = currentPresenceField2;
                    bufferLength = bufferLength2;
                    SchemaUtil.writeSInt64List(numberAt(pos), (List) unsafe.getObject(message, offset), writer, i);
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_EDITOR_ABSOLUTEX /* 49 */:
                    currentPresenceField = currentPresenceField2;
                    bufferLength = bufferLength2;
                    SchemaUtil.writeGroupList(numberAt(pos), (List) unsafe.getObject(message, offset), writer, getMessageFieldSchema(pos));
                    break;
                case 50:
                    currentPresenceField = currentPresenceField2;
                    writeMapHelper(writer, number, unsafe.getObject(message, offset), pos);
                    bufferLength = bufferLength2;
                    break;
                case 51:
                    currentPresenceField = currentPresenceField2;
                    if (!isOneofPresent(message, number, pos)) {
                        bufferLength = bufferLength2;
                        break;
                    } else {
                        writer.writeDouble(number, oneofDoubleAt(message, offset));
                        bufferLength = bufferLength2;
                        break;
                    }
                case 52:
                    currentPresenceField = currentPresenceField2;
                    if (!isOneofPresent(message, number, pos)) {
                        bufferLength = bufferLength2;
                        break;
                    } else {
                        writer.writeFloat(number, oneofFloatAt(message, offset));
                        bufferLength = bufferLength2;
                        break;
                    }
                case 53:
                    currentPresenceField = currentPresenceField2;
                    if (!isOneofPresent(message, number, pos)) {
                        bufferLength = bufferLength2;
                        break;
                    } else {
                        writer.writeInt64(number, oneofLongAt(message, offset));
                        bufferLength = bufferLength2;
                        break;
                    }
                case 54:
                    currentPresenceField = currentPresenceField2;
                    if (!isOneofPresent(message, number, pos)) {
                        bufferLength = bufferLength2;
                        break;
                    } else {
                        writer.writeUInt64(number, oneofLongAt(message, offset));
                        bufferLength = bufferLength2;
                        break;
                    }
                case 55:
                    currentPresenceField = currentPresenceField2;
                    if (!isOneofPresent(message, number, pos)) {
                        bufferLength = bufferLength2;
                        break;
                    } else {
                        writer.writeInt32(number, oneofIntAt(message, offset));
                        bufferLength = bufferLength2;
                        break;
                    }
                case 56:
                    currentPresenceField = currentPresenceField2;
                    if (!isOneofPresent(message, number, pos)) {
                        bufferLength = bufferLength2;
                        break;
                    } else {
                        writer.writeFixed64(number, oneofLongAt(message, offset));
                        bufferLength = bufferLength2;
                        break;
                    }
                case 57:
                    currentPresenceField = currentPresenceField2;
                    if (!isOneofPresent(message, number, pos)) {
                        bufferLength = bufferLength2;
                        break;
                    } else {
                        writer.writeFixed32(number, oneofIntAt(message, offset));
                        bufferLength = bufferLength2;
                        break;
                    }
                case 58:
                    currentPresenceField = currentPresenceField2;
                    if (!isOneofPresent(message, number, pos)) {
                        bufferLength = bufferLength2;
                        break;
                    } else {
                        writer.writeBool(number, oneofBooleanAt(message, offset));
                        bufferLength = bufferLength2;
                        break;
                    }
                case 59:
                    currentPresenceField = currentPresenceField2;
                    if (isOneofPresent(message, number, pos)) {
                        writeString(number, unsafe.getObject(message, offset), writer);
                        bufferLength = bufferLength2;
                        break;
                    } else {
                        bufferLength = bufferLength2;
                        break;
                    }
                case LockFreeTaskQueueCore.FROZEN_SHIFT /* 60 */:
                    currentPresenceField = currentPresenceField2;
                    if (!isOneofPresent(message, number, pos)) {
                        bufferLength = bufferLength2;
                        break;
                    } else {
                        Object value2 = unsafe.getObject(message, offset);
                        writer.writeMessage(number, value2, getMessageFieldSchema(pos));
                        bufferLength = bufferLength2;
                        break;
                    }
                case LockFreeTaskQueueCore.CLOSED_SHIFT /* 61 */:
                    currentPresenceField = currentPresenceField2;
                    if (!isOneofPresent(message, number, pos)) {
                        bufferLength = bufferLength2;
                        break;
                    } else {
                        writer.writeBytes(number, (ByteString) unsafe.getObject(message, offset));
                        bufferLength = bufferLength2;
                        break;
                    }
                case 62:
                    currentPresenceField = currentPresenceField2;
                    if (!isOneofPresent(message, number, pos)) {
                        bufferLength = bufferLength2;
                        break;
                    } else {
                        writer.writeUInt32(number, oneofIntAt(message, offset));
                        bufferLength = bufferLength2;
                        break;
                    }
                case 63:
                    currentPresenceField = currentPresenceField2;
                    if (!isOneofPresent(message, number, pos)) {
                        bufferLength = bufferLength2;
                        break;
                    } else {
                        writer.writeEnum(number, oneofIntAt(message, offset));
                        bufferLength = bufferLength2;
                        break;
                    }
                case 64:
                    currentPresenceField = currentPresenceField2;
                    if (!isOneofPresent(message, number, pos)) {
                        bufferLength = bufferLength2;
                        break;
                    } else {
                        writer.writeSFixed32(number, oneofIntAt(message, offset));
                        bufferLength = bufferLength2;
                        break;
                    }
                case 65:
                    currentPresenceField = currentPresenceField2;
                    if (!isOneofPresent(message, number, pos)) {
                        bufferLength = bufferLength2;
                        break;
                    } else {
                        writer.writeSFixed64(number, oneofLongAt(message, offset));
                        bufferLength = bufferLength2;
                        break;
                    }
                case 66:
                    currentPresenceField = currentPresenceField2;
                    if (!isOneofPresent(message, number, pos)) {
                        bufferLength = bufferLength2;
                        break;
                    } else {
                        writer.writeSInt32(number, oneofIntAt(message, offset));
                        bufferLength = bufferLength2;
                        break;
                    }
                case 67:
                    currentPresenceField = currentPresenceField2;
                    if (!isOneofPresent(message, number, pos)) {
                        bufferLength = bufferLength2;
                        break;
                    } else {
                        writer.writeSInt64(number, oneofLongAt(message, offset));
                        bufferLength = bufferLength2;
                        break;
                    }
                case 68:
                    if (!isOneofPresent(message, number, pos)) {
                        currentPresenceField = currentPresenceField2;
                        bufferLength = bufferLength2;
                        break;
                    } else {
                        currentPresenceField = currentPresenceField2;
                        writer.writeGroup(number, unsafe.getObject(message, offset), getMessageFieldSchema(pos));
                        bufferLength = bufferLength2;
                        break;
                    }
                default:
                    currentPresenceField = currentPresenceField2;
                    bufferLength = bufferLength2;
                    break;
            }
            pos += 3;
            nextExtension2 = nextExtension3;
            extensionIterator = extensionIterator2;
            currentPresenceFieldOffset = currentPresenceFieldOffset2;
            currentPresenceField2 = currentPresenceField;
            bufferLength2 = bufferLength;
        }
        Iterator<? extends Map.Entry<?, ?>> extensionIterator3 = extensionIterator;
        while (nextExtension2 != null) {
            this.extensionSchema.serializeExtension(writer, nextExtension2);
            nextExtension2 = extensionIterator3.hasNext() ? extensionIterator3.next() : null;
        }
        writeUnknownInMessageTo(this.unknownFieldSchema, message, writer);
    }

    private void writeFieldsInAscendingOrderProto3(T message, Writer writer) throws IOException {
        Iterator<? extends Map.Entry<?, ?>> extensionIterator = null;
        Map.Entry nextExtension = null;
        if (this.hasExtensions) {
            FieldSet<?> extensions = this.extensionSchema.getExtensions(message);
            if (!extensions.isEmpty()) {
                extensionIterator = extensions.iterator();
                nextExtension = extensionIterator.next();
            }
        }
        int bufferLength = this.buffer.length;
        for (int pos = 0; pos < bufferLength; pos += 3) {
            int typeAndOffset = typeAndOffsetAt(pos);
            int number = numberAt(pos);
            while (nextExtension != null && this.extensionSchema.extensionNumber(nextExtension) <= number) {
                this.extensionSchema.serializeExtension(writer, nextExtension);
                nextExtension = extensionIterator.hasNext() ? extensionIterator.next() : null;
            }
            switch (type(typeAndOffset)) {
                case 0:
                    if (isFieldPresent(message, pos)) {
                        writer.writeDouble(number, doubleAt(message, offset(typeAndOffset)));
                        break;
                    } else {
                        break;
                    }
                case 1:
                    if (isFieldPresent(message, pos)) {
                        writer.writeFloat(number, floatAt(message, offset(typeAndOffset)));
                        break;
                    } else {
                        break;
                    }
                case 2:
                    if (isFieldPresent(message, pos)) {
                        writer.writeInt64(number, longAt(message, offset(typeAndOffset)));
                        break;
                    } else {
                        break;
                    }
                case 3:
                    if (isFieldPresent(message, pos)) {
                        writer.writeUInt64(number, longAt(message, offset(typeAndOffset)));
                        break;
                    } else {
                        break;
                    }
                case 4:
                    if (isFieldPresent(message, pos)) {
                        writer.writeInt32(number, intAt(message, offset(typeAndOffset)));
                        break;
                    } else {
                        break;
                    }
                case 5:
                    if (isFieldPresent(message, pos)) {
                        writer.writeFixed64(number, longAt(message, offset(typeAndOffset)));
                        break;
                    } else {
                        break;
                    }
                case 6:
                    if (isFieldPresent(message, pos)) {
                        writer.writeFixed32(number, intAt(message, offset(typeAndOffset)));
                        break;
                    } else {
                        break;
                    }
                case 7:
                    if (isFieldPresent(message, pos)) {
                        writer.writeBool(number, booleanAt(message, offset(typeAndOffset)));
                        break;
                    } else {
                        break;
                    }
                case 8:
                    if (isFieldPresent(message, pos)) {
                        writeString(number, UnsafeUtil.getObject(message, offset(typeAndOffset)), writer);
                        break;
                    } else {
                        break;
                    }
                case 9:
                    if (isFieldPresent(message, pos)) {
                        Object value = UnsafeUtil.getObject(message, offset(typeAndOffset));
                        writer.writeMessage(number, value, getMessageFieldSchema(pos));
                        break;
                    } else {
                        break;
                    }
                case 10:
                    if (isFieldPresent(message, pos)) {
                        writer.writeBytes(number, (ByteString) UnsafeUtil.getObject(message, offset(typeAndOffset)));
                        break;
                    } else {
                        break;
                    }
                case 11:
                    if (isFieldPresent(message, pos)) {
                        writer.writeUInt32(number, intAt(message, offset(typeAndOffset)));
                        break;
                    } else {
                        break;
                    }
                case 12:
                    if (isFieldPresent(message, pos)) {
                        writer.writeEnum(number, intAt(message, offset(typeAndOffset)));
                        break;
                    } else {
                        break;
                    }
                case 13:
                    if (isFieldPresent(message, pos)) {
                        writer.writeSFixed32(number, intAt(message, offset(typeAndOffset)));
                        break;
                    } else {
                        break;
                    }
                case 14:
                    if (isFieldPresent(message, pos)) {
                        writer.writeSFixed64(number, longAt(message, offset(typeAndOffset)));
                        break;
                    } else {
                        break;
                    }
                case 15:
                    if (isFieldPresent(message, pos)) {
                        writer.writeSInt32(number, intAt(message, offset(typeAndOffset)));
                        break;
                    } else {
                        break;
                    }
                case 16:
                    if (isFieldPresent(message, pos)) {
                        writer.writeSInt64(number, longAt(message, offset(typeAndOffset)));
                        break;
                    } else {
                        break;
                    }
                case 17:
                    if (isFieldPresent(message, pos)) {
                        writer.writeGroup(number, UnsafeUtil.getObject(message, offset(typeAndOffset)), getMessageFieldSchema(pos));
                        break;
                    } else {
                        break;
                    }
                case 18:
                    SchemaUtil.writeDoubleList(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                    break;
                case 19:
                    SchemaUtil.writeFloatList(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                    break;
                case 20:
                    SchemaUtil.writeInt64List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                    break;
                case 21:
                    SchemaUtil.writeUInt64List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                    break;
                case 22:
                    SchemaUtil.writeInt32List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                    break;
                case 23:
                    SchemaUtil.writeFixed64List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                    break;
                case 24:
                    SchemaUtil.writeFixed32List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                    break;
                case 25:
                    SchemaUtil.writeBoolList(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                    break;
                case 26:
                    SchemaUtil.writeStringList(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer);
                    break;
                case 27:
                    SchemaUtil.writeMessageList(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, getMessageFieldSchema(pos));
                    break;
                case 28:
                    SchemaUtil.writeBytesList(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer);
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_HORIZONTAL_BIAS /* 29 */:
                    SchemaUtil.writeUInt32List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                    break;
                case 30:
                    SchemaUtil.writeEnumList(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                    break;
                case 31:
                    SchemaUtil.writeSFixed32List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                    break;
                case 32:
                    SchemaUtil.writeSFixed64List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                    break;
                case 33:
                    SchemaUtil.writeSInt32List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                    break;
                case 34:
                    SchemaUtil.writeSInt64List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                    break;
                case 35:
                    SchemaUtil.writeDoubleList(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                    break;
                case 36:
                    SchemaUtil.writeFloatList(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                    break;
                case 37:
                    SchemaUtil.writeInt64List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                    break;
                case 38:
                    SchemaUtil.writeUInt64List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                    break;
                case 39:
                    SchemaUtil.writeInt32List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                    break;
                case 40:
                    SchemaUtil.writeFixed64List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                    break;
                case 41:
                    SchemaUtil.writeFixed32List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                    break;
                case 42:
                    SchemaUtil.writeBoolList(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                    break;
                case 43:
                    SchemaUtil.writeUInt32List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                    break;
                case 44:
                    SchemaUtil.writeEnumList(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                    break;
                case 45:
                    SchemaUtil.writeSFixed32List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                    break;
                case 46:
                    SchemaUtil.writeSFixed64List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                    break;
                case 47:
                    SchemaUtil.writeSInt32List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                    break;
                case 48:
                    SchemaUtil.writeSInt64List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                    break;
                case ConstraintLayout.LayoutParams.Table.LAYOUT_EDITOR_ABSOLUTEX /* 49 */:
                    SchemaUtil.writeGroupList(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, getMessageFieldSchema(pos));
                    break;
                case 50:
                    writeMapHelper(writer, number, UnsafeUtil.getObject(message, offset(typeAndOffset)), pos);
                    break;
                case 51:
                    if (isOneofPresent(message, number, pos)) {
                        writer.writeDouble(number, oneofDoubleAt(message, offset(typeAndOffset)));
                        break;
                    } else {
                        break;
                    }
                case 52:
                    if (isOneofPresent(message, number, pos)) {
                        writer.writeFloat(number, oneofFloatAt(message, offset(typeAndOffset)));
                        break;
                    } else {
                        break;
                    }
                case 53:
                    if (isOneofPresent(message, number, pos)) {
                        writer.writeInt64(number, oneofLongAt(message, offset(typeAndOffset)));
                        break;
                    } else {
                        break;
                    }
                case 54:
                    if (isOneofPresent(message, number, pos)) {
                        writer.writeUInt64(number, oneofLongAt(message, offset(typeAndOffset)));
                        break;
                    } else {
                        break;
                    }
                case 55:
                    if (isOneofPresent(message, number, pos)) {
                        writer.writeInt32(number, oneofIntAt(message, offset(typeAndOffset)));
                        break;
                    } else {
                        break;
                    }
                case 56:
                    if (isOneofPresent(message, number, pos)) {
                        writer.writeFixed64(number, oneofLongAt(message, offset(typeAndOffset)));
                        break;
                    } else {
                        break;
                    }
                case 57:
                    if (isOneofPresent(message, number, pos)) {
                        writer.writeFixed32(number, oneofIntAt(message, offset(typeAndOffset)));
                        break;
                    } else {
                        break;
                    }
                case 58:
                    if (isOneofPresent(message, number, pos)) {
                        writer.writeBool(number, oneofBooleanAt(message, offset(typeAndOffset)));
                        break;
                    } else {
                        break;
                    }
                case 59:
                    if (isOneofPresent(message, number, pos)) {
                        writeString(number, UnsafeUtil.getObject(message, offset(typeAndOffset)), writer);
                        break;
                    } else {
                        break;
                    }
                case LockFreeTaskQueueCore.FROZEN_SHIFT /* 60 */:
                    if (isOneofPresent(message, number, pos)) {
                        Object value2 = UnsafeUtil.getObject(message, offset(typeAndOffset));
                        writer.writeMessage(number, value2, getMessageFieldSchema(pos));
                        break;
                    } else {
                        break;
                    }
                case LockFreeTaskQueueCore.CLOSED_SHIFT /* 61 */:
                    if (isOneofPresent(message, number, pos)) {
                        writer.writeBytes(number, (ByteString) UnsafeUtil.getObject(message, offset(typeAndOffset)));
                        break;
                    } else {
                        break;
                    }
                case 62:
                    if (isOneofPresent(message, number, pos)) {
                        writer.writeUInt32(number, oneofIntAt(message, offset(typeAndOffset)));
                        break;
                    } else {
                        break;
                    }
                case 63:
                    if (isOneofPresent(message, number, pos)) {
                        writer.writeEnum(number, oneofIntAt(message, offset(typeAndOffset)));
                        break;
                    } else {
                        break;
                    }
                case 64:
                    if (isOneofPresent(message, number, pos)) {
                        writer.writeSFixed32(number, oneofIntAt(message, offset(typeAndOffset)));
                        break;
                    } else {
                        break;
                    }
                case 65:
                    if (isOneofPresent(message, number, pos)) {
                        writer.writeSFixed64(number, oneofLongAt(message, offset(typeAndOffset)));
                        break;
                    } else {
                        break;
                    }
                case 66:
                    if (isOneofPresent(message, number, pos)) {
                        writer.writeSInt32(number, oneofIntAt(message, offset(typeAndOffset)));
                        break;
                    } else {
                        break;
                    }
                case 67:
                    if (isOneofPresent(message, number, pos)) {
                        writer.writeSInt64(number, oneofLongAt(message, offset(typeAndOffset)));
                        break;
                    } else {
                        break;
                    }
                case 68:
                    if (isOneofPresent(message, number, pos)) {
                        writer.writeGroup(number, UnsafeUtil.getObject(message, offset(typeAndOffset)), getMessageFieldSchema(pos));
                        break;
                    } else {
                        break;
                    }
            }
        }
        while (nextExtension != null) {
            this.extensionSchema.serializeExtension(writer, nextExtension);
            nextExtension = extensionIterator.hasNext() ? extensionIterator.next() : null;
        }
        writeUnknownInMessageTo(this.unknownFieldSchema, message, writer);
    }

    private void writeFieldsInDescendingOrder(T message, Writer writer) throws IOException {
        writeUnknownInMessageTo(this.unknownFieldSchema, message, writer);
        Iterator<? extends Map.Entry<?, ?>> extensionIterator = null;
        Map.Entry nextExtension = null;
        if (this.hasExtensions) {
            FieldSet<?> extensions = this.extensionSchema.getExtensions(message);
            if (!extensions.isEmpty()) {
                extensionIterator = extensions.descendingIterator();
                nextExtension = extensionIterator.next();
            }
        }
        int pos = this.buffer.length;
        while (true) {
            pos -= 3;
            if (pos >= 0) {
                int typeAndOffset = typeAndOffsetAt(pos);
                int number = numberAt(pos);
                while (nextExtension != null && this.extensionSchema.extensionNumber(nextExtension) > number) {
                    this.extensionSchema.serializeExtension(writer, nextExtension);
                    nextExtension = extensionIterator.hasNext() ? extensionIterator.next() : null;
                }
                switch (type(typeAndOffset)) {
                    case 0:
                        if (!isFieldPresent(message, pos)) {
                            break;
                        } else {
                            writer.writeDouble(number, doubleAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case 1:
                        if (!isFieldPresent(message, pos)) {
                            break;
                        } else {
                            writer.writeFloat(number, floatAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case 2:
                        if (!isFieldPresent(message, pos)) {
                            break;
                        } else {
                            writer.writeInt64(number, longAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case 3:
                        if (!isFieldPresent(message, pos)) {
                            break;
                        } else {
                            writer.writeUInt64(number, longAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case 4:
                        if (!isFieldPresent(message, pos)) {
                            break;
                        } else {
                            writer.writeInt32(number, intAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case 5:
                        if (!isFieldPresent(message, pos)) {
                            break;
                        } else {
                            writer.writeFixed64(number, longAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case 6:
                        if (!isFieldPresent(message, pos)) {
                            break;
                        } else {
                            writer.writeFixed32(number, intAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case 7:
                        if (!isFieldPresent(message, pos)) {
                            break;
                        } else {
                            writer.writeBool(number, booleanAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case 8:
                        if (!isFieldPresent(message, pos)) {
                            break;
                        } else {
                            writeString(number, UnsafeUtil.getObject(message, offset(typeAndOffset)), writer);
                            break;
                        }
                    case 9:
                        if (!isFieldPresent(message, pos)) {
                            break;
                        } else {
                            Object value = UnsafeUtil.getObject(message, offset(typeAndOffset));
                            writer.writeMessage(number, value, getMessageFieldSchema(pos));
                            break;
                        }
                    case 10:
                        if (!isFieldPresent(message, pos)) {
                            break;
                        } else {
                            writer.writeBytes(number, (ByteString) UnsafeUtil.getObject(message, offset(typeAndOffset)));
                            break;
                        }
                    case 11:
                        if (!isFieldPresent(message, pos)) {
                            break;
                        } else {
                            writer.writeUInt32(number, intAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case 12:
                        if (!isFieldPresent(message, pos)) {
                            break;
                        } else {
                            writer.writeEnum(number, intAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case 13:
                        if (!isFieldPresent(message, pos)) {
                            break;
                        } else {
                            writer.writeSFixed32(number, intAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case 14:
                        if (!isFieldPresent(message, pos)) {
                            break;
                        } else {
                            writer.writeSFixed64(number, longAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case 15:
                        if (!isFieldPresent(message, pos)) {
                            break;
                        } else {
                            writer.writeSInt32(number, intAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case 16:
                        if (!isFieldPresent(message, pos)) {
                            break;
                        } else {
                            writer.writeSInt64(number, longAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case 17:
                        if (!isFieldPresent(message, pos)) {
                            break;
                        } else {
                            writer.writeGroup(number, UnsafeUtil.getObject(message, offset(typeAndOffset)), getMessageFieldSchema(pos));
                            break;
                        }
                    case 18:
                        SchemaUtil.writeDoubleList(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                        break;
                    case 19:
                        SchemaUtil.writeFloatList(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                        break;
                    case 20:
                        SchemaUtil.writeInt64List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                        break;
                    case 21:
                        SchemaUtil.writeUInt64List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                        break;
                    case 22:
                        SchemaUtil.writeInt32List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                        break;
                    case 23:
                        SchemaUtil.writeFixed64List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                        break;
                    case 24:
                        SchemaUtil.writeFixed32List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                        break;
                    case 25:
                        SchemaUtil.writeBoolList(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                        break;
                    case 26:
                        SchemaUtil.writeStringList(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer);
                        break;
                    case 27:
                        SchemaUtil.writeMessageList(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, getMessageFieldSchema(pos));
                        break;
                    case 28:
                        SchemaUtil.writeBytesList(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer);
                        break;
                    case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_HORIZONTAL_BIAS /* 29 */:
                        SchemaUtil.writeUInt32List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                        break;
                    case 30:
                        SchemaUtil.writeEnumList(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                        break;
                    case 31:
                        SchemaUtil.writeSFixed32List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                        break;
                    case 32:
                        SchemaUtil.writeSFixed64List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                        break;
                    case 33:
                        SchemaUtil.writeSInt32List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                        break;
                    case 34:
                        SchemaUtil.writeSInt64List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, false);
                        break;
                    case 35:
                        SchemaUtil.writeDoubleList(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                        break;
                    case 36:
                        SchemaUtil.writeFloatList(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                        break;
                    case 37:
                        SchemaUtil.writeInt64List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                        break;
                    case 38:
                        SchemaUtil.writeUInt64List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                        break;
                    case 39:
                        SchemaUtil.writeInt32List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                        break;
                    case 40:
                        SchemaUtil.writeFixed64List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                        break;
                    case 41:
                        SchemaUtil.writeFixed32List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                        break;
                    case 42:
                        SchemaUtil.writeBoolList(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                        break;
                    case 43:
                        SchemaUtil.writeUInt32List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                        break;
                    case 44:
                        SchemaUtil.writeEnumList(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                        break;
                    case 45:
                        SchemaUtil.writeSFixed32List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                        break;
                    case 46:
                        SchemaUtil.writeSFixed64List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                        break;
                    case 47:
                        SchemaUtil.writeSInt32List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                        break;
                    case 48:
                        SchemaUtil.writeSInt64List(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, true);
                        break;
                    case ConstraintLayout.LayoutParams.Table.LAYOUT_EDITOR_ABSOLUTEX /* 49 */:
                        SchemaUtil.writeGroupList(numberAt(pos), (List) UnsafeUtil.getObject(message, offset(typeAndOffset)), writer, getMessageFieldSchema(pos));
                        break;
                    case 50:
                        writeMapHelper(writer, number, UnsafeUtil.getObject(message, offset(typeAndOffset)), pos);
                        break;
                    case 51:
                        if (!isOneofPresent(message, number, pos)) {
                            break;
                        } else {
                            writer.writeDouble(number, oneofDoubleAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case 52:
                        if (!isOneofPresent(message, number, pos)) {
                            break;
                        } else {
                            writer.writeFloat(number, oneofFloatAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case 53:
                        if (!isOneofPresent(message, number, pos)) {
                            break;
                        } else {
                            writer.writeInt64(number, oneofLongAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case 54:
                        if (!isOneofPresent(message, number, pos)) {
                            break;
                        } else {
                            writer.writeUInt64(number, oneofLongAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case 55:
                        if (!isOneofPresent(message, number, pos)) {
                            break;
                        } else {
                            writer.writeInt32(number, oneofIntAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case 56:
                        if (!isOneofPresent(message, number, pos)) {
                            break;
                        } else {
                            writer.writeFixed64(number, oneofLongAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case 57:
                        if (!isOneofPresent(message, number, pos)) {
                            break;
                        } else {
                            writer.writeFixed32(number, oneofIntAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case 58:
                        if (!isOneofPresent(message, number, pos)) {
                            break;
                        } else {
                            writer.writeBool(number, oneofBooleanAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case 59:
                        if (!isOneofPresent(message, number, pos)) {
                            break;
                        } else {
                            writeString(number, UnsafeUtil.getObject(message, offset(typeAndOffset)), writer);
                            break;
                        }
                    case LockFreeTaskQueueCore.FROZEN_SHIFT /* 60 */:
                        if (!isOneofPresent(message, number, pos)) {
                            break;
                        } else {
                            Object value2 = UnsafeUtil.getObject(message, offset(typeAndOffset));
                            writer.writeMessage(number, value2, getMessageFieldSchema(pos));
                            break;
                        }
                    case LockFreeTaskQueueCore.CLOSED_SHIFT /* 61 */:
                        if (!isOneofPresent(message, number, pos)) {
                            break;
                        } else {
                            writer.writeBytes(number, (ByteString) UnsafeUtil.getObject(message, offset(typeAndOffset)));
                            break;
                        }
                    case 62:
                        if (!isOneofPresent(message, number, pos)) {
                            break;
                        } else {
                            writer.writeUInt32(number, oneofIntAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case 63:
                        if (!isOneofPresent(message, number, pos)) {
                            break;
                        } else {
                            writer.writeEnum(number, oneofIntAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case 64:
                        if (!isOneofPresent(message, number, pos)) {
                            break;
                        } else {
                            writer.writeSFixed32(number, oneofIntAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case 65:
                        if (!isOneofPresent(message, number, pos)) {
                            break;
                        } else {
                            writer.writeSFixed64(number, oneofLongAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case 66:
                        if (!isOneofPresent(message, number, pos)) {
                            break;
                        } else {
                            writer.writeSInt32(number, oneofIntAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case 67:
                        if (!isOneofPresent(message, number, pos)) {
                            break;
                        } else {
                            writer.writeSInt64(number, oneofLongAt(message, offset(typeAndOffset)));
                            break;
                        }
                    case 68:
                        if (!isOneofPresent(message, number, pos)) {
                            break;
                        } else {
                            writer.writeGroup(number, UnsafeUtil.getObject(message, offset(typeAndOffset)), getMessageFieldSchema(pos));
                            break;
                        }
                }
            } else {
                while (nextExtension != null) {
                    this.extensionSchema.serializeExtension(writer, nextExtension);
                    nextExtension = extensionIterator.hasNext() ? extensionIterator.next() : null;
                }
                return;
            }
        }
    }

    private <K, V> void writeMapHelper(Writer writer, int number, Object mapField, int pos) throws IOException {
        if (mapField != null) {
            writer.writeMap(number, this.mapFieldSchema.forMapMetadata(getMapFieldDefaultEntry(pos)), this.mapFieldSchema.forMapData(mapField));
        }
    }

    private <UT, UB> void writeUnknownInMessageTo(UnknownFieldSchema<UT, UB> schema, T message, Writer writer) throws IOException {
        schema.writeTo(schema.getFromMessage(message), writer);
    }

    @Override // androidx.datastore.preferences.protobuf.Schema
    public void mergeFrom(T message, Reader reader, ExtensionRegistryLite extensionRegistry) throws IOException {
        if (extensionRegistry == null) {
            throw new NullPointerException();
        }
        checkMutable(message);
        mergeFromHelper(this.unknownFieldSchema, this.extensionSchema, message, reader, extensionRegistry);
    }

    /*  JADX ERROR: Type inference failed
        jadx.core.utils.exceptions.JadxOverflowException: Type inference error: updates count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:77)
        */
    private <UT, UB, ET extends androidx.datastore.preferences.protobuf.FieldSet.FieldDescriptorLite<ET>> void mergeFromHelper(androidx.datastore.preferences.protobuf.UnknownFieldSchema<UT, UB> r17, androidx.datastore.preferences.protobuf.ExtensionSchema<ET> r18, T r19, androidx.datastore.preferences.protobuf.Reader r20, androidx.datastore.preferences.protobuf.ExtensionRegistryLite r21) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 2370
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.datastore.preferences.protobuf.MessageSchema.mergeFromHelper(androidx.datastore.preferences.protobuf.UnknownFieldSchema, androidx.datastore.preferences.protobuf.ExtensionSchema, java.lang.Object, androidx.datastore.preferences.protobuf.Reader, androidx.datastore.preferences.protobuf.ExtensionRegistryLite):void");
    }

    static UnknownFieldSetLite getMutableUnknownFields(Object message) {
        UnknownFieldSetLite unknownFields = ((GeneratedMessageLite) message).unknownFields;
        if (unknownFields == UnknownFieldSetLite.getDefaultInstance()) {
            UnknownFieldSetLite unknownFields2 = UnknownFieldSetLite.newInstance();
            ((GeneratedMessageLite) message).unknownFields = unknownFields2;
            return unknownFields2;
        }
        return unknownFields;
    }

    /* renamed from: androidx.datastore.preferences.protobuf.MessageSchema$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$WireFormat$FieldType = new int[WireFormat.FieldType.values().length];

        static {
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.BOOL.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.BYTES.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.DOUBLE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.FIXED32.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SFIXED32.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.FIXED64.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SFIXED64.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.FLOAT.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.ENUM.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.INT32.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.UINT32.ordinal()] = 11;
            } catch (NoSuchFieldError e11) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.INT64.ordinal()] = 12;
            } catch (NoSuchFieldError e12) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.UINT64.ordinal()] = 13;
            } catch (NoSuchFieldError e13) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.MESSAGE.ordinal()] = 14;
            } catch (NoSuchFieldError e14) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SINT32.ordinal()] = 15;
            } catch (NoSuchFieldError e15) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.SINT64.ordinal()] = 16;
            } catch (NoSuchFieldError e16) {
            }
            try {
                $SwitchMap$com$google$protobuf$WireFormat$FieldType[WireFormat.FieldType.STRING.ordinal()] = 17;
            } catch (NoSuchFieldError e17) {
            }
        }
    }

    private int decodeMapEntryValue(byte[] data, int position, int limit, WireFormat.FieldType fieldType, Class<?> messageType, ArrayDecoders.Registers registers) throws IOException {
        switch (AnonymousClass1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[fieldType.ordinal()]) {
            case 1:
                int position2 = ArrayDecoders.decodeVarint64(data, position, registers);
                registers.object1 = Boolean.valueOf(registers.long1 != 0);
                return position2;
            case 2:
                return ArrayDecoders.decodeBytes(data, position, registers);
            case 3:
                registers.object1 = Double.valueOf(ArrayDecoders.decodeDouble(data, position));
                return position + 8;
            case 4:
            case 5:
                registers.object1 = Integer.valueOf(ArrayDecoders.decodeFixed32(data, position));
                return position + 4;
            case 6:
            case 7:
                registers.object1 = Long.valueOf(ArrayDecoders.decodeFixed64(data, position));
                return position + 8;
            case 8:
                registers.object1 = Float.valueOf(ArrayDecoders.decodeFloat(data, position));
                return position + 4;
            case 9:
            case 10:
            case 11:
                int position3 = ArrayDecoders.decodeVarint32(data, position, registers);
                registers.object1 = Integer.valueOf(registers.int1);
                return position3;
            case 12:
            case 13:
                int position4 = ArrayDecoders.decodeVarint64(data, position, registers);
                registers.object1 = Long.valueOf(registers.long1);
                return position4;
            case 14:
                return ArrayDecoders.decodeMessageField(Protobuf.getInstance().schemaFor((Class) messageType), data, position, limit, registers);
            case 15:
                int position5 = ArrayDecoders.decodeVarint32(data, position, registers);
                registers.object1 = Integer.valueOf(CodedInputStream.decodeZigZag32(registers.int1));
                return position5;
            case 16:
                int position6 = ArrayDecoders.decodeVarint64(data, position, registers);
                registers.object1 = Long.valueOf(CodedInputStream.decodeZigZag64(registers.long1));
                return position6;
            case 17:
                return ArrayDecoders.decodeStringRequireUtf8(data, position, registers);
            default:
                throw new RuntimeException("unsupported field type.");
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private <K, V> int decodeMapEntry(byte[] data, int position, int limit, MapEntryLite.Metadata<K, V> metadata, Map<K, V> target, ArrayDecoders.Registers registers) throws IOException {
        int tag;
        byte[] bArr = data;
        int position2 = ArrayDecoders.decodeVarint32(bArr, position, registers);
        int length = registers.int1;
        if (length < 0 || length > limit - position2) {
            throw InvalidProtocolBufferException.truncatedMessage();
        }
        int end = position2 + length;
        K key = metadata.defaultKey;
        Object obj = key;
        Object obj2 = metadata.defaultValue;
        while (position2 < end) {
            int position3 = position2 + 1;
            int tag2 = bArr[position2];
            if (tag2 >= 0) {
                tag = tag2;
            } else {
                position3 = ArrayDecoders.decodeVarint32(tag2, bArr, position3, registers);
                tag = registers.int1;
            }
            int fieldNumber = tag >>> 3;
            int wireType = tag & 7;
            switch (fieldNumber) {
                case 1:
                    if (wireType != metadata.keyType.getWireType()) {
                        bArr = data;
                        position2 = ArrayDecoders.skipField(tag, bArr, position3, limit, registers);
                        break;
                    } else {
                        bArr = data;
                        int position4 = decodeMapEntryValue(bArr, position3, limit, metadata.keyType, null, registers);
                        obj = registers.object1;
                        position2 = position4;
                        break;
                    }
                case 2:
                    if (wireType != metadata.valueType.getWireType()) {
                        bArr = data;
                        position2 = ArrayDecoders.skipField(tag, bArr, position3, limit, registers);
                        break;
                    } else {
                        int position5 = decodeMapEntryValue(bArr, position3, limit, metadata.valueType, metadata.defaultValue.getClass(), registers);
                        obj2 = registers.object1;
                        bArr = data;
                        position2 = position5;
                        break;
                    }
                default:
                    position2 = ArrayDecoders.skipField(tag, bArr, position3, limit, registers);
                    break;
            }
        }
        if (position2 != end) {
            throw InvalidProtocolBufferException.parseFailure();
        }
        target.put(obj, obj2);
        return end;
    }

    private int parseRepeatedField(T message, byte[] data, int position, int limit, int tag, int number, int wireType, int bufferPosition, long typeAndOffset, int fieldType, long fieldOffset, ArrayDecoders.Registers registers) throws IOException {
        Internal.ProtobufList<?> list;
        int position2;
        Internal.ProtobufList<?> list2 = (Internal.ProtobufList) UNSAFE.getObject(message, fieldOffset);
        if (list2.isModifiable()) {
            list = list2;
        } else {
            int size = list2.size();
            Internal.ProtobufList<?> list3 = list2.mutableCopyWithCapacity2(size == 0 ? 10 : size * 2);
            UNSAFE.putObject(message, fieldOffset, list3);
            list = list3;
        }
        switch (fieldType) {
            case 18:
            case 35:
                if (wireType == 2) {
                    return ArrayDecoders.decodePackedDoubleList(data, position, list, registers);
                }
                if (wireType == 1) {
                    return ArrayDecoders.decodeDoubleList(tag, data, position, limit, list, registers);
                }
                break;
            case 19:
            case 36:
                if (wireType == 2) {
                    return ArrayDecoders.decodePackedFloatList(data, position, list, registers);
                }
                if (wireType != 5) {
                    break;
                } else {
                    return ArrayDecoders.decodeFloatList(tag, data, position, limit, list, registers);
                }
            case 20:
            case 21:
            case 37:
            case 38:
                if (wireType == 2) {
                    return ArrayDecoders.decodePackedVarint64List(data, position, list, registers);
                }
                if (wireType != 0) {
                    break;
                } else {
                    return ArrayDecoders.decodeVarint64List(tag, data, position, limit, list, registers);
                }
            case 22:
            case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_HORIZONTAL_BIAS /* 29 */:
            case 39:
            case 43:
                if (wireType == 2) {
                    return ArrayDecoders.decodePackedVarint32List(data, position, list, registers);
                }
                if (wireType == 0) {
                    return ArrayDecoders.decodeVarint32List(tag, data, position, limit, list, registers);
                }
                break;
            case 23:
            case 32:
            case 40:
            case 46:
                if (wireType == 2) {
                    return ArrayDecoders.decodePackedFixed64List(data, position, list, registers);
                }
                if (wireType != 1) {
                    break;
                } else {
                    return ArrayDecoders.decodeFixed64List(tag, data, position, limit, list, registers);
                }
            case 24:
            case 31:
            case 41:
            case 45:
                if (wireType == 2) {
                    return ArrayDecoders.decodePackedFixed32List(data, position, list, registers);
                }
                if (wireType != 5) {
                    break;
                } else {
                    return ArrayDecoders.decodeFixed32List(tag, data, position, limit, list, registers);
                }
            case 25:
            case 42:
                if (wireType == 2) {
                    return ArrayDecoders.decodePackedBoolList(data, position, list, registers);
                }
                if (wireType != 0) {
                    break;
                } else {
                    return ArrayDecoders.decodeBoolList(tag, data, position, limit, list, registers);
                }
            case 26:
                if (wireType != 2) {
                    break;
                } else {
                    if ((typeAndOffset & 536870912) == 0) {
                        return ArrayDecoders.decodeStringList(tag, data, position, limit, list, registers);
                    }
                    return ArrayDecoders.decodeStringListRequireUtf8(tag, data, position, limit, list, registers);
                }
            case 27:
                if (wireType == 2) {
                    return ArrayDecoders.decodeMessageList(getMessageFieldSchema(bufferPosition), tag, data, position, limit, list, registers);
                }
                break;
            case 28:
                if (wireType == 2) {
                    return ArrayDecoders.decodeBytesList(tag, data, position, limit, list, registers);
                }
                break;
            case 30:
            case 44:
                if (wireType == 2) {
                    position2 = ArrayDecoders.decodePackedVarint32List(data, position, list, registers);
                } else if (wireType == 0) {
                    Internal.ProtobufList<?> list4 = list;
                    list = list4;
                    position2 = ArrayDecoders.decodeVarint32List(tag, data, position, limit, list4, registers);
                }
                SchemaUtil.filterUnknownEnumList((Object) message, number, (List<Integer>) list, getEnumFieldVerifier(bufferPosition), (Object) null, (UnknownFieldSchema<UT, Object>) this.unknownFieldSchema);
                return position2;
            case 33:
            case 47:
                if (wireType == 2) {
                    return ArrayDecoders.decodePackedSInt32List(data, position, list, registers);
                }
                if (wireType == 0) {
                    return ArrayDecoders.decodeSInt32List(tag, data, position, limit, list, registers);
                }
                break;
            case 34:
            case 48:
                if (wireType == 2) {
                    return ArrayDecoders.decodePackedSInt64List(data, position, list, registers);
                }
                if (wireType != 0) {
                    break;
                } else {
                    return ArrayDecoders.decodeSInt64List(tag, data, position, limit, list, registers);
                }
            case ConstraintLayout.LayoutParams.Table.LAYOUT_EDITOR_ABSOLUTEX /* 49 */:
                if (wireType != 3) {
                    break;
                } else {
                    return ArrayDecoders.decodeGroupList(getMessageFieldSchema(bufferPosition), tag, data, position, limit, list, registers);
                }
        }
        return position;
    }

    private <K, V> int parseMapField(T message, byte[] data, int position, int limit, int bufferPosition, long fieldOffset, ArrayDecoders.Registers registers) throws IOException {
        Object mapField;
        Unsafe unsafe = UNSAFE;
        Object mapDefaultEntry = getMapFieldDefaultEntry(bufferPosition);
        Object mapField2 = unsafe.getObject(message, fieldOffset);
        if (!this.mapFieldSchema.isImmutable(mapField2)) {
            mapField = mapField2;
        } else {
            Object mapField3 = this.mapFieldSchema.newMapField(mapDefaultEntry);
            this.mapFieldSchema.mergeFrom(mapField3, mapField2);
            unsafe.putObject(message, fieldOffset, mapField3);
            mapField = mapField3;
        }
        return decodeMapEntry(data, position, limit, this.mapFieldSchema.forMapMetadata(mapDefaultEntry), this.mapFieldSchema.forMutableMapData(mapField), registers);
    }

    private int parseOneofField(T message, byte[] data, int position, int limit, int tag, int number, int wireType, int typeAndOffset, int fieldType, long fieldOffset, int bufferPosition, ArrayDecoders.Registers registers) throws IOException {
        int position2;
        Unsafe unsafe = UNSAFE;
        long oneofCaseOffset = this.buffer[bufferPosition + 2] & 1048575;
        switch (fieldType) {
            case 51:
                if (wireType != 1) {
                    return position;
                }
                unsafe.putObject(message, fieldOffset, Double.valueOf(ArrayDecoders.decodeDouble(data, position)));
                int position3 = position + 8;
                unsafe.putInt(message, oneofCaseOffset, number);
                return position3;
            case 52:
                if (wireType != 5) {
                    return position;
                }
                unsafe.putObject(message, fieldOffset, Float.valueOf(ArrayDecoders.decodeFloat(data, position)));
                int position4 = position + 4;
                unsafe.putInt(message, oneofCaseOffset, number);
                return position4;
            case 53:
            case 54:
                if (wireType != 0) {
                    return position;
                }
                int position5 = ArrayDecoders.decodeVarint64(data, position, registers);
                unsafe.putObject(message, fieldOffset, Long.valueOf(registers.long1));
                unsafe.putInt(message, oneofCaseOffset, number);
                return position5;
            case 55:
            case 62:
                if (wireType != 0) {
                    return position;
                }
                int position6 = ArrayDecoders.decodeVarint32(data, position, registers);
                unsafe.putObject(message, fieldOffset, Integer.valueOf(registers.int1));
                unsafe.putInt(message, oneofCaseOffset, number);
                return position6;
            case 56:
            case 65:
                if (wireType != 1) {
                    return position;
                }
                unsafe.putObject(message, fieldOffset, Long.valueOf(ArrayDecoders.decodeFixed64(data, position)));
                int position7 = position + 8;
                unsafe.putInt(message, oneofCaseOffset, number);
                return position7;
            case 57:
            case 64:
                if (wireType != 5) {
                    return position;
                }
                unsafe.putObject(message, fieldOffset, Integer.valueOf(ArrayDecoders.decodeFixed32(data, position)));
                int position8 = position + 4;
                unsafe.putInt(message, oneofCaseOffset, number);
                return position8;
            case 58:
                if (wireType != 0) {
                    return position;
                }
                int position9 = ArrayDecoders.decodeVarint64(data, position, registers);
                unsafe.putObject(message, fieldOffset, Boolean.valueOf(registers.long1 != 0));
                unsafe.putInt(message, oneofCaseOffset, number);
                return position9;
            case 59:
                if (wireType != 2) {
                    return position;
                }
                int position10 = ArrayDecoders.decodeVarint32(data, position, registers);
                int length = registers.int1;
                if (length == 0) {
                    unsafe.putObject(message, fieldOffset, "");
                } else {
                    if ((typeAndOffset & ENFORCE_UTF8_MASK) != 0 && !Utf8.isValidUtf8(data, position10, position10 + length)) {
                        throw InvalidProtocolBufferException.invalidUtf8();
                    }
                    String value = new String(data, position10, length, Internal.UTF_8);
                    unsafe.putObject(message, fieldOffset, value);
                    position10 += length;
                }
                unsafe.putInt(message, oneofCaseOffset, number);
                return position10;
            case LockFreeTaskQueueCore.FROZEN_SHIFT /* 60 */:
                if (wireType != 2) {
                    return position;
                }
                Object current = mutableOneofMessageFieldForMerge(message, number, bufferPosition);
                int position11 = ArrayDecoders.mergeMessageField(current, getMessageFieldSchema(bufferPosition), data, position, limit, registers);
                storeOneofMessageField(message, number, bufferPosition, current);
                return position11;
            case LockFreeTaskQueueCore.CLOSED_SHIFT /* 61 */:
                if (wireType != 2) {
                    return position;
                }
                int position12 = ArrayDecoders.decodeBytes(data, position, registers);
                unsafe.putObject(message, fieldOffset, registers.object1);
                unsafe.putInt(message, oneofCaseOffset, number);
                return position12;
            case 63:
                if (wireType != 0) {
                    return position;
                }
                int position13 = ArrayDecoders.decodeVarint32(data, position, registers);
                int enumValue = registers.int1;
                Internal.EnumVerifier enumVerifier = getEnumFieldVerifier(bufferPosition);
                if (enumVerifier == null) {
                    position2 = position13;
                } else {
                    if (!enumVerifier.isInRange(enumValue)) {
                        position2 = position13;
                        getMutableUnknownFields(message).storeField(tag, Long.valueOf(enumValue));
                        return position2;
                    }
                    position2 = position13;
                }
                unsafe.putObject(message, fieldOffset, Integer.valueOf(enumValue));
                unsafe.putInt(message, oneofCaseOffset, number);
                return position2;
            case 66:
                if (wireType != 0) {
                    return position;
                }
                int position14 = ArrayDecoders.decodeVarint32(data, position, registers);
                unsafe.putObject(message, fieldOffset, Integer.valueOf(CodedInputStream.decodeZigZag32(registers.int1)));
                unsafe.putInt(message, oneofCaseOffset, number);
                return position14;
            case 67:
                if (wireType != 0) {
                    return position;
                }
                int position15 = ArrayDecoders.decodeVarint64(data, position, registers);
                unsafe.putObject(message, fieldOffset, Long.valueOf(CodedInputStream.decodeZigZag64(registers.long1)));
                unsafe.putInt(message, oneofCaseOffset, number);
                return position15;
            case 68:
                if (wireType != 3) {
                    return position;
                }
                Object current2 = mutableOneofMessageFieldForMerge(message, number, bufferPosition);
                int endTag = (tag & (-8)) | 4;
                int position16 = ArrayDecoders.mergeGroupField(current2, getMessageFieldSchema(bufferPosition), data, position, limit, endTag, registers);
                storeOneofMessageField(message, number, bufferPosition, current2);
                return position16;
            default:
                return position;
        }
    }

    private Schema getMessageFieldSchema(int pos) {
        int index = (pos / 3) * 2;
        Schema schema = (Schema) this.objects[index];
        if (schema != null) {
            return schema;
        }
        Schema schema2 = Protobuf.getInstance().schemaFor((Class) this.objects[index + 1]);
        this.objects[index] = schema2;
        return schema2;
    }

    private Object getMapFieldDefaultEntry(int pos) {
        return this.objects[(pos / 3) * 2];
    }

    private Internal.EnumVerifier getEnumFieldVerifier(int pos) {
        return (Internal.EnumVerifier) this.objects[((pos / 3) * 2) + 1];
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:111:0x009b. Please report as an issue. */
    int parseProto2Message(T message, byte[] data, int position, int limit, int endGroup, ArrayDecoders.Registers registers) throws IOException {
        int i;
        T t;
        int i2;
        Unsafe unsafe;
        MessageSchema<T> messageSchema;
        int currentPresenceFieldOffset;
        int wireType;
        int tag;
        int pos;
        int position2;
        int tag2;
        int i3;
        int pos2;
        int position3;
        int currentPresenceField;
        int tag3;
        T t2;
        int tag4;
        int position4;
        int i4;
        int currentPresenceField2;
        int wireType2;
        int position5;
        int position6;
        int tag5;
        Unsafe unsafe2;
        int position7;
        int currentPresenceFieldOffset2;
        int currentPresenceFieldOffset3;
        int tag6;
        int wireType3;
        MessageSchema<T> messageSchema2 = this;
        T t3 = message;
        byte[] bArr = data;
        int endTag = limit;
        ArrayDecoders.Registers registers2 = registers;
        checkMutable(t3);
        Unsafe unsafe3 = UNSAFE;
        int tag7 = 0;
        int fieldType = 0;
        int wireType4 = 0;
        int tag8 = -1;
        int pos3 = 1048575;
        int position8 = position;
        while (true) {
            if (position8 >= endTag) {
                i = endGroup;
                t = t3;
                i2 = endTag;
                int currentPresenceFieldOffset4 = pos3;
                unsafe = unsafe3;
                int currentPresenceField3 = wireType4;
                messageSchema = messageSchema2;
                currentPresenceFieldOffset = tag7;
                wireType = currentPresenceFieldOffset4;
                tag = position8;
                pos = currentPresenceField3;
            } else {
                int position9 = position8 + 1;
                int tag9 = bArr[position8];
                if (tag9 >= 0) {
                    position2 = tag9;
                    tag2 = position9;
                } else {
                    int position10 = ArrayDecoders.decodeVarint32(tag9, bArr, position9, registers2);
                    int tag10 = registers2.int1;
                    position2 = tag10;
                    tag2 = position10;
                }
                int number = position2 >>> 3;
                int wireType5 = position2 & 7;
                if (number > tag8) {
                    i3 = 1048575;
                    int pos4 = messageSchema2.positionForFieldNumber(number, fieldType / 3);
                    pos2 = pos4;
                } else {
                    i3 = 1048575;
                    int pos5 = messageSchema2.positionForFieldNumber(number);
                    pos2 = pos5;
                }
                if (pos2 == -1) {
                    position3 = tag2;
                    fieldType = 0;
                    unsafe = unsafe3;
                    currentPresenceField = wireType4;
                    tag3 = position2;
                    messageSchema = messageSchema2;
                } else {
                    int typeAndOffset = messageSchema2.buffer[pos2 + 1];
                    int fieldType2 = type(typeAndOffset);
                    long fieldOffset = offset(typeAndOffset);
                    int position11 = tag2;
                    if (fieldType2 <= 17) {
                        int presenceMaskAndOffset = messageSchema2.buffer[pos2 + 2];
                        int presenceMask = 1 << (presenceMaskAndOffset >>> 20);
                        int presenceFieldOffset = presenceMaskAndOffset & i3;
                        if (presenceFieldOffset == pos3) {
                            currentPresenceField = wireType4;
                            currentPresenceField2 = pos3;
                        } else {
                            if (pos3 != i3) {
                                unsafe3.putInt(t3, pos3, wireType4);
                            }
                            currentPresenceField = unsafe3.getInt(t3, presenceFieldOffset);
                            currentPresenceField2 = presenceFieldOffset;
                        }
                        switch (fieldType2) {
                            case 0:
                                unsafe2 = unsafe3;
                                position6 = position11;
                                wireType2 = wireType5;
                                position5 = currentPresenceField2;
                                if (wireType2 != 1) {
                                    tag5 = position2;
                                    registers2 = registers;
                                    unsafe = unsafe2;
                                    tag3 = tag5;
                                    fieldType = pos2;
                                    position3 = position6;
                                    pos3 = position5;
                                    messageSchema = messageSchema2;
                                    break;
                                } else {
                                    int tag11 = position2;
                                    UnsafeUtil.putDouble(t3, fieldOffset, ArrayDecoders.decodeDouble(data, position6));
                                    tag7 = tag11;
                                    position8 = position6 + 8;
                                    endTag = limit;
                                    unsafe3 = unsafe2;
                                    bArr = data;
                                    wireType4 = currentPresenceField | presenceMask;
                                    fieldType = pos2;
                                    tag8 = number;
                                    pos3 = position5;
                                    registers2 = registers;
                                }
                            case 1:
                                unsafe2 = unsafe3;
                                position6 = position11;
                                wireType2 = wireType5;
                                position5 = currentPresenceField2;
                                ArrayDecoders.Registers registers3 = registers2;
                                if (wireType2 != 5) {
                                    tag5 = position2;
                                    registers2 = registers;
                                    unsafe = unsafe2;
                                    tag3 = tag5;
                                    fieldType = pos2;
                                    position3 = position6;
                                    pos3 = position5;
                                    messageSchema = messageSchema2;
                                    break;
                                } else {
                                    UnsafeUtil.putFloat(t3, fieldOffset, ArrayDecoders.decodeFloat(data, position6));
                                    position8 = position6 + 4;
                                    endTag = limit;
                                    unsafe3 = unsafe2;
                                    wireType4 = currentPresenceField | presenceMask;
                                    bArr = data;
                                    fieldType = pos2;
                                    tag7 = position2;
                                    registers2 = registers3;
                                    tag8 = number;
                                    pos3 = position5;
                                }
                            case 2:
                            case 3:
                                unsafe2 = unsafe3;
                                position6 = position11;
                                wireType2 = wireType5;
                                position5 = currentPresenceField2;
                                ArrayDecoders.Registers registers4 = registers2;
                                if (wireType2 == 0) {
                                    int position12 = ArrayDecoders.decodeVarint64(data, position6, registers4);
                                    T t4 = t3;
                                    unsafe2.putLong(t4, fieldOffset, registers4.long1);
                                    t3 = t4;
                                    endTag = limit;
                                    unsafe3 = unsafe2;
                                    wireType4 = currentPresenceField | presenceMask;
                                    bArr = data;
                                    position8 = position12;
                                    fieldType = pos2;
                                    tag7 = position2;
                                    registers2 = registers4;
                                    tag8 = number;
                                    pos3 = position5;
                                } else {
                                    tag5 = position2;
                                    registers2 = registers;
                                    unsafe = unsafe2;
                                    tag3 = tag5;
                                    fieldType = pos2;
                                    position3 = position6;
                                    pos3 = position5;
                                    messageSchema = messageSchema2;
                                    break;
                                }
                            case 4:
                            case 11:
                                unsafe2 = unsafe3;
                                position6 = position11;
                                wireType2 = wireType5;
                                position5 = currentPresenceField2;
                                ArrayDecoders.Registers registers5 = registers2;
                                if (wireType2 == 0) {
                                    int position13 = ArrayDecoders.decodeVarint32(data, position6, registers5);
                                    unsafe2.putInt(t3, fieldOffset, registers5.int1);
                                    endTag = limit;
                                    unsafe3 = unsafe2;
                                    position8 = position13;
                                    wireType4 = currentPresenceField | presenceMask;
                                    bArr = data;
                                    fieldType = pos2;
                                    tag7 = position2;
                                    registers2 = registers5;
                                    tag8 = number;
                                    pos3 = position5;
                                } else {
                                    tag5 = position2;
                                    registers2 = registers;
                                    unsafe = unsafe2;
                                    tag3 = tag5;
                                    fieldType = pos2;
                                    position3 = position6;
                                    pos3 = position5;
                                    messageSchema = messageSchema2;
                                    break;
                                }
                            case 5:
                            case 14:
                                ArrayDecoders.Registers registers6 = registers2;
                                Unsafe unsafe4 = unsafe3;
                                T t5 = t3;
                                wireType2 = wireType5;
                                position5 = currentPresenceField2;
                                if (wireType2 == 1) {
                                    unsafe4.putLong(t5, fieldOffset, ArrayDecoders.decodeFixed64(data, position11));
                                    t3 = t5;
                                    endTag = limit;
                                    unsafe3 = unsafe4;
                                    position8 = position11 + 8;
                                    wireType4 = currentPresenceField | presenceMask;
                                    bArr = data;
                                    fieldType = pos2;
                                    tag7 = position2;
                                    registers2 = registers6;
                                    tag8 = number;
                                    pos3 = position5;
                                } else {
                                    position6 = position11;
                                    unsafe2 = unsafe4;
                                    tag5 = position2;
                                    registers2 = registers;
                                    unsafe = unsafe2;
                                    tag3 = tag5;
                                    fieldType = pos2;
                                    position3 = position6;
                                    pos3 = position5;
                                    messageSchema = messageSchema2;
                                    break;
                                }
                            case 6:
                            case 13:
                                ArrayDecoders.Registers registers7 = registers2;
                                Unsafe unsafe5 = unsafe3;
                                T t6 = t3;
                                wireType2 = wireType5;
                                position5 = currentPresenceField2;
                                if (wireType2 == 5) {
                                    unsafe5.putInt(t6, fieldOffset, ArrayDecoders.decodeFixed32(data, position11));
                                    position8 = position11 + 4;
                                    int position14 = currentPresenceField | presenceMask;
                                    wireType4 = position14;
                                    unsafe3 = unsafe5;
                                    fieldType = pos2;
                                    tag7 = position2;
                                    tag8 = number;
                                    pos3 = position5;
                                    bArr = data;
                                    t3 = t6;
                                    registers2 = registers7;
                                    endTag = limit;
                                } else {
                                    tag5 = position2;
                                    position6 = position11;
                                    unsafe2 = unsafe5;
                                    registers2 = registers;
                                    unsafe = unsafe2;
                                    tag3 = tag5;
                                    fieldType = pos2;
                                    position3 = position6;
                                    pos3 = position5;
                                    messageSchema = messageSchema2;
                                    break;
                                }
                            case 7:
                                ArrayDecoders.Registers registers8 = registers2;
                                Unsafe unsafe6 = unsafe3;
                                T t7 = t3;
                                wireType2 = wireType5;
                                position5 = currentPresenceField2;
                                if (wireType2 == 0) {
                                    position8 = ArrayDecoders.decodeVarint64(data, position11, registers8);
                                    UnsafeUtil.putBoolean(t7, fieldOffset, registers8.long1 != 0);
                                    wireType4 = currentPresenceField | presenceMask;
                                    unsafe3 = unsafe6;
                                    fieldType = pos2;
                                    tag7 = position2;
                                    tag8 = number;
                                    pos3 = position5;
                                    bArr = data;
                                    t3 = t7;
                                    registers2 = registers8;
                                    endTag = limit;
                                } else {
                                    tag5 = position2;
                                    position6 = position11;
                                    unsafe2 = unsafe6;
                                    registers2 = registers;
                                    unsafe = unsafe2;
                                    tag3 = tag5;
                                    fieldType = pos2;
                                    position3 = position6;
                                    pos3 = position5;
                                    messageSchema = messageSchema2;
                                    break;
                                }
                            case 8:
                                ArrayDecoders.Registers registers9 = registers2;
                                Unsafe unsafe7 = unsafe3;
                                T t8 = t3;
                                wireType2 = wireType5;
                                position5 = currentPresenceField2;
                                if (wireType2 == 2) {
                                    if ((ENFORCE_UTF8_MASK & typeAndOffset) == 0) {
                                        position7 = ArrayDecoders.decodeString(data, position11, registers9);
                                    } else {
                                        position7 = ArrayDecoders.decodeStringRequireUtf8(data, position11, registers9);
                                    }
                                    position8 = position7;
                                    unsafe7.putObject(t8, fieldOffset, registers9.object1);
                                    wireType4 = currentPresenceField | presenceMask;
                                    unsafe3 = unsafe7;
                                    fieldType = pos2;
                                    tag7 = position2;
                                    tag8 = number;
                                    pos3 = position5;
                                    bArr = data;
                                    t3 = t8;
                                    registers2 = registers9;
                                    endTag = limit;
                                } else {
                                    tag5 = position2;
                                    position6 = position11;
                                    unsafe2 = unsafe7;
                                    registers2 = registers;
                                    unsafe = unsafe2;
                                    tag3 = tag5;
                                    fieldType = pos2;
                                    position3 = position6;
                                    pos3 = position5;
                                    messageSchema = messageSchema2;
                                    break;
                                }
                            case 9:
                                ArrayDecoders.Registers registers10 = registers2;
                                Unsafe unsafe8 = unsafe3;
                                wireType2 = wireType5;
                                position5 = currentPresenceField2;
                                if (wireType2 == 2) {
                                    T t9 = t3;
                                    Object current = messageSchema2.mutableMessageFieldForMerge(t9, pos2);
                                    int position15 = ArrayDecoders.mergeMessageField(current, messageSchema2.getMessageFieldSchema(pos2), data, position11, limit, registers10);
                                    messageSchema2.storeMessageField(t9, pos2, current);
                                    position8 = position15;
                                    wireType4 = currentPresenceField | presenceMask;
                                    unsafe3 = unsafe8;
                                    fieldType = pos2;
                                    tag7 = position2;
                                    tag8 = number;
                                    pos3 = position5;
                                    bArr = data;
                                    t3 = t9;
                                    registers2 = registers10;
                                    endTag = limit;
                                } else {
                                    tag5 = position2;
                                    position6 = position11;
                                    unsafe2 = unsafe8;
                                    registers2 = registers;
                                    unsafe = unsafe2;
                                    tag3 = tag5;
                                    fieldType = pos2;
                                    position3 = position6;
                                    pos3 = position5;
                                    messageSchema = messageSchema2;
                                    break;
                                }
                            case 10:
                                ArrayDecoders.Registers registers11 = registers2;
                                unsafe2 = unsafe3;
                                wireType2 = wireType5;
                                position5 = currentPresenceField2;
                                if (wireType2 != 2) {
                                    position6 = position11;
                                    tag5 = position2;
                                    registers2 = registers;
                                    unsafe = unsafe2;
                                    tag3 = tag5;
                                    fieldType = pos2;
                                    position3 = position6;
                                    pos3 = position5;
                                    messageSchema = messageSchema2;
                                    break;
                                } else {
                                    position8 = ArrayDecoders.decodeBytes(data, position11, registers11);
                                    unsafe2.putObject(t3, fieldOffset, registers11.object1);
                                    endTag = limit;
                                    unsafe3 = unsafe2;
                                    wireType4 = currentPresenceField | presenceMask;
                                    bArr = data;
                                    registers2 = registers11;
                                    fieldType = pos2;
                                    tag7 = position2;
                                    tag8 = number;
                                    pos3 = position5;
                                }
                            case 12:
                                ArrayDecoders.Registers registers12 = registers2;
                                unsafe2 = unsafe3;
                                wireType2 = wireType5;
                                position5 = currentPresenceField2;
                                if (wireType2 == 0) {
                                    int position16 = ArrayDecoders.decodeVarint32(data, position11, registers12);
                                    int enumValue = registers12.int1;
                                    Internal.EnumVerifier enumVerifier = messageSchema2.getEnumFieldVerifier(pos2);
                                    if (enumVerifier == null || enumVerifier.isInRange(enumValue)) {
                                        unsafe2.putInt(t3, fieldOffset, enumValue);
                                        endTag = limit;
                                        unsafe3 = unsafe2;
                                        wireType4 = currentPresenceField | presenceMask;
                                        bArr = data;
                                        registers2 = registers12;
                                        fieldType = pos2;
                                        tag7 = position2;
                                        tag8 = number;
                                        pos3 = position5;
                                        position8 = position16;
                                    } else {
                                        getMutableUnknownFields(t3).storeField(position2, Long.valueOf(enumValue));
                                        position8 = position16;
                                        endTag = limit;
                                        unsafe3 = unsafe2;
                                        bArr = data;
                                        registers2 = registers12;
                                        fieldType = pos2;
                                        tag7 = position2;
                                        tag8 = number;
                                        pos3 = position5;
                                        wireType4 = currentPresenceField;
                                    }
                                } else {
                                    position6 = position11;
                                    tag5 = position2;
                                    registers2 = registers;
                                    unsafe = unsafe2;
                                    tag3 = tag5;
                                    fieldType = pos2;
                                    position3 = position6;
                                    pos3 = position5;
                                    messageSchema = messageSchema2;
                                    break;
                                }
                                break;
                            case 15:
                                ArrayDecoders.Registers registers13 = registers2;
                                unsafe2 = unsafe3;
                                wireType2 = wireType5;
                                position5 = currentPresenceField2;
                                if (wireType2 != 0) {
                                    position6 = position11;
                                    tag5 = position2;
                                    registers2 = registers;
                                    unsafe = unsafe2;
                                    tag3 = tag5;
                                    fieldType = pos2;
                                    position3 = position6;
                                    pos3 = position5;
                                    messageSchema = messageSchema2;
                                    break;
                                } else {
                                    position8 = ArrayDecoders.decodeVarint32(data, position11, registers13);
                                    unsafe2.putInt(t3, fieldOffset, CodedInputStream.decodeZigZag32(registers13.int1));
                                    endTag = limit;
                                    unsafe3 = unsafe2;
                                    wireType4 = currentPresenceField | presenceMask;
                                    bArr = data;
                                    registers2 = registers13;
                                    fieldType = pos2;
                                    tag7 = position2;
                                    tag8 = number;
                                    pos3 = position5;
                                }
                            case 16:
                                ArrayDecoders.Registers registers14 = registers2;
                                Unsafe unsafe9 = unsafe3;
                                wireType2 = wireType5;
                                position5 = currentPresenceField2;
                                if (wireType2 == 0) {
                                    int position17 = ArrayDecoders.decodeVarint64(data, position11, registers14);
                                    T t10 = t3;
                                    unsafe9.putLong(t10, fieldOffset, CodedInputStream.decodeZigZag64(registers14.long1));
                                    t3 = t10;
                                    endTag = limit;
                                    unsafe3 = unsafe9;
                                    wireType4 = currentPresenceField | presenceMask;
                                    bArr = data;
                                    registers2 = registers14;
                                    fieldType = pos2;
                                    tag7 = position2;
                                    tag8 = number;
                                    pos3 = position5;
                                    position8 = position17;
                                } else {
                                    unsafe2 = unsafe9;
                                    position6 = position11;
                                    tag5 = position2;
                                    registers2 = registers;
                                    unsafe = unsafe2;
                                    tag3 = tag5;
                                    fieldType = pos2;
                                    position3 = position6;
                                    pos3 = position5;
                                    messageSchema = messageSchema2;
                                    break;
                                }
                            case 17:
                                if (wireType5 == 3) {
                                    Object current2 = messageSchema2.mutableMessageFieldForMerge(t3, pos2);
                                    int endTag2 = (number << 3) | 4;
                                    int currentPresenceFieldOffset5 = currentPresenceField2;
                                    position8 = ArrayDecoders.mergeGroupField(current2, messageSchema2.getMessageFieldSchema(pos2), data, position11, limit, endTag2, registers);
                                    messageSchema2.storeMessageField(t3, pos2, current2);
                                    endTag = limit;
                                    wireType4 = currentPresenceField | presenceMask;
                                    bArr = data;
                                    registers2 = registers;
                                    fieldType = pos2;
                                    tag7 = position2;
                                    tag8 = number;
                                    pos3 = currentPresenceFieldOffset5;
                                    unsafe3 = unsafe3;
                                } else {
                                    Unsafe unsafe10 = unsafe3;
                                    wireType2 = wireType5;
                                    position5 = currentPresenceField2;
                                    position6 = position11;
                                    tag5 = position2;
                                    unsafe2 = unsafe10;
                                    registers2 = registers;
                                    unsafe = unsafe2;
                                    tag3 = tag5;
                                    fieldType = pos2;
                                    position3 = position6;
                                    pos3 = position5;
                                    messageSchema = messageSchema2;
                                    break;
                                }
                            default:
                                unsafe2 = unsafe3;
                                tag5 = position2;
                                position6 = position11;
                                wireType2 = wireType5;
                                position5 = currentPresenceField2;
                                registers2 = registers;
                                unsafe = unsafe2;
                                tag3 = tag5;
                                fieldType = pos2;
                                position3 = position6;
                                pos3 = position5;
                                messageSchema = messageSchema2;
                                break;
                        }
                    } else {
                        Unsafe unsafe11 = unsafe3;
                        currentPresenceField = wireType4;
                        int tag12 = position2;
                        if (fieldType2 == 27) {
                            if (wireType5 != 2) {
                                currentPresenceFieldOffset2 = pos3;
                                currentPresenceFieldOffset3 = pos2;
                                tag6 = position11;
                                unsafe = unsafe11;
                                wireType3 = tag12;
                                registers2 = registers;
                                position3 = tag6;
                                fieldType = currentPresenceFieldOffset3;
                                tag3 = wireType3;
                                pos3 = currentPresenceFieldOffset2;
                                messageSchema = this;
                            } else {
                                Internal.ProtobufList<?> list = (Internal.ProtobufList) unsafe11.getObject(t3, fieldOffset);
                                if (!list.isModifiable()) {
                                    int size = list.size();
                                    list = list.mutableCopyWithCapacity2(size == 0 ? 10 : size * 2);
                                    unsafe11.putObject(t3, fieldOffset, list);
                                }
                                int currentPresenceFieldOffset6 = pos3;
                                int currentPresenceFieldOffset7 = pos2;
                                registers2 = registers;
                                bArr = data;
                                endTag = limit;
                                position8 = ArrayDecoders.decodeMessageList(messageSchema2.getMessageFieldSchema(pos2), tag12, data, position11, limit, list, registers2);
                                tag7 = tag12;
                                fieldType = currentPresenceFieldOffset7;
                                unsafe3 = unsafe11;
                                tag8 = number;
                                pos3 = currentPresenceFieldOffset6;
                                wireType4 = currentPresenceField;
                                t3 = message;
                            }
                        } else {
                            currentPresenceFieldOffset2 = pos3;
                            currentPresenceFieldOffset3 = pos2;
                            tag6 = position11;
                            if (fieldType2 <= 49) {
                                unsafe = unsafe11;
                                position8 = messageSchema2.parseRepeatedField(message, data, tag6, limit, tag12, number, wireType5, currentPresenceFieldOffset3, typeAndOffset, fieldType2, fieldOffset, registers);
                                if (position8 != tag6) {
                                    messageSchema2 = this;
                                    t3 = message;
                                    bArr = data;
                                    endTag = limit;
                                    registers2 = registers;
                                    fieldType = currentPresenceFieldOffset3;
                                    tag7 = tag12;
                                    tag8 = number;
                                    pos3 = currentPresenceFieldOffset2;
                                    wireType4 = currentPresenceField;
                                    unsafe3 = unsafe;
                                } else {
                                    registers2 = registers;
                                    position3 = position8;
                                    fieldType = currentPresenceFieldOffset3;
                                    tag3 = tag12;
                                    pos3 = currentPresenceFieldOffset2;
                                    messageSchema = this;
                                }
                            } else {
                                unsafe = unsafe11;
                                wireType3 = tag12;
                                if (fieldType2 == 50) {
                                    if (wireType5 == 2) {
                                        position8 = parseMapField(message, data, tag6, limit, currentPresenceFieldOffset3, fieldOffset, registers);
                                        if (position8 != tag6) {
                                            messageSchema2 = this;
                                            t3 = message;
                                            bArr = data;
                                            endTag = limit;
                                            registers2 = registers;
                                            fieldType = currentPresenceFieldOffset3;
                                            tag7 = wireType3;
                                            tag8 = number;
                                            pos3 = currentPresenceFieldOffset2;
                                            wireType4 = currentPresenceField;
                                            unsafe3 = unsafe;
                                        } else {
                                            registers2 = registers;
                                            position3 = position8;
                                            fieldType = currentPresenceFieldOffset3;
                                            tag3 = wireType3;
                                            pos3 = currentPresenceFieldOffset2;
                                            messageSchema = this;
                                        }
                                    } else {
                                        registers2 = registers;
                                        position3 = tag6;
                                        fieldType = currentPresenceFieldOffset3;
                                        tag3 = wireType3;
                                        pos3 = currentPresenceFieldOffset2;
                                        messageSchema = this;
                                    }
                                } else {
                                    tag3 = wireType3;
                                    position8 = parseOneofField(message, data, tag6, limit, tag3, number, wireType5, typeAndOffset, fieldType2, fieldOffset, currentPresenceFieldOffset3, registers);
                                    messageSchema = this;
                                    registers2 = registers;
                                    if (position8 == tag6) {
                                        position3 = position8;
                                        fieldType = currentPresenceFieldOffset3;
                                        pos3 = currentPresenceFieldOffset2;
                                    } else {
                                        t3 = message;
                                        bArr = data;
                                        endTag = limit;
                                        tag7 = tag3;
                                        fieldType = currentPresenceFieldOffset3;
                                        messageSchema2 = messageSchema;
                                        tag8 = number;
                                        pos3 = currentPresenceFieldOffset2;
                                        wireType4 = currentPresenceField;
                                        unsafe3 = unsafe;
                                    }
                                }
                            }
                        }
                    }
                }
                i = endGroup;
                if (tag3 == i && i != 0) {
                    t = message;
                    i2 = limit;
                    tag = position3;
                    wireType = pos3;
                    currentPresenceFieldOffset = tag3;
                    pos = currentPresenceField;
                } else {
                    if (!messageSchema.hasExtensions) {
                        t2 = message;
                    } else if (registers2.extensionRegistry == ExtensionRegistryLite.getEmptyRegistry()) {
                        t2 = message;
                    } else {
                        tag4 = tag3;
                        position4 = ArrayDecoders.decodeExtensionOrUnknownField(tag4, data, position3, limit, message, messageSchema.defaultInstance, messageSchema.unknownFieldSchema, registers2);
                        t2 = message;
                        i4 = limit;
                        position8 = position4;
                        bArr = data;
                        tag7 = tag4;
                        t3 = t2;
                        messageSchema2 = messageSchema;
                        endTag = i4;
                        tag8 = number;
                        wireType4 = currentPresenceField;
                        unsafe3 = unsafe;
                        registers2 = registers;
                    }
                    tag4 = tag3;
                    position4 = ArrayDecoders.decodeUnknownField(tag4, data, position3, limit, getMutableUnknownFields(t2), registers);
                    i4 = limit;
                    position8 = position4;
                    bArr = data;
                    tag7 = tag4;
                    t3 = t2;
                    messageSchema2 = messageSchema;
                    endTag = i4;
                    tag8 = number;
                    wireType4 = currentPresenceField;
                    unsafe3 = unsafe;
                    registers2 = registers;
                }
            }
        }
        if (wireType != 1048575) {
            unsafe.putInt(t, wireType, pos);
        }
        UnknownFieldSetLite unknownFields = null;
        for (int i5 = messageSchema.checkInitializedCount; i5 < messageSchema.repeatedFieldOffsetStart; i5++) {
            T t11 = t;
            unknownFields = (UnknownFieldSetLite) messageSchema.filterMapUnknownEnumValues(t11, messageSchema.intArray[i5], unknownFields, messageSchema.unknownFieldSchema, message);
            t = t11;
        }
        T t12 = t;
        MessageSchema<T> messageSchema3 = messageSchema;
        if (unknownFields != null) {
            messageSchema3.unknownFieldSchema.setBuilderToMessage(t12, unknownFields);
        }
        if (i == 0) {
            if (tag != i2) {
                throw InvalidProtocolBufferException.parseFailure();
            }
        } else if (tag > i2 || currentPresenceFieldOffset != i) {
            throw InvalidProtocolBufferException.parseFailure();
        }
        return tag;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private Object mutableMessageFieldForMerge(T message, int pos) {
        Schema messageFieldSchema = getMessageFieldSchema(pos);
        long offset = offset(typeAndOffsetAt(pos));
        if (!isFieldPresent(message, pos)) {
            return messageFieldSchema.newInstance();
        }
        Object current = UNSAFE.getObject(message, offset);
        if (isMutable(current)) {
            return current;
        }
        Object newMessage = messageFieldSchema.newInstance();
        if (current != null) {
            messageFieldSchema.mergeFrom(newMessage, current);
        }
        return newMessage;
    }

    private void storeMessageField(T message, int pos, Object field) {
        UNSAFE.putObject(message, offset(typeAndOffsetAt(pos)), field);
        setFieldPresent(message, pos);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private Object mutableOneofMessageFieldForMerge(T message, int fieldNumber, int pos) {
        Schema messageFieldSchema = getMessageFieldSchema(pos);
        if (!isOneofPresent(message, fieldNumber, pos)) {
            return messageFieldSchema.newInstance();
        }
        Object current = UNSAFE.getObject(message, offset(typeAndOffsetAt(pos)));
        if (isMutable(current)) {
            return current;
        }
        Object newMessage = messageFieldSchema.newInstance();
        if (current != null) {
            messageFieldSchema.mergeFrom(newMessage, current);
        }
        return newMessage;
    }

    private void storeOneofMessageField(T message, int fieldNumber, int pos, Object field) {
        UNSAFE.putObject(message, offset(typeAndOffsetAt(pos)), field);
        setOneofPresent(message, fieldNumber, pos);
    }

    private int parseProto3Message(T message, byte[] data, int position, int limit, ArrayDecoders.Registers registers) throws IOException {
        int position2;
        int tag;
        int pos;
        Unsafe unsafe;
        int position3;
        int tag2;
        T t;
        int pos2;
        int fieldType;
        int position4;
        int number;
        Unsafe unsafe2;
        int position5;
        int position6;
        int currentPresenceField;
        int wireType;
        int typeAndOffset;
        int currentPresenceField2;
        int number2;
        int fieldType2;
        MessageSchema<T> messageSchema = this;
        T t2 = message;
        byte[] bArr = data;
        int position7 = limit;
        ArrayDecoders.Registers registers2 = registers;
        checkMutable(t2);
        Unsafe unsafe3 = UNSAFE;
        int number3 = -1;
        int currentPresenceField3 = 0;
        int currentPresenceField4 = 0;
        int currentPresenceFieldOffset = 1048575;
        int position8 = position;
        while (position8 < position7) {
            int position9 = position8 + 1;
            int tag3 = bArr[position8];
            if (tag3 >= 0) {
                position2 = tag3;
                tag = position9;
            } else {
                int position10 = ArrayDecoders.decodeVarint32(tag3, bArr, position9, registers2);
                position2 = registers2.int1;
                tag = position10;
            }
            int number4 = position2 >>> 3;
            int wireType2 = position2 & 7;
            if (number4 > number3) {
                pos = messageSchema.positionForFieldNumber(number4, currentPresenceField4 / 3);
            } else {
                pos = messageSchema.positionForFieldNumber(number4);
            }
            if (pos == -1) {
                unsafe = unsafe3;
                position3 = tag;
                tag2 = position2;
                t = t2;
                pos2 = 0;
            } else {
                int typeAndOffset2 = messageSchema.buffer[pos + 1];
                int tag4 = position2;
                int fieldType3 = type(typeAndOffset2);
                long fieldOffset = offset(typeAndOffset2);
                if (fieldType3 <= 17) {
                    int presenceMaskAndOffset = messageSchema.buffer[pos + 2];
                    int presenceMask = 1 << (presenceMaskAndOffset >>> 20);
                    int presenceFieldOffset = presenceMaskAndOffset & 1048575;
                    if (presenceFieldOffset == currentPresenceFieldOffset) {
                        fieldType = fieldType3;
                    } else {
                        if (currentPresenceFieldOffset == 1048575) {
                            fieldType = fieldType3;
                        } else {
                            fieldType = fieldType3;
                            unsafe3.putInt(t2, currentPresenceFieldOffset, currentPresenceField3);
                        }
                        if (presenceFieldOffset != 1048575) {
                            currentPresenceField3 = unsafe3.getInt(t2, presenceFieldOffset);
                        }
                        currentPresenceFieldOffset = presenceFieldOffset;
                    }
                    switch (fieldType) {
                        case 0:
                            position4 = tag;
                            pos2 = pos;
                            t = t2;
                            unsafe2 = unsafe3;
                            number = number4;
                            if (wireType2 == 1) {
                                UnsafeUtil.putDouble(t, fieldOffset, ArrayDecoders.decodeDouble(bArr, position4));
                                int position11 = position4 + 8;
                                currentPresenceField3 |= presenceMask;
                                position7 = limit;
                                unsafe3 = unsafe2;
                                position8 = position11;
                                currentPresenceField4 = pos2;
                                t2 = t;
                                number3 = number4;
                                break;
                            } else {
                                unsafe = unsafe2;
                                position3 = position4;
                                tag2 = tag4;
                                break;
                            }
                        case 1:
                            position4 = tag;
                            pos2 = pos;
                            t = t2;
                            unsafe2 = unsafe3;
                            number = number4;
                            if (wireType2 == 5) {
                                UnsafeUtil.putFloat(t, fieldOffset, ArrayDecoders.decodeFloat(bArr, position4));
                                int position12 = position4 + 4;
                                currentPresenceField3 |= presenceMask;
                                position7 = limit;
                                unsafe3 = unsafe2;
                                position8 = position12;
                                currentPresenceField4 = pos2;
                                t2 = t;
                                number3 = number4;
                                break;
                            } else {
                                unsafe = unsafe2;
                                position3 = position4;
                                tag2 = tag4;
                                break;
                            }
                        case 2:
                        case 3:
                            position4 = tag;
                            pos2 = pos;
                            number = number4;
                            if (wireType2 != 0) {
                                t = t2;
                                unsafe2 = unsafe3;
                                unsafe = unsafe2;
                                position3 = position4;
                                tag2 = tag4;
                                break;
                            } else {
                                int position13 = ArrayDecoders.decodeVarint64(bArr, position4, registers2);
                                Unsafe unsafe4 = unsafe3;
                                T t3 = t2;
                                unsafe4.putLong(t3, fieldOffset, registers2.long1);
                                currentPresenceField3 |= presenceMask;
                                unsafe3 = unsafe4;
                                position8 = position13;
                                currentPresenceField4 = pos2;
                                t2 = t3;
                                number3 = number4;
                                position7 = limit;
                                break;
                            }
                        case 4:
                        case 11:
                            position4 = tag;
                            pos2 = pos;
                            number = number4;
                            if (wireType2 != 0) {
                                t = t2;
                                unsafe2 = unsafe3;
                                unsafe = unsafe2;
                                position3 = position4;
                                tag2 = tag4;
                                break;
                            } else {
                                int position14 = ArrayDecoders.decodeVarint32(bArr, position4, registers2);
                                unsafe3.putInt(t2, fieldOffset, registers2.int1);
                                currentPresenceField3 |= presenceMask;
                                position7 = limit;
                                position8 = position14;
                                currentPresenceField4 = pos2;
                                number3 = number4;
                                break;
                            }
                        case 5:
                        case 14:
                            byte[] bArr2 = bArr;
                            T t4 = t2;
                            Unsafe unsafe5 = unsafe3;
                            int position15 = tag;
                            pos2 = pos;
                            ArrayDecoders.Registers registers3 = registers2;
                            number = number4;
                            if (wireType2 != 1) {
                                position4 = position15;
                                t = t4;
                                unsafe2 = unsafe5;
                                unsafe = unsafe2;
                                position3 = position4;
                                tag2 = tag4;
                                break;
                            } else {
                                bArr = bArr2;
                                registers2 = registers3;
                                unsafe5.putLong(t4, fieldOffset, ArrayDecoders.decodeFixed64(bArr2, position15));
                                unsafe3 = unsafe5;
                                t2 = t4;
                                currentPresenceField3 |= presenceMask;
                                position7 = limit;
                                position8 = position15 + 8;
                                currentPresenceField4 = pos2;
                                number3 = number4;
                                break;
                            }
                        case 6:
                        case 13:
                            byte[] bArr3 = bArr;
                            T t5 = t2;
                            Unsafe unsafe6 = unsafe3;
                            int position16 = tag;
                            pos2 = pos;
                            ArrayDecoders.Registers registers4 = registers2;
                            number = number4;
                            if (wireType2 != 5) {
                                unsafe2 = unsafe6;
                                position4 = position16;
                                t = t5;
                                unsafe = unsafe2;
                                position3 = position4;
                                tag2 = tag4;
                                break;
                            } else {
                                unsafe6.putInt(t5, fieldOffset, ArrayDecoders.decodeFixed32(bArr3, position16));
                                currentPresenceField3 |= presenceMask;
                                bArr = bArr3;
                                t2 = t5;
                                registers2 = registers4;
                                currentPresenceField4 = pos2;
                                number3 = number4;
                                position8 = position16 + 4;
                                unsafe3 = unsafe6;
                                position7 = limit;
                                break;
                            }
                        case 7:
                            byte[] bArr4 = bArr;
                            T t6 = t2;
                            Unsafe unsafe7 = unsafe3;
                            int position17 = tag;
                            pos2 = pos;
                            ArrayDecoders.Registers registers5 = registers2;
                            number = number4;
                            if (wireType2 != 0) {
                                unsafe2 = unsafe7;
                                position4 = position17;
                                t = t6;
                                unsafe = unsafe2;
                                position3 = position4;
                                tag2 = tag4;
                                break;
                            } else {
                                int position18 = ArrayDecoders.decodeVarint64(bArr4, position17, registers5);
                                UnsafeUtil.putBoolean(t6, fieldOffset, registers5.long1 != 0);
                                currentPresenceField3 |= presenceMask;
                                bArr = bArr4;
                                t2 = t6;
                                registers2 = registers5;
                                currentPresenceField4 = pos2;
                                number3 = number4;
                                position8 = position18;
                                unsafe3 = unsafe7;
                                position7 = limit;
                                break;
                            }
                        case 8:
                            byte[] bArr5 = bArr;
                            T t7 = t2;
                            Unsafe unsafe8 = unsafe3;
                            int position19 = tag;
                            pos2 = pos;
                            ArrayDecoders.Registers registers6 = registers2;
                            number = number4;
                            if (wireType2 != 2) {
                                unsafe2 = unsafe8;
                                position4 = position19;
                                t = t7;
                                unsafe = unsafe2;
                                position3 = position4;
                                tag2 = tag4;
                                break;
                            } else {
                                if ((ENFORCE_UTF8_MASK & typeAndOffset2) == 0) {
                                    position5 = ArrayDecoders.decodeString(bArr5, position19, registers6);
                                } else {
                                    position5 = ArrayDecoders.decodeStringRequireUtf8(bArr5, position19, registers6);
                                }
                                unsafe8.putObject(t7, fieldOffset, registers6.object1);
                                currentPresenceField3 |= presenceMask;
                                bArr = bArr5;
                                t2 = t7;
                                registers2 = registers6;
                                currentPresenceField4 = pos2;
                                number3 = number4;
                                position8 = position5;
                                unsafe3 = unsafe8;
                                position7 = limit;
                                break;
                            }
                        case 9:
                            pos2 = pos;
                            if (wireType2 == 2) {
                                T t8 = t2;
                                Object current = messageSchema.mutableMessageFieldForMerge(t8, pos2);
                                Unsafe unsafe9 = unsafe3;
                                int position20 = tag;
                                byte[] bArr6 = bArr;
                                ArrayDecoders.Registers registers7 = registers2;
                                int position21 = ArrayDecoders.mergeMessageField(current, messageSchema.getMessageFieldSchema(pos2), bArr6, position20, position7, registers7);
                                messageSchema.storeMessageField(t8, pos2, current);
                                currentPresenceField3 |= presenceMask;
                                bArr = bArr6;
                                t2 = t8;
                                registers2 = registers7;
                                currentPresenceField4 = pos2;
                                number3 = number4;
                                position8 = position21;
                                unsafe3 = unsafe9;
                                position7 = limit;
                                break;
                            } else {
                                T t9 = t2;
                                Unsafe unsafe10 = unsafe3;
                                int position22 = tag;
                                number = number4;
                                unsafe2 = unsafe10;
                                position4 = position22;
                                t = t9;
                                unsafe = unsafe2;
                                position3 = position4;
                                tag2 = tag4;
                                break;
                            }
                        case 10:
                            pos2 = pos;
                            if (wireType2 != 2) {
                                position4 = tag;
                                number = number4;
                                t = t2;
                                unsafe2 = unsafe3;
                                unsafe = unsafe2;
                                position3 = position4;
                                tag2 = tag4;
                                break;
                            } else {
                                position8 = ArrayDecoders.decodeBytes(bArr, tag, registers2);
                                unsafe3.putObject(t2, fieldOffset, registers2.object1);
                                currentPresenceField3 |= presenceMask;
                                currentPresenceField4 = pos2;
                                number3 = number4;
                                break;
                            }
                        case 12:
                            pos2 = pos;
                            if (wireType2 != 0) {
                                position4 = tag;
                                number = number4;
                                t = t2;
                                unsafe2 = unsafe3;
                                unsafe = unsafe2;
                                position3 = position4;
                                tag2 = tag4;
                                break;
                            } else {
                                position8 = ArrayDecoders.decodeVarint32(bArr, tag, registers2);
                                unsafe3.putInt(t2, fieldOffset, registers2.int1);
                                currentPresenceField3 |= presenceMask;
                                currentPresenceField4 = pos2;
                                number3 = number4;
                                break;
                            }
                        case 15:
                            pos2 = pos;
                            if (wireType2 != 0) {
                                position4 = tag;
                                number = number4;
                                t = t2;
                                unsafe2 = unsafe3;
                                unsafe = unsafe2;
                                position3 = position4;
                                tag2 = tag4;
                                break;
                            } else {
                                position8 = ArrayDecoders.decodeVarint32(bArr, tag, registers2);
                                unsafe3.putInt(t2, fieldOffset, CodedInputStream.decodeZigZag32(registers2.int1));
                                currentPresenceField3 |= presenceMask;
                                currentPresenceField4 = pos2;
                                number3 = number4;
                                break;
                            }
                        case 16:
                            if (wireType2 != 0) {
                                pos2 = pos;
                                position4 = tag;
                                number = number4;
                                t = t2;
                                unsafe2 = unsafe3;
                                unsafe = unsafe2;
                                position3 = position4;
                                tag2 = tag4;
                                break;
                            } else {
                                int position23 = ArrayDecoders.decodeVarint64(bArr, tag, registers2);
                                Unsafe unsafe11 = unsafe3;
                                unsafe11.putLong(message, fieldOffset, CodedInputStream.decodeZigZag64(registers2.long1));
                                unsafe3 = unsafe11;
                                t2 = message;
                                currentPresenceField3 |= presenceMask;
                                currentPresenceField4 = pos;
                                number3 = number4;
                                position8 = position23;
                                break;
                            }
                        default:
                            position4 = tag;
                            pos2 = pos;
                            t = t2;
                            unsafe2 = unsafe3;
                            number = number4;
                            unsafe = unsafe2;
                            position3 = position4;
                            tag2 = tag4;
                            break;
                    }
                } else {
                    int position24 = tag;
                    int fieldType4 = pos;
                    T t10 = t2;
                    Unsafe unsafe12 = unsafe3;
                    if (fieldType3 != 27) {
                        position6 = position24;
                        if (fieldType3 <= 49) {
                            int currentPresenceField5 = currentPresenceField3;
                            unsafe = unsafe12;
                            int number5 = currentPresenceFieldOffset;
                            position8 = messageSchema.parseRepeatedField(t10, data, position6, limit, tag4, number4, wireType2, fieldType4, typeAndOffset2, fieldType3, fieldOffset, registers);
                            pos2 = fieldType4;
                            if (position8 != position6) {
                                messageSchema = this;
                                t2 = message;
                                bArr = data;
                                position7 = limit;
                                registers2 = registers;
                                currentPresenceField4 = pos2;
                                number3 = number4;
                                currentPresenceFieldOffset = number5;
                                currentPresenceField3 = currentPresenceField5;
                                unsafe3 = unsafe;
                            } else {
                                position3 = position8;
                                tag2 = tag4;
                                currentPresenceFieldOffset = number5;
                                currentPresenceField3 = currentPresenceField5;
                                t = message;
                            }
                        } else {
                            pos2 = fieldType4;
                            unsafe = unsafe12;
                            currentPresenceField = currentPresenceField3;
                            wireType = wireType2;
                            typeAndOffset = number4;
                            currentPresenceField2 = tag4;
                            number2 = currentPresenceFieldOffset;
                            if (fieldType3 == 50) {
                                if (wireType == 2) {
                                    position8 = parseMapField(message, data, position6, limit, pos2, fieldOffset, registers);
                                    if (position8 != position6) {
                                        messageSchema = this;
                                        t2 = message;
                                        bArr = data;
                                        position7 = limit;
                                        registers2 = registers;
                                        currentPresenceField4 = pos2;
                                        number3 = number4;
                                        currentPresenceFieldOffset = number2;
                                        currentPresenceField3 = currentPresenceField;
                                        unsafe3 = unsafe;
                                    } else {
                                        position3 = position8;
                                        tag2 = currentPresenceField2;
                                        currentPresenceFieldOffset = number2;
                                        currentPresenceField3 = currentPresenceField;
                                        t = message;
                                    }
                                } else {
                                    position3 = position6;
                                    tag2 = currentPresenceField2;
                                    currentPresenceFieldOffset = number2;
                                    currentPresenceField3 = currentPresenceField;
                                    t = message;
                                }
                            } else {
                                tag2 = currentPresenceField2;
                                position8 = parseOneofField(message, data, position6, limit, tag2, typeAndOffset, wireType, typeAndOffset2, fieldType3, fieldOffset, pos2, registers);
                                pos2 = pos2;
                                t = message;
                                if (position8 == position6) {
                                    position3 = position8;
                                    currentPresenceFieldOffset = number2;
                                    currentPresenceField3 = currentPresenceField;
                                } else {
                                    messageSchema = this;
                                    bArr = data;
                                    position7 = limit;
                                    registers2 = registers;
                                    currentPresenceField4 = pos2;
                                    t2 = t;
                                    number3 = number4;
                                    currentPresenceFieldOffset = number2;
                                    currentPresenceField3 = currentPresenceField;
                                    unsafe3 = unsafe;
                                }
                            }
                        }
                    } else if (wireType2 == 2) {
                        Internal.ProtobufList<?> list = (Internal.ProtobufList) unsafe12.getObject(t10, fieldOffset);
                        if (list.isModifiable()) {
                            fieldType2 = fieldType3;
                        } else {
                            int size = list.size();
                            fieldType2 = fieldType3;
                            list = list.mutableCopyWithCapacity2(size == 0 ? 10 : size * 2);
                            unsafe12.putObject(t10, fieldOffset, list);
                        }
                        int position25 = ArrayDecoders.decodeMessageList(messageSchema.getMessageFieldSchema(fieldType4), tag4, bArr, position24, limit, list, registers2);
                        bArr = data;
                        position8 = position25;
                        unsafe3 = unsafe12;
                        t2 = t10;
                        currentPresenceField4 = fieldType4;
                        number3 = number4;
                        position7 = limit;
                        registers2 = registers;
                    } else {
                        position6 = position24;
                        unsafe = unsafe12;
                        currentPresenceField = currentPresenceField3;
                        pos2 = fieldType4;
                        wireType = wireType2;
                        typeAndOffset = number4;
                        currentPresenceField2 = tag4;
                        number2 = currentPresenceFieldOffset;
                        position3 = position6;
                        tag2 = currentPresenceField2;
                        currentPresenceFieldOffset = number2;
                        currentPresenceField3 = currentPresenceField;
                        t = message;
                    }
                }
            }
            position7 = limit;
            messageSchema = this;
            bArr = data;
            registers2 = registers;
            position8 = ArrayDecoders.decodeUnknownField(tag2, data, position3, limit, getMutableUnknownFields(t), registers);
            currentPresenceField4 = pos2;
            t2 = t;
            number3 = number4;
            unsafe3 = unsafe;
        }
        T t11 = t2;
        Unsafe unsafe13 = unsafe3;
        int currentPresenceField6 = currentPresenceField3;
        if (currentPresenceFieldOffset != 1048575) {
            unsafe13.putInt(t11, currentPresenceFieldOffset, currentPresenceField6);
        }
        if (position8 != position7) {
            throw InvalidProtocolBufferException.parseFailure();
        }
        return position8;
    }

    @Override // androidx.datastore.preferences.protobuf.Schema
    public void mergeFrom(T message, byte[] data, int position, int limit, ArrayDecoders.Registers registers) throws IOException {
        if (this.proto3) {
            parseProto3Message(message, data, position, limit, registers);
        } else {
            parseProto2Message(message, data, position, limit, 0, registers);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.datastore.preferences.protobuf.Schema
    public void makeImmutable(T message) {
        if (!isMutable(message)) {
            return;
        }
        if (message instanceof GeneratedMessageLite) {
            GeneratedMessageLite<?, ?> generatedMessage = (GeneratedMessageLite) message;
            generatedMessage.clearMemoizedSerializedSize();
            generatedMessage.clearMemoizedHashCode();
            generatedMessage.markImmutable();
        }
        int bufferLength = this.buffer.length;
        for (int pos = 0; pos < bufferLength; pos += 3) {
            int typeAndOffset = typeAndOffsetAt(pos);
            long offset = offset(typeAndOffset);
            switch (type(typeAndOffset)) {
                case 9:
                case 17:
                    if (isFieldPresent(message, pos)) {
                        getMessageFieldSchema(pos).makeImmutable(UNSAFE.getObject(message, offset));
                        break;
                    } else {
                        break;
                    }
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_HORIZONTAL_BIAS /* 29 */:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_EDITOR_ABSOLUTEX /* 49 */:
                    this.listFieldSchema.makeImmutableListAt(message, offset);
                    break;
                case 50:
                    Object mapField = UNSAFE.getObject(message, offset);
                    if (mapField != null) {
                        UNSAFE.putObject(message, offset, this.mapFieldSchema.toImmutable(mapField));
                        break;
                    } else {
                        break;
                    }
                case LockFreeTaskQueueCore.FROZEN_SHIFT /* 60 */:
                case 68:
                    if (isOneofPresent(message, numberAt(pos), pos)) {
                        getMessageFieldSchema(pos).makeImmutable(UNSAFE.getObject(message, offset));
                        break;
                    } else {
                        break;
                    }
            }
        }
        this.unknownFieldSchema.makeImmutable(message);
        if (this.hasExtensions) {
            this.extensionSchema.makeImmutable(message);
        }
    }

    private final <K, V> void mergeMap(Object message, int pos, Object mapDefaultEntry, ExtensionRegistryLite extensionRegistry, Reader reader) throws IOException {
        long offset = offset(typeAndOffsetAt(pos));
        Object mapField = UnsafeUtil.getObject(message, offset);
        MapFieldSchema mapFieldSchema = this.mapFieldSchema;
        if (mapField == null) {
            mapField = mapFieldSchema.newMapField(mapDefaultEntry);
            UnsafeUtil.putObject(message, offset, mapField);
        } else if (mapFieldSchema.isImmutable(mapField)) {
            mapField = this.mapFieldSchema.newMapField(mapDefaultEntry);
            this.mapFieldSchema.mergeFrom(mapField, mapField);
            UnsafeUtil.putObject(message, offset, mapField);
        }
        reader.readMap(this.mapFieldSchema.forMutableMapData(mapField), this.mapFieldSchema.forMapMetadata(mapDefaultEntry), extensionRegistry);
    }

    private <UT, UB> UB filterMapUnknownEnumValues(Object obj, int i, UB ub, UnknownFieldSchema<UT, UB> unknownFieldSchema, Object obj2) {
        int numberAt = numberAt(i);
        Object object = UnsafeUtil.getObject(obj, offset(typeAndOffsetAt(i)));
        if (object == null) {
            return ub;
        }
        Internal.EnumVerifier enumFieldVerifier = getEnumFieldVerifier(i);
        if (enumFieldVerifier == null) {
            return ub;
        }
        return (UB) filterUnknownEnumMap(i, numberAt, this.mapFieldSchema.forMutableMapData(object), enumFieldVerifier, ub, unknownFieldSchema, obj2);
    }

    private <K, V, UT, UB> UB filterUnknownEnumMap(int i, int i2, Map<K, V> map, Internal.EnumVerifier enumVerifier, UB ub, UnknownFieldSchema<UT, UB> unknownFieldSchema, Object obj) {
        MapEntryLite.Metadata<?, ?> forMapMetadata = this.mapFieldSchema.forMapMetadata(getMapFieldDefaultEntry(i));
        Iterator<Map.Entry<K, V>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<K, V> next = it.next();
            if (!enumVerifier.isInRange(((Integer) next.getValue()).intValue())) {
                if (ub == null) {
                    ub = unknownFieldSchema.getBuilderFromMessage(obj);
                }
                ByteString.CodedBuilder newCodedBuilder = ByteString.newCodedBuilder(MapEntryLite.computeSerializedSize(forMapMetadata, next.getKey(), next.getValue()));
                try {
                    MapEntryLite.writeTo(newCodedBuilder.getCodedOutput(), forMapMetadata, next.getKey(), next.getValue());
                    unknownFieldSchema.addLengthDelimited(ub, i2, newCodedBuilder.build());
                    it.remove();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return ub;
    }

    @Override // androidx.datastore.preferences.protobuf.Schema
    public final boolean isInitialized(T message) {
        int currentPresenceField;
        int currentPresenceFieldOffset;
        int pos;
        int currentPresenceFieldOffset2 = 1048575;
        int currentPresenceFieldOffset3 = 0;
        int i = 0;
        while (i < this.checkInitializedCount) {
            int pos2 = this.intArray[i];
            int number = numberAt(pos2);
            int typeAndOffset = typeAndOffsetAt(pos2);
            int presenceMaskAndOffset = this.buffer[pos2 + 2];
            int presenceFieldOffset = presenceMaskAndOffset & 1048575;
            int presenceMask = 1 << (presenceMaskAndOffset >>> 20);
            if (presenceFieldOffset == currentPresenceFieldOffset2) {
                int i2 = currentPresenceFieldOffset3;
                currentPresenceField = currentPresenceFieldOffset2;
                currentPresenceFieldOffset = pos2;
                pos = i2;
            } else if (presenceFieldOffset == 1048575) {
                int i3 = currentPresenceFieldOffset3;
                currentPresenceField = presenceFieldOffset;
                currentPresenceFieldOffset = pos2;
                pos = i3;
            } else {
                int currentPresenceField2 = UNSAFE.getInt(message, presenceFieldOffset);
                currentPresenceField = presenceFieldOffset;
                currentPresenceFieldOffset = pos2;
                pos = currentPresenceField2;
            }
            if (isRequired(typeAndOffset) && !isFieldPresent(message, currentPresenceFieldOffset, currentPresenceField, pos, presenceMask)) {
                return false;
            }
            switch (type(typeAndOffset)) {
                case 9:
                case 17:
                    if (isFieldPresent(message, currentPresenceFieldOffset, currentPresenceField, pos, presenceMask) && !isInitialized(message, typeAndOffset, getMessageFieldSchema(currentPresenceFieldOffset))) {
                        return false;
                    }
                    break;
                case 27:
                case ConstraintLayout.LayoutParams.Table.LAYOUT_EDITOR_ABSOLUTEX /* 49 */:
                    if (!isListInitialized(message, typeAndOffset, currentPresenceFieldOffset)) {
                        return false;
                    }
                    break;
                case 50:
                    if (!isMapInitialized(message, typeAndOffset, currentPresenceFieldOffset)) {
                        return false;
                    }
                    break;
                case LockFreeTaskQueueCore.FROZEN_SHIFT /* 60 */:
                case 68:
                    if (isOneofPresent(message, number, currentPresenceFieldOffset) && !isInitialized(message, typeAndOffset, getMessageFieldSchema(currentPresenceFieldOffset))) {
                        return false;
                    }
                    break;
            }
            i++;
            currentPresenceFieldOffset2 = currentPresenceField;
            currentPresenceFieldOffset3 = pos;
        }
        return !this.hasExtensions || this.extensionSchema.getExtensions(message).isInitialized();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static boolean isInitialized(Object message, int typeAndOffset, Schema schema) {
        Object nested = UnsafeUtil.getObject(message, offset(typeAndOffset));
        return schema.isInitialized(nested);
    }

    private <N> boolean isListInitialized(Object message, int typeAndOffset, int pos) {
        List<N> list = (List) UnsafeUtil.getObject(message, offset(typeAndOffset));
        if (list.isEmpty()) {
            return true;
        }
        Schema schema = getMessageFieldSchema(pos);
        for (int i = 0; i < list.size(); i++) {
            N nested = list.get(i);
            if (!schema.isInitialized(nested)) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r4v3 */
    /* JADX WARN: Type inference failed for: r4v4, types: [androidx.datastore.preferences.protobuf.Schema] */
    /* JADX WARN: Type inference failed for: r4v6 */
    /* JADX WARN: Type inference failed for: r4v7 */
    private boolean isMapInitialized(T t, int i, int i2) {
        Map<?, ?> forMapData = this.mapFieldSchema.forMapData(UnsafeUtil.getObject(t, offset(i)));
        if (forMapData.isEmpty()) {
            return true;
        }
        if (this.mapFieldSchema.forMapMetadata(getMapFieldDefaultEntry(i2)).valueType.getJavaType() != WireFormat.JavaType.MESSAGE) {
            return true;
        }
        ?? r4 = 0;
        for (Object obj : forMapData.values()) {
            r4 = r4;
            if (r4 == 0) {
                r4 = Protobuf.getInstance().schemaFor((Class) obj.getClass());
            }
            if (!r4.isInitialized(obj)) {
                return false;
            }
        }
        return true;
    }

    private void writeString(int fieldNumber, Object value, Writer writer) throws IOException {
        if (value instanceof String) {
            writer.writeString(fieldNumber, (String) value);
        } else {
            writer.writeBytes(fieldNumber, (ByteString) value);
        }
    }

    private void readString(Object message, int typeAndOffset, Reader reader) throws IOException {
        if (isEnforceUtf8(typeAndOffset)) {
            UnsafeUtil.putObject(message, offset(typeAndOffset), reader.readStringRequireUtf8());
        } else if (this.lite) {
            UnsafeUtil.putObject(message, offset(typeAndOffset), reader.readString());
        } else {
            UnsafeUtil.putObject(message, offset(typeAndOffset), reader.readBytes());
        }
    }

    private void readStringList(Object message, int typeAndOffset, Reader reader) throws IOException {
        boolean isEnforceUtf8 = isEnforceUtf8(typeAndOffset);
        ListFieldSchema listFieldSchema = this.listFieldSchema;
        if (isEnforceUtf8) {
            reader.readStringListRequireUtf8(listFieldSchema.mutableListAt(message, offset(typeAndOffset)));
        } else {
            reader.readStringList(listFieldSchema.mutableListAt(message, offset(typeAndOffset)));
        }
    }

    private <E> void readMessageList(Object message, int typeAndOffset, Reader reader, Schema<E> schema, ExtensionRegistryLite extensionRegistry) throws IOException {
        long offset = offset(typeAndOffset);
        reader.readMessageList(this.listFieldSchema.mutableListAt(message, offset), schema, extensionRegistry);
    }

    private <E> void readGroupList(Object message, long offset, Reader reader, Schema<E> schema, ExtensionRegistryLite extensionRegistry) throws IOException {
        reader.readGroupList(this.listFieldSchema.mutableListAt(message, offset), schema, extensionRegistry);
    }

    private int numberAt(int pos) {
        return this.buffer[pos];
    }

    private int typeAndOffsetAt(int pos) {
        return this.buffer[pos + 1];
    }

    private int presenceMaskAndOffsetAt(int pos) {
        return this.buffer[pos + 2];
    }

    private static int type(int value) {
        return (FIELD_TYPE_MASK & value) >>> 20;
    }

    private static boolean isRequired(int value) {
        return (REQUIRED_MASK & value) != 0;
    }

    private static boolean isEnforceUtf8(int value) {
        return (ENFORCE_UTF8_MASK & value) != 0;
    }

    private static long offset(int value) {
        return 1048575 & value;
    }

    private static boolean isMutable(Object message) {
        if (message == null) {
            return false;
        }
        if (message instanceof GeneratedMessageLite) {
            return ((GeneratedMessageLite) message).isMutable();
        }
        return true;
    }

    private static void checkMutable(Object message) {
        if (!isMutable(message)) {
            throw new IllegalArgumentException("Mutating immutable message: " + message);
        }
    }

    private static <T> double doubleAt(T message, long offset) {
        return UnsafeUtil.getDouble(message, offset);
    }

    private static <T> float floatAt(T message, long offset) {
        return UnsafeUtil.getFloat(message, offset);
    }

    private static <T> int intAt(T message, long offset) {
        return UnsafeUtil.getInt(message, offset);
    }

    private static <T> long longAt(T message, long offset) {
        return UnsafeUtil.getLong(message, offset);
    }

    private static <T> boolean booleanAt(T message, long offset) {
        return UnsafeUtil.getBoolean(message, offset);
    }

    private static <T> double oneofDoubleAt(T message, long offset) {
        return ((Double) UnsafeUtil.getObject(message, offset)).doubleValue();
    }

    private static <T> float oneofFloatAt(T message, long offset) {
        return ((Float) UnsafeUtil.getObject(message, offset)).floatValue();
    }

    private static <T> int oneofIntAt(T message, long offset) {
        return ((Integer) UnsafeUtil.getObject(message, offset)).intValue();
    }

    private static <T> long oneofLongAt(T message, long offset) {
        return ((Long) UnsafeUtil.getObject(message, offset)).longValue();
    }

    private static <T> boolean oneofBooleanAt(T message, long offset) {
        return ((Boolean) UnsafeUtil.getObject(message, offset)).booleanValue();
    }

    private boolean arePresentForEquals(T message, T other, int pos) {
        return isFieldPresent(message, pos) == isFieldPresent(other, pos);
    }

    private boolean isFieldPresent(T message, int pos, int presenceFieldOffset, int presenceField, int presenceMask) {
        if (presenceFieldOffset == 1048575) {
            return isFieldPresent(message, pos);
        }
        return (presenceField & presenceMask) != 0;
    }

    private boolean isFieldPresent(T message, int pos) {
        int presenceMaskAndOffset = presenceMaskAndOffsetAt(pos);
        long presenceFieldOffset = presenceMaskAndOffset & 1048575;
        if (presenceFieldOffset == 1048575) {
            int typeAndOffset = typeAndOffsetAt(pos);
            long offset = offset(typeAndOffset);
            switch (type(typeAndOffset)) {
                case 0:
                    return Double.doubleToRawLongBits(UnsafeUtil.getDouble(message, offset)) != 0;
                case 1:
                    return Float.floatToRawIntBits(UnsafeUtil.getFloat(message, offset)) != 0;
                case 2:
                    return UnsafeUtil.getLong(message, offset) != 0;
                case 3:
                    return UnsafeUtil.getLong(message, offset) != 0;
                case 4:
                    return UnsafeUtil.getInt(message, offset) != 0;
                case 5:
                    return UnsafeUtil.getLong(message, offset) != 0;
                case 6:
                    return UnsafeUtil.getInt(message, offset) != 0;
                case 7:
                    return UnsafeUtil.getBoolean(message, offset);
                case 8:
                    Object value = UnsafeUtil.getObject(message, offset);
                    if (value instanceof String) {
                        return !((String) value).isEmpty();
                    }
                    if (value instanceof ByteString) {
                        return !ByteString.EMPTY.equals(value);
                    }
                    throw new IllegalArgumentException();
                case 9:
                    return UnsafeUtil.getObject(message, offset) != null;
                case 10:
                    return !ByteString.EMPTY.equals(UnsafeUtil.getObject(message, offset));
                case 11:
                    return UnsafeUtil.getInt(message, offset) != 0;
                case 12:
                    return UnsafeUtil.getInt(message, offset) != 0;
                case 13:
                    return UnsafeUtil.getInt(message, offset) != 0;
                case 14:
                    return UnsafeUtil.getLong(message, offset) != 0;
                case 15:
                    return UnsafeUtil.getInt(message, offset) != 0;
                case 16:
                    return UnsafeUtil.getLong(message, offset) != 0;
                case 17:
                    return UnsafeUtil.getObject(message, offset) != null;
                default:
                    throw new IllegalArgumentException();
            }
        }
        int presenceMask = 1 << (presenceMaskAndOffset >>> 20);
        return (UnsafeUtil.getInt(message, (long) (1048575 & presenceMaskAndOffset)) & presenceMask) != 0;
    }

    private void setFieldPresent(T message, int pos) {
        int presenceMaskAndOffset = presenceMaskAndOffsetAt(pos);
        long presenceFieldOffset = 1048575 & presenceMaskAndOffset;
        if (presenceFieldOffset == 1048575) {
            return;
        }
        int presenceMask = 1 << (presenceMaskAndOffset >>> 20);
        UnsafeUtil.putInt(message, presenceFieldOffset, UnsafeUtil.getInt(message, presenceFieldOffset) | presenceMask);
    }

    private boolean isOneofPresent(T message, int fieldNumber, int pos) {
        int presenceMaskAndOffset = presenceMaskAndOffsetAt(pos);
        return UnsafeUtil.getInt(message, (long) (1048575 & presenceMaskAndOffset)) == fieldNumber;
    }

    private boolean isOneofCaseEqual(T message, T other, int pos) {
        int presenceMaskAndOffset = presenceMaskAndOffsetAt(pos);
        return UnsafeUtil.getInt(message, (long) (presenceMaskAndOffset & 1048575)) == UnsafeUtil.getInt(other, (long) (1048575 & presenceMaskAndOffset));
    }

    private void setOneofPresent(T message, int fieldNumber, int pos) {
        int presenceMaskAndOffset = presenceMaskAndOffsetAt(pos);
        UnsafeUtil.putInt(message, 1048575 & presenceMaskAndOffset, fieldNumber);
    }

    private int positionForFieldNumber(final int number) {
        if (number >= this.minFieldNumber && number <= this.maxFieldNumber) {
            return slowPositionForFieldNumber(number, 0);
        }
        return -1;
    }

    private int positionForFieldNumber(final int number, final int min) {
        if (number >= this.minFieldNumber && number <= this.maxFieldNumber) {
            return slowPositionForFieldNumber(number, min);
        }
        return -1;
    }

    private int slowPositionForFieldNumber(final int number, int min) {
        int max = (this.buffer.length / 3) - 1;
        while (min <= max) {
            int mid = (max + min) >>> 1;
            int pos = mid * 3;
            int midFieldNumber = numberAt(pos);
            if (number == midFieldNumber) {
                return pos;
            }
            if (number < midFieldNumber) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return -1;
    }

    int getSchemaSize() {
        return this.buffer.length * 3;
    }
}
