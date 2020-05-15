package view.menu;

import model.account.Account;

import java.util.HashMap;

public class SellerAccountMenu extends Menu {
    public SellerAccountMenu(Menu parent, Account account) {
        super("Seller Account Menu", parent, account);
        HashMap<Integer, Menu> submenus = new HashMap<>();
        submenus.put(1, new ViewPersonalInfoOfSellerMenu(this, account));
        submenus.put(2, getViewCompanyInformationMenu());
        submenus.put(3, getViewSaleHistoryMenu());
        submenus.put(4, new ManagerProductsForSellerMenu(this, account));
        this.setSubmenus(submenus);


    }

    private Menu getViewCompanyInformationMenu() {
        return new Menu("View Company Information Menu", this, this.getCurrentUser()) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
            }
        };
    }

    private Menu getViewSaleHistoryMenu() {
        return new Menu("View Sale History Menu", this, this.getCurrentUser()) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
            }
        };
    }
}
