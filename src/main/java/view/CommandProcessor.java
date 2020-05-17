package view;

import model.account.Account;
import view.menu.MainMenu;
import view.menu.Menu;

import java.util.Scanner;

public class CommandProcessor {
    private static Scanner scanner = new Scanner(System.in);
    private static Account currentUser;

    public static void runCommandProcessorByMenu() {
        Menu.setScanner(scanner);
        Menu currentMenu = new MainMenu(null , currentUser);
        currentMenu.show();
        currentMenu.execute();
    }
}
