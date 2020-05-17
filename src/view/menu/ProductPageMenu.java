package view.menu;

import model.account.Account;

import java.util.HashMap;

public class ProductPageMenu extends Menu {
    public ProductPageMenu( Menu parent, Account account) {
        super("Product Page Menu", parent, account);
        HashMap<Integer , Menu> submenus = new HashMap<>();
        this.setSubmenus(submenus);
    }
}
