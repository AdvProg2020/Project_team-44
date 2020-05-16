package view.menu;

import controller.ManagerAccountController;
import exception.ProductIdNotExistsException;
import model.account.Account;

import java.util.HashMap;

public class ManageAllProductsMenu extends Menu {
    public ManageAllProductsMenu(Menu parent, Account account) {
        super("Manage All Products Menu", parent, account);
        HashMap<Integer, Menu> submenus = new HashMap<>();
        submenus.put(1, getRemoveProductMenu());
        this.setSubmenus(submenus);
    }

    private Menu getRemoveProductMenu() {
        return new Menu("Remove Product Menu", this, this.getCurrentUserLoggedIn()) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Please enter the product Id:");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("back"))
                    this.backInExecute();
                else if (input.equalsIgnoreCase("logout") && this.getCurrentUserLoggedIn() != null)
                    this.logoutInExecute();
                else if (!input.matches("remove \\w+"))
                    this.invalidCommandInExecute();
                else {
                    String productId = input.substring(7);
                    try {
                        ManagerAccountController.processRemoveProductEach(productId);
                        System.out.println("delete product successful");
                        this.execute();
                    } catch (ProductIdNotExistsException productError) {
                        System.out.println(productError.getMessage());
                        this.execute();
                    }

                }
            }
        };
    }
}
