package chapter2;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * VM options in Java 1.7: -XX:PermSize=10M -XX:MaxPermSize=10M
 * VM options in Java 8: -XX:MaxMetaspaceSize=10M
 * @author Zack H
 */
public class MethodAreaOOM {
    /**
     * runtime constant area oom
     * did not success in JDK 8
     */
    public static void caOOM(){
        int i = 0;
        List<String> list = new ArrayList();
        while(true){
            list.add(String.valueOf(i++).intern());
        }
    }

    // 加载的类过多，类信息量太大，method area oom
    public static void maOOM(){
        while (true){
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(OOMObject.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {

                public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                    return methodProxy.invokeSuper(o,objects);
                }
            });
            enhancer.create();
        }
    }

    static class OOMObject{}


}
