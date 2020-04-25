package view.menu.userRegion.purchaserRegion;

import view.menu.Menu;

public class ViewOrders extends Menu {
    private ViewOrders() {

    }

    private static ViewOrders singleton = new ViewOrders();

    public static ViewOrders getInstance() {
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
    private Menu showOrder(){

    }
    private Menu rate(){

    }
}
