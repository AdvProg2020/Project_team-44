package view.menu;

import controller.LoginPageController;
import view.menu.managerRegion.ManagerAccountMenu;
import view.menu.productsPage.ProductsPageMenu;
import view.menu.purchaseRegion.PurchaserAccountMenu;
import view.menu.sellerRegion.SellerAccountMenu;

import java.util.HashMap;

public class MainMenu extends Menu {

    public MainMenu(Menu parent) {
        super("Main Menu", parent);
        HashMap<Integer, Menu> submenus = new HashMap<>();
        submenus.put(1, new ProductsPageMenu(this));
        submenus.put(2,new OffsMenu(this));
        this.setSubmenus(submenus);

    }
    @Override
    public void show(){
        System.out.println(this.getName()+":");
        System.out.println("1-"+this.getSubmenus().get(1).getName());
        System.out.println("2-"+this.getSubmenus().get(2).getName());
        if(LoginPageController.getLoggedInAccountType().equals("null"))
            System.out.println("3-Register And Login Menu");
        if(LoginPageController.getLoggedInAccountType().equals("seller"))
            System.out.println("3-Seller Account Menu");
        if(LoginPageController.getLoggedInAccountType().equals("purchaser"))
            System.out.println("3-Purchaser Account Menu");
        if(LoginPageController.getLoggedInAccountType().equals("manager"))
            System.out.println("3-Manager Account Menu");
        System.out.println("4-Exit");
    }
    @Override
    public void execute(){
        Menu nextMenu = null;
        String input = scanner.nextLine();
        if (!input.matches("\\d+")) {
            System.err.println("please choose a number for your menu!");
            this.execute();
        } else {
            int menuNumber = Integer.parseInt(input);
            if (menuNumber == 0 || menuNumber > 4) {
                System.err.println("your menu number is invalid!");
                this.execute();
            }
            else if(menuNumber==1)
                nextMenu = this.getSubmenus().get(1);
            else if(menuNumber == 2)
                nextMenu = this.getSubmenus().get(2);
            else if(menuNumber==4)
                System.exit(0);
            else{
                if(LoginPageController.getLoggedInAccountType().equals("null"))
                    nextMenu = new RegisterAndLoginMenu(this);
                else if(LoginPageController.getLoggedInAccountType().equals("seller"))
                    nextMenu = new SellerAccountMenu(this);
                else if(LoginPageController.getLoggedInAccountType().equals("purchaser"))
                    nextMenu = new PurchaserAccountMenu(this);
                else
                    nextMenu = new ManagerAccountMenu(this);
            }
            nextMenu.show();
            nextMenu.menuWork();
            nextMenu.execute();
        }
    }

}
