package view.menu.offsPage;

import controller.OffersPageController;
import exception.SortNotExistsException;
import view.menu.Menu;

import java.util.HashMap;

public class SortingInOffsMenu extends Menu {
    public SortingInOffsMenu(Menu parent) {
        super("Sorting In Offs Menu", parent);
        HashMap<Integer, Menu> submenus = new HashMap<>();
        submenus.put(1, getShowAvailableSortsMenu());
        submenus.put(2, getShowOffsWithSortMenu());
        submenus.put(3, getCurrentSortMenu());
        submenus.put(4, getDisableSortMenu());
        this.setSubmenus(submenus);
    }

    private Menu getShowAvailableSortsMenu() {
        return new Menu("Show Available Sorts Menu", this) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("back"))
                    this.backInExecute();
                else
                    this.invalidCommandInExecute();
            }

            @Override
            public void menuWork() {
                int i = 1;
                for (String showAvailableSortsEach : OffersPageController.processShowAvailableSortsEach()) {
                    System.out.println(i + "- " + showAvailableSortsEach);
                    i++;
                }
            }

        };
    }

    private Menu getShowOffsWithSortMenu() {
        return new Menu("Show Offs With Sort Menu", this) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
            }

            @Override
            public void execute() {
                System.out.println("Please enter the sort");
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("back"))
                    this.backInExecute();
                else if (!input.matches("sort \\w+"))
                    this.invalidCommandInExecute();
                else {
                    String availableSort = input.substring(5);
                    System.out.println("Do you like sorting from up or down?");
                    String upDown = scanner.nextLine();
                    if (upDown.equalsIgnoreCase("back"))
                        this.backInExecute();
                    if (!upDown.equalsIgnoreCase("up") && !upDown.equalsIgnoreCase("down"))
                        this.invalidCommandInExecute();
                    boolean isUp = true;
                    if (upDown.equalsIgnoreCase("down"))
                        isUp = false;
                    try {
                        OffersPageController.processSortEach(availableSort, isUp);
                        int i = 1;
                        for (String off : OffersPageController.processShowOffs()) {
                            System.out.println(i + "- " + off);
                            i++;
                        }
                        this.execute();
                    } catch (SortNotExistsException sortError) {
                        System.err.println(sortError.getMessage());
                        this.execute();
                    }
                }
            }
        };
    }

    private Menu getCurrentSortMenu() {
        return new Menu("Current Sorts Menu", this) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("back"))
                    this.backInExecute();
                else
                    this.invalidCommandInExecute();
            }

            @Override
            public void menuWork() {
                System.out.println(OffersPageController.processCurrentSortEach());
            }
        };
    }

    private Menu getDisableSortMenu() {
        return new Menu("Disable Sort Menu", this) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("back"))
                    this.backInExecute();
                else
                    this.invalidCommandInExecute();
            }

            @Override
            public void menuWork() {
                OffersPageController.processDisableSortEach();
            }

        };
    }
}
