package chapter7;

/**
 * 被动引用，不触发类的初始化
 * vm options: -XX:+TraceClassLoading 可打印类的加载情况
 * @Author Zack H
 * @Date: 2019/12/29 13:06
 */
public class NotInitialization {
    public static void main(String[] args) {
        // 通过子类调用父类的 static 字段
        //System.out.println(SubClass.word);
        // 数组定义引用类
        //SuperClass[] scs = new SuperClass[10];
        // static final 常量通过传播，存储到当前 NotInitialization 类的常量池中
        System.out.println(SubClass.CONSTWORD);
    }
}
