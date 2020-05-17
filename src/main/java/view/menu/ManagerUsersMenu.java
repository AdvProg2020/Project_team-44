package view.menu;


import controller.LoginPageController;
import controller.ManagerAccountController;
import exception.UsernameNotExistsException;

import java.util.HashMap;

public class ManagerUsersMenu extends Menu {
    public ManagerUsersMenu(Menu parent) {
        super("Manager Users Menu", parent);
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
        return new Menu("View Of User Menu", this) {
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
                else if (!input.matches("view \\w+"))
                    this.invalidCommandInExecute();
                else {
                    String userName = input.substring(5);
                    try {
                        ManagerAccountController.processViewUserInfoEach(userName);
                        this.execute();
                    } catch (UsernameNotExistsException userNameError) {
                        System.out.println(userNameError.getMessage());
                        this.execute();
                    }

                }
            }
        };
    }

    private Menu getDeleteUserMenu() {
        return new Menu("Delete User Menu", this) {
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
                else if (!input.matches("delete user \\w+"))
                    this.invalidCommandInExecute();
                else {
                    String userName = input.substring(12);
                    try {
                        ManagerAccountController.processDeleteUserEach(userName);
                        System.out.println("delete user successful");
                        this.execute();
                    } catch (UsernameNotExistsException userNameError) {
                        System.out.println(userNameError.getMessage());
                        this.execute();
                    }

                }
            }
        };
    }

    private Menu getCreateManagerProfileMenu() {
        return new Menu("Create Manager Profile Menu", this) {
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
