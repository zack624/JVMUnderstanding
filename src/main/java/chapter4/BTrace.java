package chapter4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * BTrace 是一个动态日志跟踪工具，VisualVM的插件。
 * @Author Zack H
 * @Date: 2019/12/10 21:46
 */
public class BTrace {

    public int add(int a, int b){
        return a+b;
    }

    public static void main(String[] args) throws IOException {
        BTrace bTrace = new BTrace();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int i=0; i<10; i++){
            br.readLine();
            int a = (int) Math.round(Math.random()*1000);
            int b = (int) Math.round(Math.random()*1000);
            System.out.println(bTrace.add(a,b));;
        }
    }
}

/* BTrace Script Template */
//import com.sun.btrace.annotations.*;
//        import static com.sun.btrace.BTraceUtils.*;
//
//@BTrace
//public class TracingScript {
//    /* put your code here */
//    @OnMethod(
//            clazz  = "chapter4.BTrace",
//            method = "add",
//            location = @Location(Kind.RETURN)
//    )
//
//    public static void func(@Self chapter4.BTrace instance, int a, int b, @Return int result){
//        println("调用堆栈");
//        jstack();
//        println(strcat("方法参数A:",str(a)));
//        println(strcat("方法参数B:",str(b)));
//        println(strcat("方法结果:",str(result)));
//    }
//}