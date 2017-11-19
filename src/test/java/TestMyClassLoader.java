/**
 * Created by lierl
 * 2017/11/18 18:06.
 */
public class TestMyClassLoader {
    public static void main(String[] args) {
        MyClassLoader classLoader = new MyClassLoader("lierl","F:/");
        try {
            Class<?> clazz = classLoader.findClass("Demo");
            clazz.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}
