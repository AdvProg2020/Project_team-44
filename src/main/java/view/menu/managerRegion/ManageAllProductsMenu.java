package view.menu.managerRegion;

import controller.ManagerAccountController;
import controller.ProductsPageController;
import exception.ProductIdNotExistsException;
import view.menu.Menu;

import java.util.HashMap;

public class ManageAllProductsMenu extends Menu {
    public ManageAllProductsMenu(Menu parent) {
        super("Manage All Products Menu", parent);
        HashMap<Integer, Menu> submenus = new HashMap<>();
        submenus.put(1, getRemoveProductMenu());
        this.setSubmenus(submenus);
    }

    private Menu getRemoveProductMenu() {
        return new Menu("Remove Product Menu", this) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Please enter the product Id:");
            }

            @Override
            public void execute() {
                int counter = 1;
                for (String product : ProductsPageController.processShowProducts()) {
                    System.out.println(counter + "- " + product);
                    counter++;
                }
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("back"))
                    this.backInExecute();
                else if (!input.matches("remove \\w+"))
                    this.invalidCommandInExecute();
                else {
                    String productId = input.substring(7);
                    try {
                        ManagerAccountController.processRemoveProductEach(productId);
                        System.out.println("remove product successful");
                        this.execute();
                    } catch (ProductIdNotExistsException productError) {
                        System.err.println(productError.getMessage());
                        this.execute();
                    }

                }
            }
        };
    }
}
