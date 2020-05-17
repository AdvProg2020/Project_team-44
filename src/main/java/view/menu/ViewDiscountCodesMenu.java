package view.menu;

import controller.ManagerAccountController;
import exception.CodedDiscountNotExistsException;

import java.util.HashMap;

public class ViewDiscountCodesMenu extends Menu {
    public ViewDiscountCodesMenu(Menu parent) {
        super("View Discount Codes Menu", parent);
        HashMap<Integer, Menu> submenus = new HashMap<>();
        submenus.put(1, getViewOfDiscountCodeMenu());
        submenus.put(2, getEditDiscountCodeMenu());
        submenus.put(3, getRemoveDiscountCodeMenu());
        this.setSubmenus(submenus);
    }

    @Override
    public void menuWork() {
        ManagerAccountController.processViewDiscountCodes();
    }

    private Menu getViewOfDiscountCodeMenu() {
        return new Menu("View Of Discount Code Menu", this) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Please enter your code:");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("back"))
                    this.backInExecute();
                else if (!input.matches("view discount code \\w+"))
                    this.invalidCommandInExecute();
                else {
                    String code = input.substring(19);
                    try {
                        ManagerAccountController.processViewDiscountCodeEach(code);
                        this.execute();
                    } catch (CodedDiscountNotExistsException discountCodeError) {
                        System.out.println(discountCodeError.getMessage());
                        this.execute();
                    }

                }
            }
        };
    }

    private Menu getEditDiscountCodeMenu() {
        return new Menu("Edit Discount Code Menu", this) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Please enter your code:");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("back"))
                    this.backInExecute();
                else if (!input.matches("edit discount code \\w+-\\w+-\\w+"))
                    this.invalidCommandInExecute();
                else {
                    String newInput = input.substring(19);
                    String code = newInput.split("-")[0];
                    String field = newInput.split("-")[1];
                    String newValue = newInput.split("-")[2];
                    try {
                        ManagerAccountController.processEditDiscountCodeEach(code, field, newValue);
                        System.out.println("edit discount code successful");
                        this.execute();
                    } catch (CodedDiscountNotExistsException editDiscountCodeError) {
                        System.out.println(editDiscountCodeError.getMessage());
                        this.execute();
                    }

                }
            }
        };
    }

    private Menu getRemoveDiscountCodeMenu() {
        return new Menu("Remove Discount Code Menu", this) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Please enter your code:");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("back"))
                    this.backInExecute();
                else if (!input.matches("remove discount code \\w+"))
                    this.invalidCommandInExecute();
                else {
                    String code = input.substring(21);
                    try {
                        ManagerAccountController.processRemoveDiscountCodeEach(code);
                        System.out.println("remove discount code successful");
                        this.execute();
                    } catch (CodedDiscountNotExistsException removeDiscountCodeError) {
                        System.out.println(removeDiscountCodeError.getMessage());
                        this.execute();
                    }

                }
            }
        };
    }
}
