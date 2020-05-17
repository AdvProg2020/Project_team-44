package view.menu;

import model.account.Account;

import java.util.HashMap;

public class OffsMenu extends Menu {
    public OffsMenu(Menu parent, Account account) {
        super("Offs Menu", parent, account);
        HashMap<Integer, Menu> submenus = new HashMap<>();
        this.setSubmenus(submenus);
    }
}
