package view.menu;

import controller.ProductsPageController;
import exception.SortNotExistsException;
import model.account.Account;

import java.util.ArrayList;
import java.util.HashMap;

public class SortingMenu extends Menu {
    public SortingMenu(Menu parent, Account account) {
        super("Sorting Menu", parent, account);
        HashMap<Integer, Menu> submenus = new HashMap<>();
        submenus.put(1, getShowAvailableSortsMenu());
        submenus.put(2, getShowProductsWithSortMenu());
        submenus.put(3, getCurrentSortMenu());
        submenus.put(4, getDisableSortMenu());
        this.setSubmenus(submenus);
    }

    private Menu getShowAvailableSortsMenu() {
        return new Menu("Show Available Sorts Menu", this, this.getCurrentUserLoggedIn()) {
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
                ArrayList<String> showSort = new ArrayList<>();
                showSort = ProductsPageController.processShowAvailableSortsEach();

            }
        };
    }

    private Menu getShowProductsWithSortMenu() {
        return new Menu("Show Products With Sort Menu", this, this.getCurrentUserLoggedIn()) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Please enter the sort");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("back"))
                    this.backInExecute();
                else if (input.equalsIgnoreCase("logout") && this.getCurrentUserLoggedIn() != null)
                    this.logoutInExecute();
                else if (!input.matches("sort \\w+"))
                    this.invalidCommandInExecute();
                else {
                    String availableSort = input.substring(5);
                    try {
                        ProductsPageController.processSortEach(availableSort);
                        this.execute();
                    } catch (SortNotExistsException sortError) {
                        System.out.println(sortError.getMessage());
                        this.execute();
                    }
                }
            }
        };
    }

    private Menu getCurrentSortMenu() {
        return new Menu("Current Sorts Menu", this, this.getCurrentUserLoggedIn()) {
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
                System.out.println(ProductsPageController.processCurrentSortEach());
            }
        };
    }

    private Menu getDisableSortMenu() {
        return new Menu("Disable Sort Menu", this, this.getCurrentUserLoggedIn()) {
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
                ProductsPageController.processDisableSortEach();
            }

        };
    }

}
