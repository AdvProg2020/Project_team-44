package view.menu.productsPage;

import controller.ProductsPageController;
import view.menu.Menu;

import java.util.HashMap;

public class ProductsPageMenu extends Menu {
    public ProductsPageMenu(Menu parent) {
        super("Products Page Menu", parent);
        HashMap<Integer, Menu> submenus = new HashMap<>();
        submenus.put(1, getViewCategoriesMenu());
        submenus.put(2, new FilteringInProductsPageMenu(this));
        submenus.put(3, new SortingInProductsPageMenu(this));
        submenus.put(4, new ShowProductsInProductsPageMenu(this));
        this.setSubmenus(submenus);
    }

    private Menu getViewCategoriesMenu() {
        return new Menu("View Categories Menu", this) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("back"))
                    this.backInExecute();
                else
                    this.invalidCommandInExecute();
            }

            @Override
            public void menuWork() {
                int i = 1;
                for (String viewCategory : ProductsPageController.processViewCategories()) {
                    System.out.println(i + "- " + viewCategory);
                    i++;
                }
            }
        };
    }
}
