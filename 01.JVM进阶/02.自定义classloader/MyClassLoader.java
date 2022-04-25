import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;

public class MyClassLoader extends ClassLoader {

  public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
    Class<?> clazz = new MyClassLoader().findClass("Hello");
    Object obj = clazz.newInstance();
    Method method = clazz.getMethod("hello");
    method.invoke(obj);
  }

  @Override
  protected Class<?> findClass(String name) throws ClassNotFoundException {
    byte[] bs;
    try {
      bs = Files.readAllBytes(new File("Hello.xlass").toPath());
    } catch (IOException e) {
      throw new ClassNotFoundException(name, e);
    }
    for (int i = 0; i < bs.length; i++) {
      bs[i] = (byte) (255 - bs[i]);
    }
    try {
      Files.write(new File("Hello.class").toPath(), bs);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return defineClass(name, bs, 0, bs.length);
  }
}
