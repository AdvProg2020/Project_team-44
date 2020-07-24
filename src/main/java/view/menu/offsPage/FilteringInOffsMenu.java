package view.menu.offsPage;

import controller.OffersPageController;
import exception.CategoryNotExistsException;
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
                int i = 1;
                for (String filtersEach : OffersPageController.processShowAvailableFiltersEach()) {
                    System.out.println(i + "- " + filtersEach);
                    i++;
                }
            }
        };
    }

    private Menu getShowOffsWithFilterMenu() {
        return new Menu("Show Offs With Filter Menu", this) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
            }

            @Override
            public void execute() {
                System.out.println("Please enter the field of the filter");
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("back"))
                    this.backInExecute();
                else if (!input.matches("filter \\w+"))
                    this.invalidCommandInExecute();
                else {
                    String filed = input.substring(7);
                    System.out.println("Please enter your filteringItem that we filter with this");
                    String filter = scanner.nextLine();
                    if (filter.equalsIgnoreCase("back"))
                        this.backInExecute();
                    try {
                        OffersPageController.processFilterEach(filed, filter);
                        int i = 1;
                        for (String off : OffersPageController.processShowOffs()) {
                            System.out.println(i + "- " + off);
                            i++;
                        }
                        this.execute();
                    } catch (FilterNotExistsException | CategoryNotExistsException filterError) {
                        System.err.println(filterError.getMessage());
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
                int i = 1;
                for (String filterEach : OffersPageController.processCurrentFilterEach()) {
                    System.out.println(i + "- " + filterEach);
                    i++;
                }
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
                        int i = 1;
                        for (String off : OffersPageController.processShowOffs()) {
                            System.out.println(i + "- " + off);
                            i++;
                        }
                        System.out.println("disable filter successful");
                        this.backInExecute();
                    } catch (FilterNotExistsException disableFilterError) {
                        System.err.println(disableFilterError.getMessage());
                        this.execute();
                    }
                }
            }
        };
    }
}

