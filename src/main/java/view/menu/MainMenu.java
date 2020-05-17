package view.menu;

import model.account.Account;

import java.util.HashMap;

public class MainMenu extends Menu {

    public MainMenu(Menu parent, Account account) {
        super("Main Menu", parent, account);
        HashMap<Integer, Menu> submenus = new HashMap<>();

    }


}
