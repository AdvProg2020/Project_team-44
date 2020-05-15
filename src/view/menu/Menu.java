package view.menu;

import model.account.Account;

import java.util.HashMap;
import java.util.Scanner;

public abstract class Menu {
    private String name;
    private Menu parent;
    private HashMap<Integer, Menu> submenus = new HashMap<>();
    protected static Scanner scanner;
    private Account currentUser;

    protected Menu(String name, Menu parent, Account account) {
        this.name = name;
        this.parent = parent;
        this.currentUser = account;
    }

    public static void setScanner(Scanner scanner) {
        Menu.scanner = scanner;
    }

    public Account getCurrentUser() {
        return currentUser;
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
            System.out.println((submenus.size() + 1) + "- exit");

    }

    public void execute() {

    }
}
