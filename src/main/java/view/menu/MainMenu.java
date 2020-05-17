package view.menu;

import java.util.HashMap;

public class MainMenu extends Menu {

    public MainMenu(Menu parent) {
        super("Main Menu", parent);
        HashMap<Integer, Menu> submenus = new HashMap<>();
        submenus.put(2, new ProductsPageMenu(this));
        this.setSubmenus(submenus);

    }


}
