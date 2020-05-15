package view.menu;


import model.account.Account;

import java.util.HashMap;

public class ManagerUsersMenu extends Menu {
    public ManagerUsersMenu(Menu parent, Account account) {
        super("Manager Users Menu", parent, account);
        HashMap<Integer, Menu> submenus = new HashMap<>();
        submenus.put(1, getViewOfUserMenu());
        submenus.put(2, getDeleteUserMenu());
        submenus.put(3, getCreateManagerProfileMenu());
        this.setSubmenus(submenus);
    }

    @Override
    public void show() {

    }

    private Menu getViewOfUserMenu() {
        return new Menu("View Of User Menu", this, this.getCurrentUser()) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Please enter the username:");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();

            }
        };
    }

    private Menu getDeleteUserMenu() {
        return new Menu("Delete User Menu", this, this.getCurrentUser()) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Please enter the username");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
            }
        };
    }

    private Menu getCreateManagerProfileMenu() {
        return new Menu("Create Manager Profile Menu", this, this.getCurrentUser()) {
            @Override
            public void show() {

            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
            }
        };
    }

}
