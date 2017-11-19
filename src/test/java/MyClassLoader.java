import java.io.*;

/**
 * Created by lierl
 * 2017/11/18 17:51.
 */
public class MyClassLoader extends ClassLoader{

    private String name;
    private String path;

    public MyClassLoader(String name, String path) {
        super();//让系统类加载器成为该类的父类加载器
        this.name = name;
        this.path = path;
    }

    public MyClassLoader(ClassLoader parent, String name, String path) {
        super(parent);//手动执行父类加载器
        this.name = name;
        this.path = path;
    }

    @Override
    public String toString() {
        return "MyClassLoader{" +
                "name='" + name + '\'' +
                '}';
    }

    /**
     * 通过自定义类加载器加载类
     * @param name
     * @return
     * @throws ClassNotFoundException
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] data = readClassFileToByteArray(name);
        return this.defineClass(name,data,0,data.length);
    }

    /**
     * 通过指定路径加载class文件为byte[]
     * @param name
     * @return
     */
    private byte[] readClassFileToByteArray(String name) {
        InputStream in = null;
        byte[] returnData = null;
        //把包名转为路径
        String filepath = path + name.replaceAll("\\.", File.separator) + ".class";
        File file = new File(filepath);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            in = new FileInputStream(file);
            int tmp;
            while((tmp = in.read()) != -1){
                out.write(tmp);
            }
            returnData = out.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out!=null) out.close();
                if (in != null) in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return returnData;
    }


}
