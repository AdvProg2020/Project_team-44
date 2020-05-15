package view.menu;

import model.account.Account;

import java.util.HashMap;

public class ManageRequestsMenu extends Menu {
    public ManageRequestsMenu(Menu parent, Account account) {
        super("Manager Requests Menu", parent, account);
        HashMap<Integer, Menu> submenus = new HashMap<>();
        submenus.put(1, getDetailsOfRequestMenu());
        submenus.put(2, getAcceptRequestMenu());
        submenus.put(3, getDeclineRequestMenu());
        this.setSubmenus(submenus);
    }

    @Override
    public void show() {

    }

    private Menu getDetailsOfRequestMenu() {
        return new Menu("Details Of Request Menu", this, this.getCurrentUser()) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Please enter your request Id:");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
            }
        };
    }

    private Menu getAcceptRequestMenu() {
        return new Menu("Accept Request Menu", this, this.getCurrentUser()) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Please enter your request Id:");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
            }
        };
    }

    private Menu getDeclineRequestMenu() {
        return new Menu("Decline Request Menu", this, this.getCurrentUser()) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Please enter your request Id:");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
            }
        };
    }
}
