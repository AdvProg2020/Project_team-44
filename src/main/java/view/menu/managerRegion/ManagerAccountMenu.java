package view.menu.managerRegion;


import controller.ManagerAccountController;
import view.menu.*;

import java.text.ParseException;
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
                System.out.println("Please enter the initial date :");
                String initialDate = scanner.nextLine();
                if (initialDate.equalsIgnoreCase("back"))
                    this.backInExecute();
                if (!initialDate.matches("^([0-2][0-9]|(3)[0-1])(\\/)(((0)[0-9])|((1)[0-2]))(\\/)\\d{4}$"))
                    this.invalidCommandInExecute();
                System.out.println("Please enter the final date :");
                String finalDate = scanner.next();
                if (finalDate.equalsIgnoreCase("back"))
                    this.backInExecute();
                if (!finalDate.matches("[1-31]/[1-12]/\\d{4}"))
                    this.invalidCommandInExecute();
                System.out.println("Please enter your discount percentage :");
                String inputPercentage = scanner.nextLine();
                if (inputPercentage.equalsIgnoreCase("back"))
                    this.backInExecute();
                if (!inputPercentage.matches("[1-100]"))
                    this.invalidCommandInExecute();
                int discountPercentage = Integer.parseInt(inputPercentage);
                System.out.println("Please enter the max price :");
                String inputMaxPrice = scanner.nextLine();
                if (inputMaxPrice.equalsIgnoreCase("back"))
                    this.backInExecute();
                if (!inputMaxPrice.matches("\\d+"))
                    this.invalidCommandInExecute();
                int maxPrice = Integer.parseInt(inputMaxPrice);
                try {
                    ManagerAccountController.processCreateDiscountCode(initialDate, finalDate, discountPercentage, maxPrice);
                    System.out.println("create discount code successful");
                    this.execute();
                } catch (ParseException createDiscountCodeError) {
                    System.err.println(createDiscountCodeError.getMessage());
                    this.execute();
                }

            }
        };
    }
}
