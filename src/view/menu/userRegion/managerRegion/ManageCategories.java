package view.menu.userRegion.managerRegion;

import view.menu.Menu;

public class ManageCategories extends Menu {
    private ManageCategories() {

    }

    private static ManageCategories singleton = new ManageCategories();

    public static ManageCategories getInstance() {
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
    private Menu editCategory(){

    }
    private Menu removeCategory(){

    }
    private Menu addCategory(){

    }
}
