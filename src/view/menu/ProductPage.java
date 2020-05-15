package view.menu;

import java.util.HashMap;

public class ProductPage extends Menu {
    public ProductPage(Menu parent) {
        super("ProductPage", parent);
        HashMap<Integer , Menu> submenus = new HashMap<>();
        this.setSubmenus(submenus);
    }
}
