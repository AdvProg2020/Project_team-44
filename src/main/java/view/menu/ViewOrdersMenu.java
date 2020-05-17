package view.menu;

import controller.PurchaserAccountManager;
import exception.OrderNotExistsException;
import exception.ProductIdNotExistsException;
import model.account.Account;

import java.util.HashMap;

public class ViewOrdersMenu extends Menu {
    public ViewOrdersMenu(Menu parent, Account account) {
        super("View Orders Menu", parent, account);
        HashMap<Integer, Menu> submenus = new HashMap<>();
        submenus.put(1,getShowOrderMenu());
        submenus.put(2,getRateProductMenu());
        this.setSubmenus(submenus);
    }

    @Override
    public void menuWork() {
        PurchaserAccountManager.processViewOrders();
    }

    private Menu getShowOrderMenu() {
        return new Menu("Show Order Menu", this, this.getCurrentUserLoggedIn()) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Please enter the orderId");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("back"))
                    this.backInExecute();
                else if (input.equalsIgnoreCase("logout") && this.getCurrentUserLoggedIn() != null)
                    this.logoutInExecute();
                else if (!input.matches("show order \\w+"))
                    this.invalidCommandInExecute();
                else {
                    String orderId = input.substring(11);
                    try {
                        PurchaserAccountManager.processShowOrderEach(orderId);
                        this.execute();
                    } catch (OrderNotExistsException showOrderError) {
                        System.out.println(showOrderError.getMessage());
                        this.execute();
                    }

                }
            }
        };
    }

    private Menu getRateProductMenu() {
        return new Menu("Rate Product Menu", this, this.getCurrentUserLoggedIn()) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Please enter the productId and your rate");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("back"))
                    this.backInExecute();
                else if (input.equalsIgnoreCase("logout") && this.getCurrentUserLoggedIn() != null)
                    this.logoutInExecute();
                else if (!input.matches("rate \\w+ \\d"))
                    this.invalidCommandInExecute();
                else {
                    String productId = input.substring(5, input.length() - 2);
                    int rate = Integer.parseInt(input.substring(input.length() - 1));
                    if (rate == 0 || rate > 5) {
                        System.err.println("please rate in [1-5]");
                        this.execute();
                    } else {
                        try {
                            PurchaserAccountManager.processRateEach(productId, rate);
                            this.execute();
                        } catch (ProductIdNotExistsException rateProductError) {
                            System.out.println(rateProductError.getMessage());
                            this.execute();
                        }

                    }
                }
            }
        };
    }

}
