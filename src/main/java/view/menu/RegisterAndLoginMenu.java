package view.menu;

import controller.LoginPageController;
import controller.ManagerAccountController;
import exception.UsernameExistsException;
import exception.UsernameNotExistsException;
import exception.WrongPasswordException;
import view.menu.regexEnumForInput.RegisterRegex;

import java.util.ArrayList;
import java.util.HashMap;


public class RegisterAndLoginMenu extends Menu {
    public RegisterAndLoginMenu(Menu parent) {
        super("Register And Login Menu", parent);
        HashMap<Integer, Menu> submenus = new HashMap<>();
        submenus.put(1, getRegisterMenu());
        submenus.put(2, getLoginMenu());
        this.setSubmenus(submenus);
    }

    @Override
    public void show() {
        System.out.println(this.getName() + ":");
        System.out.println("1-" + getSubmenus().get(1).getName());
        System.out.println("2-" + getSubmenus().get(2).getName());
        System.out.println("3-back");
        System.out.println("4-help");
    }

    @Override
    public void execute() {
        Menu nextMenu = null;
        String input = scanner.nextLine();
        if (!input.matches("\\d+")) {
            System.err.println("please choose a number for your menu!");
            this.execute();
        } else {
            int menuNumber = Integer.parseInt(input);
            if (menuNumber == 0 || menuNumber > 4) {
                System.err.println("your menu number is invalid!");
                this.execute();
            } else if (menuNumber == 1)
                nextMenu = this.getSubmenus().get(1);
            else if (menuNumber == 2)
                nextMenu = this.getSubmenus().get(2);
            else if (menuNumber == 3)
                nextMenu = this.parent;
            else
                nextMenu = this;
            nextMenu.show();
            nextMenu.menuWork();
            nextMenu.execute();
        }
    }

    private Menu getRegisterMenu() {
        return new Menu("Register Menu", this) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Please enter your type and information :");
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

    private Menu getLoginMenu() {
        return new Menu("Login Menu", this) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Please enter your username");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("back"))
                    this.backInExecute();
                else if (!input.matches("login \\w+"))
                    this.invalidCommandInExecute();
                else {
                    String userName = input.substring(6);
                    System.out.println("Please enter your password");
                    String passWord = scanner.nextLine();
                    try {
                        LoginPageController.processLogin(userName, passWord);
                        System.out.println("login successful");
                        parent.show();
                        parent.menuWork();
                        parent.execute();
                    } catch (UsernameNotExistsException userNameError) {
                        System.err.println(userNameError.getMessage());
                        this.execute();
                    } catch (WrongPasswordException passWordError) {
                        System.err.println(passWordError.getMessage());
                        this.execute();
                    }
                }
            }
        };
    }
}
