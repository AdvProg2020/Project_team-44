package view.menu;

import java.util.HashMap;

public class ProductPageMenu extends Menu {
    public ProductPageMenu(Menu parent) {
        super("Product Page Menu", parent);
        HashMap<Integer, Menu> submenus = new HashMap<>();
        this.setSubmenus(submenus);
    }
}
