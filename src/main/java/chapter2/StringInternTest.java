package chapter2;

/**
 * String.intern() in JDK more than 1.7
 * @author Zack H
 */
public class StringInternTest {
    public static void test(){
        // "Java" is a word created initially, so the instance will not be created again.
        // "Java" 在字符串常量池已经出现过，因此和StringBuilder在堆上的 "Java" 不是同一个
        String s1 = new StringBuilder("Ja").append("va").toString();
        System.out.println(s1.intern()==s1);

        // JDK 1.7 以后，intern() 方法只会在常量池存储“计算机”的实例引用
        String s2 = new StringBuilder("计算").append("机").toString();
        System.out.println(s2.intern()==s2);
    }
}
