package view.menu.userRegion.managerRegion;

import view.menu.Menu;

public class ManagerAccount extends Menu {
    private ManagerAccount() {

    }

    private static ManagerAccount singleton = new ManagerAccount();

    public static ManagerAccount getInstance() {
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
    private Menu viewPersonalInfo(){

    }
    private Menu edit(){

    }
    private Menu createDiscountCode(){

    }

}
