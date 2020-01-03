package chapter9;

import java.io.*;
import java.nio.channels.Channel;
import java.nio.channels.spi.SelectorProvider;

/**
 * 劫持java.lang.System类，修改out和err输出
 * @Author Zack H
 * @Date: 2020/1/3 10:56
 */
public class HackSystem{

    public final static InputStream in = System.in;

    private static ByteArrayOutputStream buffer = new ByteArrayOutputStream();

    public final static PrintStream out = new PrintStream(buffer);

    public final static PrintStream err = out;

    public static String getBufferString(){
        return buffer.toString();
    }

    public static void clearBuffer(){
        buffer.reset();
    }

    public static
    void setSecurityManager(final SecurityManager s) {
        System.setSecurityManager(s);
    }

    public static SecurityManager getSecurityManager() {
        return System.getSecurityManager();
    }

    public static long currentTimeMillis(){
        return System.currentTimeMillis();
    }

    public static void arraycopy(Object src,  int  srcPos,
                                        Object dest, int destPos,
                                        int length){
        System.arraycopy(src,srcPos,dest,destPos,length);
    }

    public static int identityHashCode(Object x){
        return System.identityHashCode(x);
    }

    public static Console console() {
        return System.console();
    }

    public static Channel inheritedChannel() throws IOException {
        return System.inheritedChannel();
    }

    public static long nanoTime(){
        return System.nanoTime();
    }

    public static void gc() {
        System.gc();
    }
}
