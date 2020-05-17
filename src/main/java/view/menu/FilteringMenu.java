package view.menu;

import controller.ProductsPageController;
import exception.FilterNotExistsException;
import model.account.Account;

import java.util.HashMap;

public class FilteringMenu extends Menu {
    public FilteringMenu(Menu parent, Account account) {
        super("Filtering Menu", parent, account);
        HashMap<Integer, Menu> submenus = new HashMap<>();
        submenus.put(1, getShowAvailableFiltersMenu());
        submenus.put(2, getShowProductsWithFilterMenu());
        submenus.put(3, getCurrentFiltersMenu());
        submenus.put(4, getDisableFilterMenu());

        this.setSubmenus(submenus);
    }

    private Menu getShowAvailableFiltersMenu() {
        return new Menu("Show Available Filters Menu", this, this.getCurrentUserLoggedIn()) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("back"))
                    this.backInExecute();
                else if (input.equalsIgnoreCase("logout") && this.getCurrentUserLoggedIn() != null)
                    this.logoutInExecute();
                else
                    this.invalidCommandInExecute();
            }

            @Override
            public void menuWork() {
                ProductsPageController.processShowAvailableFiltersEach();
            }
        };
    }

    private Menu getShowProductsWithFilterMenu() {
        return new Menu("Show Products With Filter Menu", this, this.getCurrentUserLoggedIn()) {
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
                else if (input.equalsIgnoreCase("logout") && this.getCurrentUserLoggedIn() != null)
                    this.logoutInExecute();
                else if (!input.matches("filter \\w+"))
                    this.invalidCommandInExecute();
                else {
                    String availableFilter = input.substring(7);
                    try {
                        ProductsPageController.processFilterEach(availableFilter);
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
        return new Menu("Current Filters Menu", this, this.getCurrentUserLoggedIn()) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("back"))
                    this.backInExecute();
                else if (input.equalsIgnoreCase("logout") && this.getCurrentUserLoggedIn() != null)
                    this.logoutInExecute();
                else
                    this.invalidCommandInExecute();
            }

            @Override
            public void menuWork() {
                ProductsPageController.processCurrentFilterEach();
            }
        };
    }

    private Menu getDisableFilterMenu() {
        return new Menu("Disable Filter Menu", this, this.getCurrentUserLoggedIn()) {
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
                else if (input.equalsIgnoreCase("logout") && this.getCurrentUserLoggedIn() != null)
                    this.logoutInExecute();
                else if (!input.matches("disable filter \\w+"))
                    this.invalidCommandInExecute();
                else {
                    String selectedFilter = input.substring(15);
                    try {
                        ProductsPageController.processDeleteFilterEach(selectedFilter);
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
