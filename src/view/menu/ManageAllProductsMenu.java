package view.menu;

import model.account.Account;

import java.util.HashMap;

public class ManageAllProductsMenu extends Menu {
    public ManageAllProductsMenu(Menu parent, Account account) {
        super("Manage All Products Menu", parent, account);
        HashMap<Integer, Menu> submenus = new HashMap<>();
        submenus.put(1, getRemoveProductMenu());
        this.setSubmenus(submenus);
    }

    private Menu getRemoveProductMenu() {
        return new Menu("Remove Product Menu", this, this.getCurrentUser()) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Please enter the product Id:");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
            }
        };
    }
}
