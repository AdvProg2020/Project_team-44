package view.menu.sellerRegion;

import controller.SellerAccountController;
import exception.ProductIdNotExistsException;
import view.menu.Menu;

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
        int i = 1;
        for (String viewOff : SellerAccountController.processViewOffs()) {
            System.out.println(i + "- " + viewOff);
            i++;
        }
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
                        for (String viewOffEach : SellerAccountController.processViewOffEach(offId)) {
                            System.out.println(viewOffEach);
                        }
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
                String offId = scanner.nextLine();
                if (offId.equalsIgnoreCase("back"))
                    this.backInExecute();
                System.out.println("Please enter your field");
                String field = scanner.nextLine();
                if (field.equalsIgnoreCase("back"))
                    this.backInExecute();
                System.out.println("Please enter your old value");
                String oldValue = scanner.nextLine();
                if (oldValue.equalsIgnoreCase("back"))
                    this.backInExecute();
                System.out.println("Please enter your new value");
                String newValue = scanner.nextLine();
                if (newValue.equalsIgnoreCase("back"))
                    this.backInExecute();
                try {
                    SellerAccountController.processEditOffEach(offId,field,oldValue,newValue);
                    this.execute();
                } catch (ProductIdNotExistsException EditOffError) {
                    System.err.println(EditOffError.getMessage());
                    this.execute();
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
