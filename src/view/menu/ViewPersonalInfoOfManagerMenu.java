package view.menu;

import model.account.Account;

import java.util.HashMap;

public class ViewPersonalInfoOfManagerMenu extends Menu {
    public ViewPersonalInfoOfManagerMenu(Menu parent, Account account) {
        super("View Personal Info Of Manager Menu", parent, account);
        HashMap<Integer, Menu> submenus = new HashMap<>();
        submenus.put(1, getEditFieldMenu());
        this.setSubmenus(submenus);
    }

    @Override
    public void show() {

    }

    private Menu getEditFieldMenu() {
        return new Menu("Edit Field Menu", this, this.getCurrentUser()) {

            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Please enter your field :");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();

            }
        };
    }
}
