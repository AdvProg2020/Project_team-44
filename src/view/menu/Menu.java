package view.menu;

import java.util.HashMap;
import java.util.Scanner;

public abstract class Menu {
    private String name;
    private Menu parent;
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

    }

    public void execute() {

    }
}
