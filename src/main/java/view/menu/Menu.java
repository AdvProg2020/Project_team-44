package view.menu;

import controller.LoginPageController;

import java.util.HashMap;
import java.util.Scanner;

public abstract class Menu {
    private String name;
    protected Menu parent;
    private HashMap<Integer, Menu> submenus = new HashMap<>();
    protected static Scanner scanner;

    protected Menu(String name, Menu parent) {
        this.name = name;
        this.parent = parent;
    }

    public static void setScanner(Scanner scanner) {
        Menu.scanner = scanner;
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
        if (this.parent != null)
            System.out.println((submenus.size() + 1) + "- back");
        else
            System.out.println((submenus.size() + 1) + "- exit");
        System.out.println((submenus.size() + 2) + "- help");
        if (!LoginPageController.getLoggedInAccountType().equals("null"))
            System.out.println((submenus.size() + 3) + "- logout");
        else
            System.out.println((submenus.size()+3) + "- Register And Login Menu");


    }

    public void execute() {
        Menu nextMenu = null;
        String input = scanner.nextLine();
        if (!input.matches("\\d+")) {
            System.err.println("please choose a number for your menu!");
            this.execute();
        } else {
            int menuNumber = Integer.parseInt(input);
            if (menuNumber == 0) {
                System.err.println("your menu number is invalid!");
                this.execute();
                return;
            } else if (menuNumber == submenus.size() + 1) {
                this.show();
                nextMenu = this;
            } else if (menuNumber == submenus.size() + 2) {
                if (parent == null) {
                    System.exit(0);
                } else
                    nextMenu = this.parent;

            } else {
                if (!LoginPageController.getLoggedInAccountType().equals("null") && menuNumber > submenus.size() + 3) {
                    System.err.println("your menu number is invalid!");
                    nextMenu = this;
                } else if (LoginPageController.getLoggedInAccountType().equals("null") && menuNumber > submenus.size() + 2) {
                    System.err.println("your menu number is invalid!");
                    nextMenu = this;
                } else if (!LoginPageController.getLoggedInAccountType().equals("null") && menuNumber == submenus.size() + 3) {
                    nextMenu = new MainMenu(null);
                    LoginPageController.logout();
                } else if(LoginPageController.getLoggedInAccountType().equals("null") && menuNumber == submenus.size() +3){
                    nextMenu = new RegisterAndLoginMenu(this);
                }
                else
                    nextMenu = submenus.get(menuNumber);
            }
        }

        nextMenu.show();
        nextMenu.menuWork();
        nextMenu.execute();
    }

    public void menuWork() {

    }

    public void backInExecute() {
        this.getParent().show();
        this.getParent().menuWork();
        this.getParent().execute();
    }
    public void invalidCommandInExecute() {
        System.err.println("invalid command!");
        this.execute();
    }

    public HashMap<Integer, Menu> getSubmenus() {
        return submenus;
    }

    public Menu getParent() {
        return parent;
    }
}
