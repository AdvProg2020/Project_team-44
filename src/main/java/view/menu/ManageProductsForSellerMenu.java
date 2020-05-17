package view.menu;

import controller.LoginPageController;
import controller.SellerAccountController;
import exception.ProductIdNotExistsException;

import java.util.HashMap;

public class ManageProductsForSellerMenu extends Menu {
    public ManageProductsForSellerMenu(Menu parent) {
        super("Manager Products For Seller Menu", parent);
        HashMap<Integer, Menu> submenus = new HashMap<Integer, Menu>();
        submenus.put(1, getViewProductMenu());
        submenus.put(2, getViewBuyersOfProductMenu());
        submenus.put(3, getEditProductMenu());
        submenus.put(4, getAddProductMenu());
        submenus.put(5, getRemoveProductMenu());
        this.setSubmenus(submenus);
    }

    @Override
    public void menuWork() {
        SellerAccountController.processManageProducts();
    }

    private Menu getViewProductMenu() {
        return new Menu("View Product Menu", this) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Please enter your product Id:");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("back"))
                    this.backInExecute();
                else if (!input.matches("view \\w+"))
                    this.invalidCommandInExecute();
                else {
                    String productId = input.substring(5);
                    try {
                        SellerAccountController.processViewProductEach(productId);
                        this.execute();
                    } catch (ProductIdNotExistsException viewProductError) {
                        System.out.println(viewProductError.getMessage());
                        this.execute();
                    }
                }
            }
        };
    }

    private Menu getViewBuyersOfProductMenu() {
        return new Menu("View Buyers Of Product Menu", this) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Please enter your product Id:");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("back"))
                    this.backInExecute();
                else if (!input.matches("view buyers \\w+"))
                    this.invalidCommandInExecute();
                else {
                    String productId = input.substring(12);
                    try {
                        SellerAccountController.processViewBuyersEach(productId);
                        this.execute();
                    } catch (ProductIdNotExistsException viewBuyersOfProductError) {
                        System.out.println(viewBuyersOfProductError.getMessage());
                        this.execute();
                    }
                }
            }
        };
    }

    private Menu getEditProductMenu() {
        return new Menu("Edit Product Menu", this) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Please enter your product Id:");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("back"))
                    this.backInExecute();
                else if (!input.matches("view buyers \\w+"))
                    this.invalidCommandInExecute();
                else {
                    String productId = input.substring(12);
                    try {
                        SellerAccountController.processViewBuyersEach(productId);
                        this.execute();
                    } catch (ProductIdNotExistsException viewBuyersOfProductError) {
                        System.out.println(viewBuyersOfProductError.getMessage());
                        this.execute();
                    }
                }
            }
        };
    }

    private Menu getAddProductMenu() {
        return new Menu("Add Product Menu", this) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Please enter product Id:");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("back"))
                    this.backInExecute();
                else if (!input.matches("remove product \\w+"))
                    this.invalidCommandInExecute();
                else {
                    String productId = input.substring(15);
                    try {
                        SellerAccountController.processRemoveProduct(productId);
                    } catch (ProductIdNotExistsException RemoveProductError) {
                        System.out.println(RemoveProductError.getMessage());
                        this.execute();
                    }
                }
            }
        };
    }

    private Menu getRemoveProductMenu() {
        return new Menu("Remove Product Menu", this) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Please enter product Id:");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("back"))
                    this.backInExecute();
                else if (!input.matches("remove product \\w+"))
                    this.invalidCommandInExecute();
                else {
                    String productId = input.substring(15);
                    try {
                        SellerAccountController.processRemoveProduct(productId);
                    } catch (ProductIdNotExistsException RemoveProductError) {
                        System.out.println(RemoveProductError.getMessage());
                        this.execute();
                    }
                }
            }
        };
    }
}
