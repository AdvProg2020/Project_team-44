package view.menu;

import controller.PurchaserAccountManager;
import model.account.Account;

import java.util.HashMap;

public class PurchaserAccountMenu extends Menu {
    public PurchaserAccountMenu(Menu parent, Account account) {
        super("Purchaser Account Menu", parent, account);
        HashMap<Integer , Menu> submenus = new HashMap<>();
        submenus.put(1,new ViewPersonalInfoOfPurchaserMenu(this,account));
        submenus.put(2,new ViewOrdersMenu(this,account));
        submenus.put(3,getViewBalanceMenu());
        this.setSubmenus(submenus);
    }
    private Menu getViewBalanceMenu(){
        return new Menu("View Balance Menu",this,this.getCurrentUserLoggedIn()) {
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
                PurchaserAccountManager.processViewBalance();
            }
        };
    }
    private Menu getViewDiscountCodesMenu(){
        return new Menu("View Discount Codes Menu",this,this.getCurrentUserLoggedIn()) {
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
                PurchaserAccountManager.processViewDiscountCodes();
            }
        };
    }

}
