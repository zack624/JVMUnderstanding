package chapter3;

/**
 * Reference Counting didn't used in JVM
 * 结果被GC了，[ParOldGen: 307208K->103051K(385536K)]
 * vm options: -verbose:gc -XX:+PrintGCDetails
 * @author Zack H
 */
public class ReferenceCounting {
    public ReferenceCounting instance = null;

    private static final int _1MB = 1024*1024;
    private byte[] bigSize = new byte[100 * _1MB];

    public void rcNotInJVM(){
        ReferenceCounting rct1 = new ReferenceCounting();
        ReferenceCounting rct2 = new ReferenceCounting();

        rct1.instance = rct2;
        rct2.instance = rct1;

        rct1 = null;
        rct2 = null;

        System.gc();
    }

    public static void main(String[] args) {
        new ReferenceCounting().rcNotInJVM();
    }
}
