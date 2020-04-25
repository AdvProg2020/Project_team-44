package view.menu.productPage;

import view.menu.Menu;

public class Comments extends Menu {
    private Menu addComment(){

    }
    @Override
    public void show() {
        super.show();
    }

    @Override
    public void execute() {
        super.execute();
    }

    private Comments() {

    }

    private static Comments singleton = new Comments();

    public static Comments getInstance() {
        return singleton;
    }
}
