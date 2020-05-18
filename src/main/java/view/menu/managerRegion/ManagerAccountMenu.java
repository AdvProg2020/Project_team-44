package view.menu.managerRegion;


import controller.ManagerAccountController;
import exception.UsernameNotExistsException;
import view.menu.*;

import java.util.HashMap;

public class ManagerAccountMenu extends Menu {
    public ManagerAccountMenu(Menu parent) {
        super("Manager Account Menu", parent);
        HashMap<Integer, Menu> submenus = new HashMap<>();
        submenus.put(1, new ViewPersonalInfoOfManagerMenu(this));
        submenus.put(2, new ManageUsersMenu(this));
        submenus.put(3, new ManageAllProductsMenu(this);
        submenus.put(4, getCreateDiscountCodeMenu());
        submenus.put(5, new ViewDiscountCodesMenu(this));
        submenus.put(6, new ManageRequestsMenu(this));
        submenus.put(7, new ManageCategoriesMenu(this));
        this.setSubmenus(submenus);

    }

    private Menu getCreateDiscountCodeMenu() {
        return new Menu("Create Discount Code Menu", this) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Please enter the discount code information");
            }

            @Override
            public void execute() {
                String InitialDate = scanner.nextLine();
                String FinalDate = scanner.next();
                int discountPersent = scanner.nextLine()
                if (.equalsIgnoreCase("back"))
                this.backInExecute();
                else if (!input.matches("create discount cod"))
                    this.invalidCommandInExecute();
                else {
                    String userName = input.substring(5);
                    try {
                        ManagerAccountController.processViewUserInfoEach(userName);
                        System.out.println("done");
                        this.execute();
                    } catch (UsernameNotExistsException userNameError) {
                        System.out.println(userNameError.getMessage());
                        this.execute();
                    }

                }
            }
        };
    }
}
