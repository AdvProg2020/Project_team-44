package view.menu;

import controller.ProductsPageController;
import exception.ProductIdNotExistsException;
import view.menu.Menu;

import java.util.HashMap;

public class ShowProductsMenu extends Menu {
    public ShowProductsMenu(Menu parent) {
        super("Show Products Menu", parent);
        HashMap<Integer, Menu> submenus = new HashMap<>();
        submenus.put(1, getShowProductMenu());
        this.setSubmenus(submenus);
    }

    @Override
    public void menuWork() {
        ProductsPageController.processShowProducts();
    }

    private Menu getShowProductMenu() {
        return new Menu("Show Product Menu", this) {

            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Please enter your productId :");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("back"))
                    this.backInExecute();
                else if (!input.matches("show product \\w+"))
                    this.invalidCommandInExecute();
                else {
                    String productId = input.substring(13);
                    try {
                        ProductsPageController.processShowProduct(productId);
                        Menu productMenu = new Pro
                    } catch (ProductIdNotExistsException showProductError) {
                        System.out.println(showProductError.getMessage());
                        this.execute();
                    }

                }
            }
        };
    }
}
