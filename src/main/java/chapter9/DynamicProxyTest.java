package chapter9;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author Zack H
 * @Date: 2020/1/2 22:04
 */
public class DynamicProxyTest {
    interface IHello{
        void sayHellow();
    }

    static class Hello implements IHello{
        @Override
        public void sayHellow() {
            System.out.println("Hello.");
        }
    }

    static class DynamicProxy implements InvocationHandler{
        Object obj;
        Object bind(Object obj){
            this.obj = obj;
            return Proxy.newProxyInstance(obj.getClass().getClassLoader(),obj.getClass().getInterfaces(),this);
        }
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("welcome");
            return method.invoke(obj, args);
        }
    }

    public static void main(String[] args) {
        IHello hello = (IHello) new DynamicProxy().bind(new Hello());
        hello.sayHellow();
    }
}
