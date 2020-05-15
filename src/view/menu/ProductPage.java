package view.menu;

import model.account.Account;

import java.util.HashMap;

public class ProductPage extends Menu {
    public ProductPage(Menu parent, Account account) {
        super("ProductPage", parent, account);
        HashMap<Integer, Menu> submenus = new HashMap<>();
        this.setSubmenus(submenus);
    }
}
