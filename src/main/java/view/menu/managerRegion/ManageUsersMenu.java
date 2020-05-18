package view.menu.managerRegion;

import controller.ManagerAccountController;
import exception.UsernameNotExistsException;
import view.menu.Menu;
import view.menu.regexEnumForInput.CreateDiscountCodeRegex;
import view.menu.regexEnumForInput.CreateManagerProfileRegex;
import view.menu.regexEnumForInput.RegisterRegex;

import java.util.ArrayList;
import java.util.HashMap;

public class ManageUsersMenu extends Menu {
    public ManageUsersMenu(Menu parent) {
        super("Manager Users Menu", parent);
        HashMap<Integer, Menu> submenus = new HashMap<>();
        submenus.put(1, getViewOfUserMenu());
        submenus.put(2, getDeleteUserMenu());
        submenus.put(3, getCreateManagerProfileMenu());
        this.setSubmenus(submenus);
    }

    @Override
    public void menuWork() {
        int i = 1;
        for (String userInf : ManagerAccountController.processManageUsers()) {
            System.out.println(i + "- " + userInf);
            i++;
        }
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
                        for (String viewUserInfoEach : ManagerAccountController.processViewUserInfoEach(userName)) {
                            System.out.println(viewUserInfoEach);
                        }
                        this.execute();
                    } catch (UsernameNotExistsException userNameError) {
                        System.err.println(userNameError.getMessage());
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
                        System.err.println(userNameError.getMessage());
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
                System.out.println(this.getName() + ":");
                System.out.println("please enter your information :");
            }

            @Override
            public void execute() {
                ArrayList<String> info = RegisterRegex.checkRegex(scanner);
                if (info == null) {
                    this.backInExecute();
                } else {
                    try {
                        ManagerAccountController.processCreateManagerProfileEach(info.get(0), info.get(1),
                                info.get(2), info.get(3), info.get(4), info.get(5));
                        System.out.println("create new manager successful");
                        this.execute();
                    } catch (UsernameNotExistsException createManagerError) {
                        System.err.println(createManagerError.getMessage());
                        this.execute();
                    }
                }
            }
        };
    }

}
