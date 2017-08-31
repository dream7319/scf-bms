import com.google.common.collect.Lists;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

/**
 * @author lierl
 * @create 2017-08-30 10:43
 **/
public class ReadAllFile {

	static List<File> javaFiles = Lists.newArrayList();

	public static void main(String[] args) throws IOException {
		File file = new File("E:\\workspace\\scf-base\\src\\main\\java\\com\\xingyoucai\\scf");
		getAllFile(file);
//		writeCode();
	}

	public static void getAllFile(File file) throws IOException {
		if(file.isDirectory()){
			File[] files = file.listFiles();
			for (int i = 0; i < files.length; i++) {
				File f = files[i];
				if(f.isDirectory()){
					getAllFile(f);
				}else{
					if(!f.getName().endsWith(".xml"))
						Files.write(Paths.get("E:\\aa.doc"),Files.readAllBytes(Paths.get(f.getAbsolutePath())), StandardOpenOption.APPEND);
				}
			}
		}else{
			if(!file.getName().endsWith(".xml"))
				Files.write(Paths.get("E:\\aa.doc"),Files.readAllBytes(Paths.get(file.getAbsolutePath())), StandardOpenOption.APPEND);
		}
	}


	public static void writeCode() throws IOException {
		if(javaFiles.isEmpty()){
			javaFiles.forEach(file->{
				try {
					Files.write(Paths.get("E:\\aa.doc"),Files.readAllBytes(Paths.get(file.getAbsolutePath())), StandardOpenOption.APPEND);
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
		}

//		Files.write(Paths.get("E:\\aa.doc"),Files.readAllBytes(Paths.get("E:\\aa.txt")));
	}
}
