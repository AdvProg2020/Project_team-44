package view.menu;

import model.account.Account;

import java.util.HashMap;

public class PurchaserAccountMenu extends Menu {
    public PurchaserAccountMenu(Menu parent, Account account) {
        super("Purchaser Account Menu", parent, account);
        HashMap<Integer , Menu> submenus = new HashMap<>();
        submenus.put(1,new ViewPersonalInfoOfPurchaserMenu(this,account));
        this.setSubmenus(submenus);
    }

}
