package view.menu.sellerRegion;

import controller.SellerAccountController;
import view.menu.Menu;

import java.util.HashMap;

public class SellerAccountMenu extends Menu {
    public SellerAccountMenu(Menu parent) {
        super("Seller Account Menu", parent);
        HashMap<Integer, Menu> submenus = new HashMap<>();
        submenus.put(1, new ViewPersonalInfoOfSellerMenu(this));
        submenus.put(2, getViewCompanyInformationMenu());
        submenus.put(3, getViewSaleHistoryMenu());
        submenus.put(4, new ManageProductsForSellerMenu(this));
        submenus.put(5, getShowCategoriesMenu());
        submenus.put(6, new ViewOffsOfSellerMenu(this));
        submenus.put(7, getViewBalanceMenu());
        this.setSubmenus(submenus);


    }

    private Menu getViewCompanyInformationMenu() {
        return new Menu("View Company Information Menu", this) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
            }

            @Override
            public void menuWork() {
                for (String companyInfo : SellerAccountController.processViewCompanyInfo()) {
                    System.out.println(companyInfo);
                }
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("back"))
                    this.backInExecute();
                else
                    this.invalidCommandInExecute();
            }
        };
    }

    private Menu getViewSaleHistoryMenu() {
        return new Menu("View Sale History Menu", this) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
            }

            @Override
            public void menuWork() {
                int i = 1;
                for (String saleHistory : SellerAccountController.processViewSalesHistory()) {
                    System.out.println(i + "- " + saleHistory);
                    i++;
                }
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("back"))
                    this.backInExecute();
                else
                    this.invalidCommandInExecute();
            }
        };
    }

    private Menu getShowCategoriesMenu() {
        return new Menu("Show Categories Menu", this) {
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
                for (String category : SellerAccountController.processShowCategory()) {
                    System.out.println(i + "- " + category);
                    i++;
                }
            }
        };
    }

    private Menu getViewBalanceMenu() {
        return new Menu("View Balance Menu", this) {
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
                System.out.println("your balance : " + SellerAccountController.processViewBalance());
            }
        };
    }
}
