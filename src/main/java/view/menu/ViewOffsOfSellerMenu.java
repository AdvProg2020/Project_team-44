package view.menu;

import controller.SellerAccountController;
import exception.ProductIdNotExistsException;

import java.util.HashMap;

public class ViewOffsOfSellerMenu extends Menu {
    public ViewOffsOfSellerMenu(Menu parent) {
        super("View Offs Of Seller Menu", parent);
        HashMap<Integer, Menu> submenus = new HashMap<>();
        submenus.put(1, getViewOffMenu());
        submenus.put(2, getEditOffMenu());
        submenus.put(3, getAddOffMenu());
        this.setSubmenus(submenus);
    }

    @Override
    public void menuWork() {
        SellerAccountController.processViewOffs();
    }

    private Menu getViewOffMenu() {
        return new Menu("View Off Menu", this) {
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
                else if (!input.matches("view \\w+"))
                    this.invalidCommandInExecute();
                else {
                    String offId = input.substring(5);
                    try {
                        SellerAccountController.processViewOffEach(offId);
                        this.execute();
                    } catch (ProductIdNotExistsException viewOffError) {
                        System.err.println(viewOffError.getMessage());
                        this.execute();
                    }

                }
            }
        };
    }

    private Menu getEditOffMenu() {
        return new Menu("Edit Off Menu", this) {
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
                else if (!input.matches("view \\w+"))
                    this.invalidCommandInExecute();
                else {
                    String offId = input.substring(5);
                    try {
                        SellerAccountController.processViewOffEach(offId);
                        this.execute();
                    } catch (ProductIdNotExistsException viewOffError) {
                        System.err.println(viewOffError.getMessage());
                        this.execute();
                    }

                }
            }
        };
    }

    private Menu getAddOffMenu() {
        return new Menu("Add Off Menu", this) {
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
                else if (!input.matches("view \\w+"))
                    this.invalidCommandInExecute();
                else {
                    String offId = input.substring(5);
                    try {
                        SellerAccountController.processViewOffEach(offId);
                        this.execute();
                    } catch (ProductIdNotExistsException viewOffError) {
                        System.err.println(viewOffError.getMessage());
                        this.execute();
                    }

                }
            }
        };
    }
}
