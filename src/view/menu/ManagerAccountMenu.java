package view.menu;

import model.account.Account;

import java.util.HashMap;

public class ManagerAccountMenu extends Menu {
    public ManagerAccountMenu(Menu parent, Account account) {
        super("Manager Account Menu", parent, account);
        HashMap<Integer, Menu> submenus = new HashMap<>();
        submenus.put(1, new ViewPersonalInfoOfManagerMenu(this, account));
        submenus.put(2, new ManagerUsersMenu(this, account));
        submenus.put(3, new ManageAllProductsMenu(this, account));
        submenus.put(4, getCreateDiscountCodeMenu());
        submenus.put(5, new ViewDiscountCodesMenu(this, account));
        submenus.put(6, new ManageRequestsMenu(this, account));
        submenus.put(7, new ManageCategoriesMenu(this, account));
        this.setSubmenus(submenus);

    }

    private Menu getCreateDiscountCodeMenu() {
        return new Menu("Create Discount Code Menu", this, this.getCurrentUser()) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Please enter the discount code information");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
            }
        };
    }
}
