package chapter4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @Author Zack H
 * @Date: 2019/12/10 20:40
 */
public class ThreadAnalyze {
    public static void busyThread(){
        Thread thread = new Thread(new Runnable() {
            public void run() {
                while(true)
                    ;
            }
        },"busyThread");
        thread.start();
    }

    public static void lockWaiting(final Object lock){
        Thread thread = new Thread(new Runnable() {
            public void run() {
                synchronized(lock){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "lockWaitingThread");
        thread.start();
    }

    /**
     * 模拟两个线程死锁
     */
    static class DeadLockRunnable implements Runnable{
        int a, b;
        public DeadLockRunnable(int a, int b){
            this.a = a;
            this.b = b;
        }

        public void run(){
            synchronized (Integer.valueOf(a)){
                synchronized (Integer.valueOf(b)){
                    System.out.println(a+b);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        br.readLine();
//        busyThread();
//        br.readLine();
//        Object lock = new Object();
//        lockWaiting(lock);
        // 模拟死锁
        for (int i=0; i<200; i++){
            new Thread(new DeadLockRunnable(1,2)).start();
            new Thread(new DeadLockRunnable(2,1)).start();
        }
    }
}
