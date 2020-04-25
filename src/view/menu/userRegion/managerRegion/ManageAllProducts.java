package view.menu.userRegion.managerRegion;

import view.menu.Menu;

public class ManageAllProducts extends Menu {
    private ManageAllProducts() {

    }

    private static ManageAllProducts singleton = new ManageAllProducts();

    public static ManageAllProducts getInstance() {
        return singleton;
    }
    @Override
    public void show() {
        super.show();
    }

    @Override
    public void execute() {
        super.execute();
    }
    private Menu remove(){

    }
}
