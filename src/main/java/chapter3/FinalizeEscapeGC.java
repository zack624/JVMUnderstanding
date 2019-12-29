package chapter3;

/**
 * finalize() for an object will be executed just once by JVM.
 * @author Zack H
 */
public class FinalizeEscapeGC {

    public static FinalizeEscapeGC SAVE_HOOK = null;

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize method executed.");
        FinalizeEscapeGC.SAVE_HOOK = this;
    }

    public void isAlive(){
        System.out.println("yes, I am still alive :)");
    }

    public static void main(String[] args) throws InterruptedException {
        SAVE_HOOK = new FinalizeEscapeGC();

        SAVE_HOOK = null;
        System.gc();
        Thread.sleep(500); // waiting for finalize()
        if (SAVE_HOOK == null){
            System.out.println("gegc is dead.");
        }else{
            SAVE_HOOK.isAlive();
        }
        // second time failed
        SAVE_HOOK = null;
        System.gc();
        if (SAVE_HOOK == null){
            System.out.println("gegc is dead :(");
        }else{
            SAVE_HOOK.isAlive();
        }
    }
}
