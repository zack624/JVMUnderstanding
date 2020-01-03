package chapter9;

import java.util.Arrays;

/**
 * byte数组和int的相互转换
 * byte做运算时，会先自动转换成int
 * @Author Zack H
 * @Date: 2020/1/3 9:50
 */
public class ByteUtils {
    public static int bytes2int(byte[] bytes, int start, int length){
        int sum = 0;
        int end = start + length;
        for (int i=start; i<end; i++){
            int b = (bytes[i] & 0xff) << (--length)*8;
            sum += b;
        }
        return sum;
    }

    public static byte[] int2bytes(int num, int length){
        byte[] bytes = new byte[length];
        int end = length;
        for (int i=0; i<end; i++){
            bytes[i] = (byte) (num >> (--length)*8);
        }
        return bytes;
    }

    public static String bytes2String(byte[] bytes, int start, int length){
        return new String(bytes, start, length);
    }

    public static byte[] string2bytes(String s){
        return s.getBytes();
    }

    public static byte[] bytesReplace(byte[] originalBytes, int offset, int len, byte[] replacBytes){
        int completeLen = originalBytes.length+replacBytes.length-len;
        byte[] newBytes = new byte[completeLen];
        System.arraycopy(originalBytes, 0, newBytes, 0, offset);
        System.arraycopy(replacBytes, 0, newBytes, offset, replacBytes.length);
        System.arraycopy(originalBytes, offset+len, newBytes, offset+replacBytes.length, originalBytes.length-len-offset);
        return newBytes;
    }

    public static void main(String[] args) {
        byte[] bytes = {0x00, 0x16};
        int num = ByteUtils.bytes2int(bytes, 0, 2);
//        System.out.println(num);
//        byte[] bytes1 = ByteUtils.int2bytes(num, 2);
        System.out.println(Arrays.toString(bytes));
//        System.out.println(new String(bytes,0,2));
        byte[] newb = ByteUtils.bytesReplace(bytes, 1,1,new byte[]{0x23,0x34});
        System.out.println(Arrays.toString(newb));
    }
}
