package view.menu.managerRegion;


import controller.ManagerAccountController;
import view.menu.*;
import view.menu.regexEnumForInput.CreateDiscountCodeRegex;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

public class ManagerAccountMenu extends Menu {
    public ManagerAccountMenu(Menu parent) {
        super("Manager Account Menu", parent);
        HashMap<Integer, Menu> submenus = new HashMap<>();
        submenus.put(1, new ViewPersonalInfoOfManagerMenu(this));
        submenus.put(2, new ManageUsersMenu(this));
        submenus.put(3, new ManageAllProductsMenu(this));
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
                System.out.println("Please enter new discount code information :");
            }

            @Override
            public void execute() {
                ArrayList<String> info = CreateDiscountCodeRegex.checkRegex(scanner);
                if (info == null) {
                    this.backInExecute();
                } else {
                    try {
                        ManagerAccountController.processCreateDiscountCode(info.get(0), info.get(1), Integer.parseInt(info.get(2)), Integer.parseInt(info.get(3)));
                        System.out.println("Create discount code successful");
                        this.execute();
                    } catch (ParseException createDiscountCodeError) {
                        System.err.println(createDiscountCodeError.getMessage());
                        this.execute();
                    }
                }
            }
        };
    }
}
