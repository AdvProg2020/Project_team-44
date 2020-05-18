package view.menu.productsPage;

import controller.ProductsPageController;
import exception.ProductIdNotExistsException;
import view.menu.Menu;
import view.menu.productsPage.ProductPage.ProductPageMenu;

import java.util.HashMap;

public class ShowProductsInProductsPageMenu extends Menu {
    public ShowProductsInProductsPageMenu(Menu parent) {
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
                        Menu productMenu = new ProductPageMenu(this);
                        productMenu.show();
                        productMenu.menuWork();
                        productMenu.execute();
                    } catch (ProductIdNotExistsException showProductError) {
                        System.err.println(showProductError.getMessage());
                        this.execute();
                    }

                }
            }
        };
    }
}