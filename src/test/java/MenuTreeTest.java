import com.google.common.collect.Lists;
import com.lierl.api.entity.Menu;

import java.util.List;

/**
 * Created by lierl on 2017/8/6.
 */
public class MenuTreeTest {
    public static void main(String[] args) {
        Menu menu1 = new Menu();
        menu1.setId(1);
        menu1.setMenuName("后台管理");
        menu1.setParentId(0);

        Menu menu2 = new Menu();
        menu2.setId(2);
        menu2.setMenuName("用户管理");
        menu2.setParentId(1);

        Menu menu3 = new Menu();
        menu3.setId(3);
        menu3.setMenuName("角色管理");
        menu3.setParentId(1);

        Menu menu4 = new Menu();
        menu4.setId(4);
        menu4.setMenuName("菜单管理");
        menu4.setParentId(1);

        Menu menu5 = new Menu();
        menu5.setId(5);
        menu5.setMenuName("资源管理");
        menu5.setParentId(1);

        List<Menu> menus = Lists.newArrayList();
        menus.add(menu1);
        menus.add(menu2);
        menus.add(menu3);
        menus.add(menu4);
        menus.add(menu5);

        for (Menu menu : menus) {
            System.out.println(menu.toString());
        }

        List<Menu> mms = getMenu(menus,0);

        for (Menu mm : mms) {
            System.out.println(mm.toString());
        }
    }

    public static List<Menu> getMenu(List<Menu> menus ,Integer parentId){
        List<Menu> childMenus = Lists.newArrayList();
        for (Menu menu : menus) {
            Integer pId = menu.getParentId();
            Integer menuId = menu.getId();
            if(parentId == pId){
                List<Menu> childNodes = getMenu(menus,menuId);
                menu.setSubMenus(childNodes);
                childMenus.add(menu);
            }
        }
        return childMenus;
    }
}
