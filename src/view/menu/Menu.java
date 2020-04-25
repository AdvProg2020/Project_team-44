package view.menu;

import java.util.HashMap;
import java.util.Scanner;

public abstract class Menu {
    private String name;
    private Menu parent;
    private HashMap<Menu, Integer> submenus = new HashMap<>();
    protected static Scanner scanner;

//    public static Menu getInstance() {
//        return null;
//    }

    protected Menu(String name, Menu parent) {
        this.name = name;
        this.parent = parent;
    }

    public static void setScanner(Scanner scanner) {
        Menu.scanner = scanner;
    }

    public void show() {

    }

    public void execute() {

    }
}
