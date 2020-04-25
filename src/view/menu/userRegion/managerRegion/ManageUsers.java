package view.menu.userRegion.managerRegion;

import view.menu.Menu;

public class ManageUsers extends Menu {
    private ManageUsers() {

    }

    private static ManageUsers singleton = new ManageUsers();

    public static ManageUsers getInstance() {
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

    private Menu view() {

    }

    private Menu changeType() {

    }

    private Menu deleteUser() {

    }

    private Menu createMangerProfile() {

    }
}
