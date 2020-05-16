package view.menu;

import controller.LoginPageController;
import model.account.Account;

import java.util.HashMap;
import java.util.Scanner;

public abstract class Menu {
    private String name;
    private Menu parent;
    private HashMap<Integer, Menu> submenus = new HashMap<>();
    protected static Scanner scanner;
    private Account currentUserLoggedIn;

    protected Menu(String name, Menu parent, Account account) {
        this.name = name;
        this.parent = parent;
        this.currentUserLoggedIn = account;
    }

    public static void setScanner(Scanner scanner) {
        Menu.scanner = scanner;
    }

    public Account getCurrentUserLoggedIn() {
        return currentUserLoggedIn;
    }

    public void setSubmenus(HashMap<Integer, Menu> submenus) {
        this.submenus = submenus;
    }

    public String getName() {
        return name;
    }

    public void show() {
        System.out.println(this.name + ":");
        for (Integer menuNumber : submenus.keySet()) {
            System.out.println(menuNumber + "- " + submenus.get(menuNumber).name);
        }
        System.out.println((submenus.size() + 1) + "- help");
        if (this.parent != null)
            System.out.println((submenus.size() + 2) + "- back");
        else
            System.out.println((submenus.size() + 2) + "- exit");
        if (currentUserLoggedIn.isLoggedIn())
            System.out.println((submenus.size() + 3) + "- logout");

    }

    public void execute() {
        Menu nextMenu = null;
        String input = scanner.nextLine();
        if (!input.matches("\\d+"))
            System.err.println("please choose a number for your menu!");
        else {
            int menuNumber = Integer.parseInt(input);
            if (menuNumber == 0) {
                System.err.println("your menu number is invalid!");
                nextMenu = this;
            } else if (menuNumber == submenus.size() + 1) {
                this.show();
                nextMenu = this;
            } else if (menuNumber == submenus.size() + 2) {
                if (parent == null) {
                    System.exit(0);
                } else
                    nextMenu = this.parent;

            } else {
                if (currentUserLoggedIn != null && menuNumber > submenus.size() + 3) {
                    System.err.println("your menu number is invalid!");
                    nextMenu = this;
                } else if (currentUserLoggedIn == null && menuNumber > submenus.size() + 2) {
                    System.err.println("your menu number is invalid!");
                    nextMenu = this;
                } else if (currentUserLoggedIn != null && menuNumber == submenus.size() + 3) {
                    nextMenu = new MainMenu(null, null);
                    LoginPageController.logout();
                } else
                    nextMenu = submenus.get(menuNumber);
            }
        }

        nextMenu.show();
        nextMenu.menuWork();
        nextMenu.execute();
    }

    public void menuWork() {

    }
}
