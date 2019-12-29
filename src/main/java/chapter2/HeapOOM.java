package chapter2;

import java.util.ArrayList;
import java.util.List;

/**
 * VM options: -verbose:gc -XX:+PrintGCDetails -XX:SurvivorRatio=8 -Xms20M -Xmx20M -Xmn10M
 * -XX:+HeapDumpOnOutOfMemoryError
 * @author Zack H
 */
public class HeapOOM {
    static class OOMObject{
    }

    public static void oom(){
        List<OOMObject> list = new ArrayList();
        while (true){
            list.add(new OOMObject());
        }
    }
}
