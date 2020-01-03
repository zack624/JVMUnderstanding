package chapter9;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Author Zack H
 * @Date: 2020/1/3 14:34
 */
public class JavaClassExecuter {
    public static String execute(byte[] classByte) throws IllegalAccessException, InstantiationException{
        HackSystem.clearBuffer();
        ClassModifier cm = new ClassModifier(classByte);
        // 修改字节码的System类
        classByte = cm.modifyConstantUtf8Info("java/lang/System","chapter9/HackSystem");
        // 类加载
        Class cz = new HotSwapClassLoader().loadByte(classByte);
        try {
            // 反射调用
            Method main = cz.getMethod("main", new Class[]{String[].class});
            main.invoke(null, new String[]{null});
        } catch (Throwable e) {
            e.printStackTrace(HackSystem.out);
        }
        return HackSystem.getBufferString();
    }

    public static void main(String[] args) throws IOException, InstantiationException, IllegalAccessException {
        InputStream is = JavaClassExecuter.class.getClassLoader().getResourceAsStream("chapter9\\RemoteExecuteTest.class");
        byte[] b = new byte[is.available()];
        is.read(b);
        is.close();
        String out = JavaClassExecuter.execute(b);
        System.out.println(out);
    }
}
