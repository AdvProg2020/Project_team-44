package view.menu;

public class SellerAccountMenu extends Menu {
    public SellerAccountMenu(Menu parent) {
        super("Seller Account Menu", parent);
        submenus.put(1, new ViewPersonalInfoOfSellerMenu(this));
        submenus.put(2, getViewCompanyInformationMenu());
        submenus.put(3, getViewSaleHistoryMenu());
        submenus.put(4,new ManagerProductsForSellerMenu(this));


    }

    private Menu getViewCompanyInformationMenu() {
        return new Menu("View Company Information Menu", this) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
            }
        };
    }

    private Menu getViewSaleHistoryMenu() {
        return new Menu("View Sale History Menu", this) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
            }
        };
    }
}
