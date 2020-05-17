package view.menu;

import controller.ProductsPageController;
import controller.PurchaserAccountManager;
import model.account.Account;

import java.util.HashMap;

public class ProductPageMenu extends Menu {
    public ProductPageMenu(Menu parent, Account account) {
        super("ProductPage", parent, account);
        HashMap<Integer, Menu> submenus = new HashMap<>();
        submenus.put(1,getViewCategoriesMenu());
        submenus.put(2,new FilteringMenu(this,account));
        submenus.put(3,new SortingMenu(this,account));
        this.setSubmenus(submenus);
    }
    private Menu getViewCategoriesMenu(){
        return new Menu("View Categories Menu",this,this.getCurrentUserLoggedIn()) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("back"))
                    this.backInExecute();
                else if (input.equalsIgnoreCase("logout") && this.getCurrentUserLoggedIn() != null)
                    this.logoutInExecute();
                else
                    this.invalidCommandInExecute();
            }
            @Override
            public void menuWork(){
                ProductsPageController.processViewCategories();
            }
        };
    }
}
