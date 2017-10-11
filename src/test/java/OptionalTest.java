import java.util.Optional;

/**
 * @author lierl
 * @create 2017-10-10 10:33
 **/
public class OptionalTest {
	public static void main(String[] args) {
		Object oo = "aa";
		Optional<Object> obj = Optional.ofNullable(oo);
		if(obj.isPresent()){
			System.out.println("存在"+obj.get());
		}else{
			System.out.println("不存在");
		}
	}
}
