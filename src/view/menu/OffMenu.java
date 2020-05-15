package view.menu;

import model.account.Account;

import java.util.HashMap;

public class OffMenu extends Menu {
    public OffMenu(Menu parent, Account account) {
        super("Off Menu", parent, account);
        HashMap<Integer, Menu> submenus = new HashMap<>();
        this.setSubmenus(submenus);
    }
}
