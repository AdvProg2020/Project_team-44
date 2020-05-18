package view;

import view.menu.MainMenu;
import view.menu.Menu;

import java.util.Scanner;

public class CommandProcessor {
    private static Scanner scanner = new Scanner(System.in);

    public static void runCommandProcessorByMenu() {
        Menu.setScanner(scanner);
        Menu main = new MainMenu(null);
        main.show();
        main.menuWork();
        main.execute();
    }
}
