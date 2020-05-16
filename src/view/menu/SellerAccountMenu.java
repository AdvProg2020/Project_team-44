package view.menu;

import controller.SellerAccountManager;
import model.account.Account;

import java.util.HashMap;

public class SellerAccountMenu extends Menu {
    public SellerAccountMenu(Menu parent, Account account) {
        super("Seller Account Menu", parent, account);
        HashMap<Integer, Menu> submenus = new HashMap<>();
        submenus.put(1, new ViewPersonalInfoOfSellerMenu(this, account));
        submenus.put(2, getViewCompanyInformationMenu());
        submenus.put(3, getViewSaleHistoryMenu());
        submenus.put(4, new ManageProductsForSellerMenu(this, account));
        this.setSubmenus(submenus);


    }

    private Menu getViewCompanyInformationMenu() {
        return new Menu("View Company Information Menu", this, this.getCurrentUserLoggedIn()) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
            }
            @Override
            public void menuWork(){
                SellerAccountManager.processViewCompanyInfo();
            }
            @Override
            public void execute(){
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("back"))
                    this.backInExecute();
                else if (input.equalsIgnoreCase("logout") && this.getCurrentUserLoggedIn() != null)
                    this.logoutInExecute();
                else
                    this.invalidCommandInExecute();
            }
        };
    }

    private Menu getViewSaleHistoryMenu() {
        return new Menu("View Sale History Menu", this, this.getCurrentUserLoggedIn()) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
            }
            @Override
            public void menuWork(){
                SellerAccountManager.processViewSalesHistory();
            }
            @Override
            public void execute(){
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("back"))
                    this.backInExecute();
                else if (input.equalsIgnoreCase("logout") && this.getCurrentUserLoggedIn() != null)
                    this.logoutInExecute();
                else
                    this.invalidCommandInExecute();
            }
        };
    }
}
