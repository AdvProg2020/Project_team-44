package view.menu;

import model.account.Account;

import java.util.HashMap;

public class ManagerProductsForSellerMenu extends Menu {
    public ManagerProductsForSellerMenu(Menu parent, Account account) {
        super("Manager Products For Seller Menu", parent, account);
        HashMap<Integer, Menu> submenus = new HashMap<>();
        submenus.put(1, getViewProductMenu());
        submenus.put(2, getViewBuyersOfProductMenu());
        submenus.put(3, getEditProductMenu());
        this.setSubmenus(submenus);
    }

    @Override
    public void show() {

    }

    private Menu getViewProductMenu() {
        return new Menu("View Product Menu", this, this.getCurrentUserLoggedIn()) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Please enter your product Id:");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
            }
        };
    }

    private Menu getViewBuyersOfProductMenu() {
        return new Menu("View Buyers Of Product Menu", this, this.getCurrentUserLoggedIn()) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Please enter your product Id:");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
            }
        };
    }

    private Menu getEditProductMenu() {
        return new Menu("Edit Product Menu", this, this.getCurrentUserLoggedIn()) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Please enter your product Id:");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
            }
        };
    }
}
