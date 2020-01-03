package chapter9;

/**
 * 修改Class字节码文件中的常量池，把java.lang.System类替换
 * @Author Zack H
 * @Date: 2020/1/3 11:19
 */
public class ClassModifier {
    private byte[] classBytes;

    private final static int CONSTANT_POOL_COUNT_INDEX = 8; // 常量池的常量数量值所在索引
    private final static int CONSTANT_UTF8_INFO_TAG = 1;

    private final static int U1 = 1;
    private final static int U2 = 2;

    // 各个表结构的长度
    private final static int[] CONSTANT_INFO_LENGTHS = {-1,-1,-1,5,5,9,9,3,3,5,5,5,5,-1,-1,4,3,-1,5};

    public ClassModifier(byte[] classBytes) {
        this.classBytes = classBytes;
    }

    /**
     * 替换CONSTANT_Utf8_info值为java.lang.System的数据
     * @param oldStr
     * @param newStr
     * @return
     */
    public byte[] modifyConstantUtf8Info(String oldStr, String newStr){
        int constant_pool_count = ByteUtils.bytes2int(classBytes, CONSTANT_POOL_COUNT_INDEX, U2);
        int offset = CONSTANT_POOL_COUNT_INDEX + U2;
        for (int i=0; i<constant_pool_count; i++){
            int tag = ByteUtils.bytes2int(classBytes,offset,U1);
            if (tag == CONSTANT_UTF8_INFO_TAG){
                offset += U1;
                int utf8_info_length = ByteUtils.bytes2int(classBytes,offset,U2);
                offset += U2;
                String utf8_info = ByteUtils.bytes2String(classBytes, offset,utf8_info_length);
                if (oldStr.equalsIgnoreCase(utf8_info)){
                    byte[] newStrLen = ByteUtils.int2bytes(newStr.length(), U2);
                    classBytes = ByteUtils.bytesReplace(classBytes, offset-U2, U2, newStrLen);
                    classBytes = ByteUtils.bytesReplace(classBytes, offset, utf8_info_length, ByteUtils.string2bytes(newStr));
                    offset += ByteUtils.string2bytes(newStr).length;
                }else{
                    offset += utf8_info_length;
                }
            }else{
                offset += CONSTANT_INFO_LENGTHS[tag];
            }
        }
        return classBytes;
    }

}
