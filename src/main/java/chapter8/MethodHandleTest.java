package chapter8;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;

import static java.lang.invoke.MethodHandles.lookup;

/**
 * 动态类型支持 MethodHandle：实现调用不同类型的同一方法名。
 * 与反射 Reflection 不同，MethodHandle 模拟字节码层次的方法调用。
 * @Author Zack H
 * @Date: 2020/1/2 16:51
 */
public class MethodHandleTest {
    static class MyPrint{
        public void println(String s){
            System.out.print("MyPrint: ");
            System.out.print(s);
        }
    }

    public static void main(String[] args) throws Throwable {
        Object obj = System.currentTimeMillis() % 2 == 0 ? System.out:new MyPrint();
        // 不管是PrintStream还是MyPrint,都可以正确调用println()方法
        getPrintlnMH(obj).invokeExact("zack");
    }

    private static MethodHandle getPrintlnMH(Object receiver) throws NoSuchMethodException, IllegalAccessException {
        MethodType mt = MethodType.methodType(void.class, String.class);
        // 第一个参数是返回值类型，第二个是参数类型
        return lookup().findVirtual(receiver.getClass(), "println", mt).bindTo(receiver);
        // 在指定类中查找符合给定的方法名称 "println", 方法类型 mt, 并且要符合调用权限的方法句柄。
    }
}
