package view.menu;

import controller.SellerAccountManager;
import exception.ProductIdNotExistsException;

import model.account.Account;

import java.util.HashMap;

public class ViewOffsOfSellerMenu extends Menu {
    public ViewOffsOfSellerMenu(Menu parent, Account account) {
        super("View Offs Of Seller Menu", parent, account);
        HashMap<Integer, Menu> submenus = new HashMap<>();
        submenus.put(1,getViewOffMenu());
        submenus.put(2,getEditOffMenu());
        submenus.put(3,getAddOffMenu());
        this.setSubmenus(submenus);
    }

    @Override
    public void menuWork() {
        SellerAccountManager.processViewOffs();
    }

    private Menu getViewOffMenu() {
        return new Menu("View Off Menu", this, this.getCurrentUserLoggedIn()) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Please enter your offId:");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("back"))
                    this.backInExecute();
                else if (input.equalsIgnoreCase("logout") && this.getCurrentUserLoggedIn() != null)
                    this.logoutInExecute();
                else if (!input.matches("view \\w+"))
                    this.invalidCommandInExecute();
                else {
                    String offId = input.substring(5);
                    try {
                        SellerAccountManager.processViewOffEach(offId);
                    } catch (ProductIdNotExistsException viewOffError) {
                        System.err.println(viewOffError.getMessage());
                        this.execute();
                    }

                }
            }
        };
    }

    private Menu getEditOffMenu() {
        return new Menu("Edit Off Menu", this, this.getCurrentUserLoggedIn()) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Please enter your offId:");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("back"))
                    this.backInExecute();
                else if (input.equalsIgnoreCase("logout") && this.getCurrentUserLoggedIn() != null)
                    this.logoutInExecute();
                else if (!input.matches("view \\w+"))
                    this.invalidCommandInExecute();
                else {
                    String offId = input.substring(5);
                    try {
                        SellerAccountManager.processViewOffEach(offId);
                    } catch (ProductIdNotExistsException viewOffError) {
                        System.err.println(viewOffError.getMessage());
                        this.execute();
                    }

                }
            }
        };
    }

    private Menu getAddOffMenu() {
        return new Menu("Add Off Menu", this, this.getCurrentUserLoggedIn()) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Please enter your offId:");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("back"))
                    this.backInExecute();
                else if (input.equalsIgnoreCase("logout") && this.getCurrentUserLoggedIn() != null)
                    this.logoutInExecute();
                else if (!input.matches("view \\w+"))
                    this.invalidCommandInExecute();
                else {
                    String offId = input.substring(5);
                    try {
                        SellerAccountManager.processViewOffEach(offId);
                    } catch (ProductIdNotExistsException viewOffError) {
                        System.err.println(viewOffError.getMessage());
                        this.execute();
                    }

                }
            }
        };
    }
}
