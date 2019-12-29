package chapter2;

/**
 * VM options: -Xss2M
 * @author Zack H
 */
public class VMStackOOM {
    private static void dontStop(){
        while(true){
        }
    }

    public static void oomByThread(){
        while(true){
            Thread thread = new Thread(new Runnable(){
                @Override
                public void run() {
                    dontStop();
                }
            });

            thread.start();
        }
    }
}
