package view.menu.productPage;

import view.menu.Menu;

public class Digest extends Menu {
    private Menu addToCart(){

    }
    private Menu selectSeller(){

    }
    @Override
    public void show() {
        super.show();
    }

    @Override
    public void execute() {
        super.execute();
    }

    private Digest() {

    }

    private static Digest singleton = new Digest();

    public static Digest getInstance() {
        return singleton;
    }

}
