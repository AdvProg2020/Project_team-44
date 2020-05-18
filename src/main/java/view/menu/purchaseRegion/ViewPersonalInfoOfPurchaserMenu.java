package view.menu.purchaseRegion;

import controller.PurchaserAccountController;
import view.menu.Menu;

import java.util.HashMap;

public class ViewPersonalInfoOfPurchaserMenu extends Menu {
    public ViewPersonalInfoOfPurchaserMenu(Menu parent) {
        super("View Personal Info Of Purchaser Menu", parent);
        HashMap<Integer, Menu> submenus = new HashMap<>();
        submenus.put(1, getEditFieldMenu());
        submenus.put(2, new ViewCartMenu(this));
        this.setSubmenus(submenus);
    }

    @Override
    public void menuWork() {
        for (String purchaseInfo : PurchaserAccountController.processViewPersonalInfo()) {
            System.out.println(purchaseInfo);
        }
    }

    private Menu getEditFieldMenu() {
        return new Menu("Edit Field Menu", this) {

            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Please enter your field :");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("back"))
                    this.backInExecute();
                else if (!input.matches("edit-\\w+-\\w+"))
                    this.invalidCommandInExecute();
                else {
                    String field = input.split("-")[1];
                    String newValue = input.split("-")[2];
                    if (field.equals("username")) {
                        System.err.println("you can't edit username");
                        this.execute();
                    } else {
                        try {
                            PurchaserAccountController.processEditFieldEach(field, newValue);
                            System.out.println("your change done");
                            this.execute();
                        } catch () {

                        }
                    }
                }
            }
        };
    }
}
