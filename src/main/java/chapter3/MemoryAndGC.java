package chapter3;

public class MemoryAndGC {
    private static final int _1MB = 1024*1024;

    /**
     * vm options: -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:PrintGCDetails -XX:SurvivorRatio=8
     * Parallel Scavenge 默认将 对象<Eden/2 分配在Eden,否则直接进入老年代
     * 在minor GC之前，会检查老年代的剩余连续空间是否大于新生代所有对象之和，
     *      不大于则再比较是否大于以往晋升到老年代的对象平均值，仍不大于就Full GC
     * @Author Zack H
     */
    public static void minorGC(){
        byte[] allocation1 = new byte[2*_1MB];
        byte[] allocation2 = new byte[2*_1MB];
        byte[] allocation3 = new byte[2*_1MB];
        byte[] allocation4 = new byte[2*_1MB];
//        byte[] allocation4 = new byte[4*_1MB];
    }

    /**
     * vm options: -XX:MaxTenuringThreshold=1 -XX:+PrintTenuringDistribution
     */
    public static void objectAge(){
        byte[] object1 = new byte[_1MB/4];
        byte[] object2 = new byte[4*_1MB-1024];
        byte[] object3 = new byte[4*_1MB-1024];
        object3 = null;
        byte[] object4 = new byte[4*_1MB-1024];
    }

    public static void dynamicAge(){
        byte[] o1 = new byte[_1MB/4];
        byte[] o2 = new byte[_1MB/4];
        byte[] o3 = new byte[_1MB/4];
        byte[] object2 = new byte[4*_1MB-1024];
        byte[] object3 = new byte[4*_1MB-1024];
        object3 = null;
        byte[] object4 = new byte[4*_1MB-1024];
    }
    public static void main(String[] args) {
        minorGC();
//        objectAge();
//        dynamicAge();
    }
}
