package view.menu.offsPage;

import controller.OffersPageController;
import exception.FilterNotExistsException;
import view.menu.Menu;

import java.util.HashMap;

public class FilteringInOffsMenu extends Menu {
    public FilteringInOffsMenu(Menu parent) {
        super("Filtering In Offs Menu", parent);
        HashMap<Integer, Menu> submenus = new HashMap<>();
        submenus.put(1, getShowAvailableFiltersMenu());
        submenus.put(2, getShowOffsWithFilterMenu());
        submenus.put(3, getCurrentFiltersMenu());
        submenus.put(4, getDisableFilterMenu());
        this.setSubmenus(submenus);
    }

    private Menu getShowAvailableFiltersMenu() {
        return new Menu("Show Available Filters Menu", this) {
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
                OffersPageController.processShowAvailableFiltersEach();
            }
        };
    }

    private Menu getShowOffsWithFilterMenu() {
        return new Menu("Show Offs With Filter Menu", this) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Please enter the filter");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("back"))
                    this.backInExecute();
                else if (!input.matches("filter \\w+"))
                    this.invalidCommandInExecute();
                else {
                    String availableFilter = input.substring(7);
                    try {
                        OffersPageController.processFilterEach(availableFilter);
                        this.execute();
                    } catch (FilterNotExistsException filterError) {
                        System.out.println(filterError.getMessage());
                        this.execute();
                    }
                }
            }
        };
    }

    private Menu getCurrentFiltersMenu() {
        return new Menu("Current Filters Menu", this) {
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
                OffersPageController.processCurrentFilterEach();
            }
        };
    }

    private Menu getDisableFilterMenu() {
        return new Menu("Disable Filter Menu", this) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Please enter the filter");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("back"))
                    this.backInExecute();
                else if (!input.matches("disable filter \\w+"))
                    this.invalidCommandInExecute();
                else {
                    String selectedFilter = input.substring(15);
                    try {
                        OffersPageController.processDeleteFilterEach(selectedFilter);
                        System.out.println("disable filter successful");
                        this.execute();
                    } catch (FilterNotExistsException disableFilterError) {
                        System.out.println(disableFilterError.getMessage());
                        this.execute();
                    }
                }
            }
        };
    }
}

