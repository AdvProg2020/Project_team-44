package view.menu;

import controller.LoginPageController;
import view.menu.managerRegion.ManagerAccountMenu;
import view.menu.offsPage.OffsMenu;
import view.menu.productsPage.ProductsPageMenu;
import view.menu.purchaseRegion.PurchaserAccountMenu;
import view.menu.sellerRegion.SellerAccountMenu;

import java.util.HashMap;

public class MainMenu extends Menu {

    public MainMenu(Menu parent) {
        super("Main Menu", parent);
        HashMap<Integer, Menu> submenus = new HashMap<>();
        submenus.put(1, new ProductsPageMenu(this));
        submenus.put(2, new OffsMenu(this));
        this.setSubmenus(submenus);

    }

    @Override
    public void show() {
        System.out.println(this.getName() + ":");
        System.out.println("1-" + this.getSubmenus().get(1).getName());
        System.out.println("2-" + this.getSubmenus().get(2).getName());
        if (LoginPageController.getLoggedInAccountType().equals("null"))
            System.out.println("3-Register And Login Menu");
        if (LoginPageController.getLoggedInAccountType().equals("seller"))
            System.out.println("3-Seller Account Menu");
        if (LoginPageController.getLoggedInAccountType().equals("purchaser"))
            System.out.println("3-Purchaser Account Menu");
        if (LoginPageController.getLoggedInAccountType().equals("manager"))
            System.out.println("3-Manager Account Menu");
        System.out.println("4-Exit");
        if (!LoginPageController.getLoggedInAccountType().equals("null"))
            System.out.println("5-logout");
    }

    @Override
    public void execute() {
        Menu nextMenu = null;
        String input = scanner.nextLine();
        if (!input.matches("\\d+")) {
            System.err.println("please choose a number for your menu!");
            this.execute();
        } else {
            int menuNumber = Integer.parseInt(input);
            if (menuNumber == 0 || menuNumber > 5 ||
                    (menuNumber == 5 && LoginPageController.getLoggedInAccountType().equals("null"))) {
                System.err.println("your menu number is invalid!");
                this.execute();
            } else if (menuNumber == 1)
                nextMenu = this.getSubmenus().get(1);
            else if (menuNumber == 2)
                nextMenu = this.getSubmenus().get(2);
            else if (menuNumber == 4)
                System.exit(0);
            else if (menuNumber == 5) {
                nextMenu = new MainMenu(null);
                LoginPageController.logout();
            } else {
                switch (LoginPageController.getLoggedInAccountType()) {
                    case "null":
                        nextMenu = new RegisterAndLoginMenu(this);
                        break;
                    case "seller":
                        nextMenu = new SellerAccountMenu(this);
                        break;
                    case "purchaser":
                        nextMenu = new PurchaserAccountMenu(this);
                        break;
                    default:
                        nextMenu = new ManagerAccountMenu(this);
                        break;
                }
            }
            nextMenu.show();
            nextMenu.menuWork();
            nextMenu.execute();
        }
    }

}
