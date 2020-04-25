package view;

import model.product.Product;
import view.menu.MainMenu;
import view.menu.Menu;

import java.util.Scanner;

public class CommandProcessor {
    private static Scanner scanner = new Scanner(System.in);

    public static void runCommandProcessorByMenu() {

        Menu.setScanner(scanner);
        Menu currentMenu = MainMenu.getInstance();
        currentMenu.show();
        currentMenu.execute();
    }
}
