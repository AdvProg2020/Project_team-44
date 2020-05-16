package view.menu;


import controller.ManagerAccountController;
import exception.UsernameNotExistsException;
import model.account.Account;
import java.util.HashMap;

public class ManagerUsersMenu extends Menu {
    public ManagerUsersMenu(Menu parent, Account account) {
        super("Manager Users Menu", parent, account);
        HashMap<Integer, Menu> submenus = new HashMap<>();
        submenus.put(1, getViewOfUserMenu());
        submenus.put(2, getDeleteUserMenu());
        submenus.put(3, getCreateManagerProfileMenu());
        this.setSubmenus(submenus);
    }

    @Override
    public void menuWork() {
        ManagerAccountController.processManageUsers();
    }

    private Menu getViewOfUserMenu() {
        return new Menu("View Of User Menu", this, this.getCurrentUserLoggedIn()) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Please enter the username:");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("back"))
                    this.backInExecute();
                else if (input.equalsIgnoreCase("logout") && this.getCurrentUserLoggedIn() != null)
                    this.logoutInExecute();
                else if (!input.matches("view \\w+"))
                    this.invalidCommandInExecute();
                else {
                    String userName = input.substring(5);
                    try {
                        ManagerAccountController.processViewUserInfoEach(userName);
                    } catch (UsernameNotExistsException userNameError) {
                        userNameError.getMessage();
                        this.execute();
                    }

                }
            }
        };
    }

    private Menu getDeleteUserMenu() {
        return new Menu("Delete User Menu", this, this.getCurrentUserLoggedIn()) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Please enter the username");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("back"))
                    this.backInExecute();
                else if (input.equalsIgnoreCase("logout") && this.getCurrentUserLoggedIn() != null)
                    this.logoutInExecute();
                else if (!input.matches("delete user \\w+"))
                    this.invalidCommandInExecute();
                else {
                    String userName = input.substring(12);
                    try {
                        ManagerAccountController.processDeleteUserEach(userName);
                        System.out.println("delete user successful");
                    } catch (UsernameNotExistsException userNameError) {
                        userNameError.getMessage();
                        this.execute();
                    }

                }
            }
        };
    }

    private Menu getCreateManagerProfileMenu() {
        return new Menu("Create Manager Profile Menu", this, this.getCurrentUserLoggedIn()) {
            @Override
            public void show() {

            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
            }
        };
    }

}
