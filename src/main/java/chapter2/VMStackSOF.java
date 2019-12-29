package chapter2;

/**
 * VM options: -Xss128k
 * @author Zack H
 */
public class VMStackSOF {

    static int stackLength = 1;

    public static void sof(){
        stackLength++;
        sof();
    }


}
