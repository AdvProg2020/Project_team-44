package view.menu;

import java.util.HashMap;

public class OffMenu extends Menu {
    public OffMenu(Menu parent) {
        super("Off Menu", parent);
        HashMap<Integer , Menu> submenus = new HashMap<>();
        this.setSubmenus(submenus);
    }
}
