package chapter7;

import java.io.IOException;
import java.io.InputStream;

/**
 * 对于两个ClassLoaderTest类，一个是由系统应用程序类加载器加载的，一个是myLoader加载的。
 * 类加载器不同，两个类必然不等。
 * @author Zack H
 */
public class ClassLoaderTest {

    public static void main(String[] args) throws Exception{

        ClassLoader myLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try {
                    String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                    InputStream is = getClass().getResourceAsStream(fileName);
                    if (is == null)
                        return super.loadClass(name);
                    else {
                        byte[] b = new byte[is.available()];
                        is.read(b);
                        return defineClass(name, b, 0, b.length);
                    }
                } catch (IOException e) {
                    throw new ClassNotFoundException(name);
                }
            }
        };

        Object obj = myLoader.loadClass(ClassLoaderTest.class.getName()).newInstance();
        System.out.println(obj.getClass());
        System.out.println(obj instanceof ClassLoaderTest);
    }

}
