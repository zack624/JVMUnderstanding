package chapter4;

import java.util.ArrayList;

/**
 * vm options: -Xms100m -Xmx100m -XX:+UseSerialGC
 * @Author Zack H
 * @Date: 2019/12/10 20:10
 */
public class MemoryMonitor {
    static class OOMObject{
        private byte[] _64KB = new byte[64*1024];
    }

    public static void fillHeap(int num) throws InterruptedException {
        ArrayList<OOMObject> oomObjects = new ArrayList<OOMObject>();
        for (int i=0; i<num; i++){
            Thread.sleep(500);
            oomObjects.add(new OOMObject());
        }
        System.gc();
    }

    public static void main(String[] args) throws InterruptedException {
        fillHeap(1000);
        int i = 0;
        while(true){
            Thread.sleep(1000);
        }
    }
}
