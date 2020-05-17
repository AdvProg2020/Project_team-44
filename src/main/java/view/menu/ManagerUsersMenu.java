package view.menu;

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
                System.out.println("please enter your username");
            }

            @Override
            public void execute() {
                String userName = scanner.nextLine();
                if (userName.equalsIgnoreCase("back"))
                    this.backInExecute();
                else {
                    System.out.println("Please enter your password");
                    String passWord = scanner.nextLine();
                    if (passWord.equalsIgnoreCase("back"))
                        this.backInExecute();
                    System.out.println("Please enter your name");
                    String name = scanner.nextLine();
                    if (name.equalsIgnoreCase("back"))
                        this.backInExecute();
                    if (!name.matches("([A-Z]|[a-z])+ ([A-Z]|[a-z])+"))
                        this.invalidCommandInExecute();
                    String firstName = name.split("\\s")[0];
                    String lastName = name.split("\\s")[1];
                    System.out.println("Please enter your email");
                    String email = scanner.nextLine();
                    if (email.equalsIgnoreCase("back"))
                        this.backInExecute();
                    if (!email.matches("\\w+@\\w+.com"))
                        this.invalidCommandInExecute();
                    System.out.println("please enter your phoneNumber");
                    String phoneNumber = scanner.nextLine();
                    if (phoneNumber.equalsIgnoreCase("back"))
                        this.backInExecute();
                    if (!phoneNumber.matches("09\\d{9}"))
                        this.invalidCommandInExecute();
                    try {
                        ManagerAccountController.processCreateManagerProfileEach(userName, passWord, firstName, lastName, email, phoneNumber);
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
