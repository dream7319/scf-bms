import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.lierl.api.entity.RoleResource;

import javax.annotation.Nullable;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author lierl
 * @create 2017-08-07 10:22
 **/
public class FileTest {
	public static void main(String[] args) throws IOException {
		List<String> lines = Files.readAllLines(Paths.get("E:\\aa.txt"), StandardCharsets.UTF_8);

		Iterator<String> iters = lines.stream().filter(line -> line.contains("a")).iterator();

		Stream<String> stream = lines.stream().filter(line -> line.contains("a"));

		stream.map(String::toUpperCase).iterator().forEachRemaining(i->{
//			System.out.println(i);
		});

		List<String> list = lines.stream().filter(line -> line.contains("a")).collect(Collectors.toList());

		lines.stream().filter(line -> line.contains("a")).collect(Collectors.toList());

		list.forEach(String::toUpperCase);

		System.out.println(Paths.get("E:\\aa.txt").getFileName().toString());

		Test t1 = new Test();
		t1.setId(1);
		Test t2 = new Test();
		t2.setId(2);

		List<Test> tests = Lists.newArrayList();
		tests.add(t1);
		tests.add(t2);

		List<Integer> ids = tests.stream().map(Test::getId).collect(Collectors.toList());

		List<RoleResource> datas = Lists.transform(ids, new Function<Integer, RoleResource>() {
			@Nullable
			@Override
			public RoleResource apply(@Nullable Integer input) {
				RoleResource rr = new RoleResource();
				rr.setResourceId(input);
				rr.setRoleId(Integer.valueOf(1));
				return rr;
			}
		});

		datas.forEach(System.out::println);
	}
}
class Test{
	private Integer id ;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
