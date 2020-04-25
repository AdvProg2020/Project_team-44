package view.menu.productPage;

import view.menu.Menu;

public class Product extends Menu {
    private Menu attributes() {

    }

    private Menu compare() {

    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void execute() {
        super.execute();
    }

    private Product() {

    }

    private static Product singleton = new Product();

    public static Product getInstance() {
        return singleton;
    }

}
