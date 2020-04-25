package view.menu.userRegion.sellerRegion;

import view.menu.Menu;

public class SellerAccount extends Menu {
    private SellerAccount() {

    }

    private static SellerAccount singleton = new SellerAccount();

    public static SellerAccount getInstance() {
        return singleton;
    }
    private Menu viewCompanyInformation(){

    }
    private Menu viewSalesHistory(){

    }
    private Menu addProduct(){

    }
    private Menu removeProduct(){

    }
    private Menu showCategories(){

    }
    private Menu viewBalance(){

    }
    @Override
    public void show() {
        super.show();
    }

    @Override
    public void execute() {
        super.execute();
    }

}
