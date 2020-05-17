package view.menu;

import controller.LoginPageController;
import controller.ProductsPageController;

import java.util.HashMap;

public class ProductsPageMenu extends Menu {
    public ProductsPageMenu(Menu parent) {
        super("Products Page Menu", parent);
        HashMap<Integer, Menu> submenus = new HashMap<>();
        submenus.put(1, getViewCategoriesMenu());
        submenus.put(2, new FilteringMenu(this));
        submenus.put(3, new SortingMenu(this));
        submenus.put(4, new ShowProductsMenu(this));
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
                ProductsPageController.processViewCategories();
            }
        };
    }
}
