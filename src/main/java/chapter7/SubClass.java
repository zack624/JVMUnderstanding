package chapter7;

/**
 * @Author Zack H
 * @Date: 2019/12/29 13:03
 */
public class SubClass extends SuperClass{
    static{
        System.out.println("SubClass initializing.");
    }

    public static final String CONSTWORD = "Hello World";
}
