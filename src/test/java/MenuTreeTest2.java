import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.lierl.api.entity.Resource;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author lierl
 * @create 2017-08-07 15:01
 **/
public class MenuTreeTest2 {
	public static void main(String[] args) {
		Resource r1 = new Resource(1,2,"新增用户","/api/user/add",true,"用户管理");
		Resource r2 = new Resource(3,2,"删除用户","/api/user/delete/",true,"用户管理");
		Resource r3 = new Resource(2,2,"编辑用户","/api/user/update",true,"用户管理");

		Resource r4 = new Resource(9,3,"编辑角色","/api/role/update",true,"角色管理");
		Resource r5 = new Resource(10,3,"分配菜单","/api/roleMenu/addOrUpdate",true,"角色管理");
		Resource r6 = new Resource(11,3,"删除角色","/api/role/delete/",true,"角色管理");

		List<Resource> resources = Lists.newArrayList();
		resources.add(r1);
		resources.add(r2);
		resources.add(r3);
		resources.add(r4);
		resources.add(r5);
		resources.add(r6);

		getResource(resources);
	}

	public static List<Resource> getResource(List<Resource> resources){

		Map<Integer,String> datas = Maps.newHashMap();
		List<MenuTest> tests = Lists.newArrayList();

		for (Resource resource : resources) {
			if(!datas.containsValue(resource.getMenuName())){
				MenuTest one = new MenuTest();
				one.setMenuId(resource.getMenuId());
				one.setMenuName(resource.getMenuName());
				List<Resource> rs = resources.stream().filter(r-> r.getMenuId() == resource.getMenuId()).collect(Collectors.toList());
				one.setResources(rs);
				tests.add(one);
			}

			datas.put(resource.getMenuId(),resource.getMenuName());
		}

		Map<Integer,String> menus = resources.stream().collect(Collectors.toMap(Resource::getMenuId,Resource::getMenuName,(existingValue, newValue) -> existingValue));
		List<MenuTest> tests1 = Lists.newArrayList();

		menus.forEach((key,value)->{
			MenuTest one = new MenuTest();
			one.setMenuId(key);
			one.setMenuName(value);
			List<Resource> rs = resources.stream().filter(r-> r.getMenuId() == key).collect(Collectors.toList());
			one.setResources(rs);
			tests1.add(one);
		});


		for (MenuTest test : tests) {
			System.out.println(test.toString());
		}

		System.out.println("----------华丽分割线-----------");

		for (MenuTest test : tests1) {
			System.out.println(test.toString());
		}

		return null;
	}

	public static MenuTest getMenuTest(MenuTest t,String create){
		if("new".equals(create)){
			return new MenuTest();
		}
		return t;
	}
}
class MenuTest{
	private Integer menuId;
	private String menuName;
	private List<Resource> resources;

	@Override
	public String toString() {
		return "MenuTest{" +
				"menuId=" + menuId +
				", menuName='" + menuName + '\'' +
				", resources=" + resources +
				'}';
	}

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public List<Resource> getResources() {
		return resources;
	}

	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}
}