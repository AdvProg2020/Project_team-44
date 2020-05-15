package view.menu;

import model.account.Account;

import java.util.HashMap;

public class ViewDiscountCodesMenu extends Menu {
    public ViewDiscountCodesMenu(Menu parent, Account account) {
        super("View Discount Codes Menu", parent, account);
        HashMap<Integer, Menu> submenus = new HashMap<>();
        submenus.put(1, getViewOfDiscountCodeMenu());
        submenus.put(2, getEditDiscountCodeMenu());
        submenus.put(3, getRemoveDiscountCodeMenu());
        this.setSubmenus(submenus);
    }

    @Override
    public void show() {

    }

    private Menu getViewOfDiscountCodeMenu() {
        return new Menu("View Of Discount Code Menu", this, this.getCurrentUser()) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Please enter your code:");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
            }
        };
    }

    private Menu getEditDiscountCodeMenu() {
        return new Menu("Edit Discount Code Menu", this, this.getCurrentUser()) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Please enter your code:");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
            }
        };
    }

    private Menu getRemoveDiscountCodeMenu() {
        return new Menu("Remove Discount Code Menu", this, this.getCurrentUser()) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Please enter your code:");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
            }
        };
    }
}
