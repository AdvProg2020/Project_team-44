package view.menu.userRegion.managerRegion;

import view.menu.Menu;

public class ManageRequests extends Menu {
    private ManageRequests() {

    }

    private static ManageRequests singleton = new ManageRequests();

    public static ManageRequests getInstance() {
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

    private Menu detailsRequest() {

    }

    private Menu acceptRequest() {

    }

    private Menu declineRequest() {

    }
}
