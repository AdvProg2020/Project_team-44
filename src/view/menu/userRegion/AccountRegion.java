package view.menu.userRegion;

import view.menu.Menu;

public class AccountRegion extends Menu {

    private Menu createAccount() {

    }

    private Menu login() {

    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void execute() {
        super.execute();
    }

    private AccountRegion() {

    }

    private static AccountRegion singleton = new AccountRegion();

    public static AccountRegion getInstance() {
        return singleton;
    }
}
