package view.menu;

import java.util.HashMap;

public class OffsMenu extends Menu {
    public OffsMenu(Menu parent) {
        super("Offs Menu", parent);
        HashMap<Integer, Menu> submenus = new HashMap<>();
        this.setSubmenus(submenus);
    }
}
