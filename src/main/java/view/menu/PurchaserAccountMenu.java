package view.menu;

import controller.LoginPageController;
import controller.PurchaserAccountController;

import java.util.HashMap;

public class PurchaserAccountMenu extends Menu {
    public PurchaserAccountMenu(Menu parent) {
        super("Purchaser Account Menu", parent);
        HashMap<Integer, Menu> submenus = new HashMap<>();
        submenus.put(1, new ViewPersonalInfoOfPurchaserMenu(this));
        submenus.put(2, new ViewOrdersMenu(this));
        submenus.put(3, getViewBalanceMenu());
        this.setSubmenus(submenus);
    }

    private Menu getViewBalanceMenu() {
        return new Menu("View Balance Menu", this) {
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
                PurchaserAccountController.processViewBalance();
            }
        };
    }

    private Menu getViewDiscountCodesMenu() {
        return new Menu("View Discount Codes Menu", this) {
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
                PurchaserAccountController.processViewDiscountCodes();
            }
        };
    }

}
