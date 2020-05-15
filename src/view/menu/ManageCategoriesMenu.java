package view.menu;

import model.account.Account;

import java.util.HashMap;

public class ManageCategoriesMenu extends Menu {
    public ManageCategoriesMenu(Menu parent, Account account) {
        super("Manage Categories Menu", parent, account);
        HashMap<Integer, Menu> submenus = new HashMap<>();
        submenus.put(1, getEditCategoryMenu());
        submenus.put(2, getAddCategoryMenu());
        submenus.put(3, getRemoveCategoryMenu());
        this.setSubmenus(submenus);
    }

    @Override
    public void show() {

    }

    private Menu getEditCategoryMenu() {
        return new Menu("Edit Category Menu", this, this.getCurrentUser()) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Please enter your category:");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
            }
        };
    }

    private Menu getAddCategoryMenu() {
        return new Menu("Add Category Menu", this, this.getCurrentUser()) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Please enter your category:");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
            }
        };
    }

    private Menu getRemoveCategoryMenu() {
        return new Menu("Remove Category Menu", this, this.getCurrentUser()) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Please enter your category:");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
            }
        };
    }
}
