package view.menu;

import controller.PurchaserAccountManager;
import exception.ProductIdNotExistsException;
import model.account.Account;

import java.util.HashMap;

public class ViewCartMenu extends Menu {
    public ViewCartMenu( Menu parent, Account account) {
        super("View Cart Menu", parent, account);
        HashMap<Integer , Menu> submenus = new HashMap<>();
        submenus.put(1,getShowProductsMenu());
        submenus.put(2,getViewProductMenu());
        submenus.put(3,getIncreaseProductMenu());
        this.setSubmenus(submenus);
    }
    @Override
    public void menuWork() {
        PurchaserAccountManager.processViewCart();
    }
    private Menu getShowProductsMenu(){
        return new Menu("Show Products Menu",this,this.getCurrentUserLoggedIn()) {
            @Override
            public void show() {
                System.out.println(this.getName()+":");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
                if(input.equals("show products")){
                   PurchaserAccountManager.processShowProductsEach();
                    this.execute();
                }
                else if (input.equalsIgnoreCase("back"))
                    this.backInExecute();
                else if (input.equalsIgnoreCase("logout") && getCurrentUserLoggedIn() != null)
                    this.logoutInExecute();
                else
                    invalidCommandInExecute();
            }
        };
    }
    private Menu getViewProductMenu(){
        return new Menu("View Product Menu",this,this.getCurrentUserLoggedIn()) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Please enter product Id :");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("back"))
                    this.backInExecute();
                else if (input.equalsIgnoreCase("logout") && getCurrentUserLoggedIn() != null)
                    this.logoutInExecute();
                else if (!input.matches("view \\w+"))
                    this.invalidCommandInExecute();
                else {
                    String productId = input.substring(5);
                    try {
                        PurchaserAccountManager.processViewProductsEach(productId);
                        this.execute();
                    } catch (ProductIdNotExistsException viewProductError) {
                        System.err.println(viewProductError.getMessage());
                        this.execute();
                    }
                }
            }
        };
    }
    private Menu getIncreaseProductMenu(){
        return new Menu("Increase Product Menu",this,this.getCurrentUserLoggedIn()) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Please enter product Id :");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("back"))
                    this.backInExecute();
                else if (input.equalsIgnoreCase("logout") && getCurrentUserLoggedIn() != null)
                    this.logoutInExecute();
                else if (!input.matches("increase \\w+"))
                    this.invalidCommandInExecute();
                else {
                    String productId = input.substring(9);
                    try {
                        PurchaserAccountManager.processIncreaseProductEach(productId);
                        this.execute();
                    } catch (ProductIdNotExistsException increaseProductError) {
                        System.err.println(increaseProductError.getMessage());
                        this.execute();
                    }
                }
            }
        };
    }
    private Menu getDecreaseProductMenu(){
        return new Menu("Decrease Product Menu",this,this.getCurrentUserLoggedIn()) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Please enter product Id :");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("back"))
                    this.backInExecute();
                else if (input.equalsIgnoreCase("logout") && getCurrentUserLoggedIn() != null)
                    this.logoutInExecute();
                else if (!input.matches("decrease \\w+"))
                    this.invalidCommandInExecute();
                else {
                    String productId = input.substring(9);
                    try {
                        PurchaserAccountManager.processDecreaseProductEach(productId);
                        this.execute();
                    } catch (ProductIdNotExistsException decreaseProductError) {
                        System.err.println(decreaseProductError.getMessage());
                        this.execute();
                    }
                }
            }
        };
    }

}
