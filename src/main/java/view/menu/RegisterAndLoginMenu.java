package view.menu;

import controller.LoginPageController;
import exception.UsernameNotExistsException;
import exception.WrongPasswordException;

import java.util.HashMap;


public class RegisterAndLoginMenu extends Menu {
    public RegisterAndLoginMenu(Menu parent) {
        super("Register And Login Menu", parent);
        HashMap<Integer, Menu> submenus = new HashMap<>();
        submenus.put(1,getRegisterMenu());
        submenus.put(2, getLoginMenu());
        this.setSubmenus(submenus);
    }

    private Menu getRegisterMenu() {
        return new Menu("Register Menu", this) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Please enter your type and userName");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("back"))
                    this.backInExecute();
                else if (!input.matches("create account (manager|seller|purchaser) \\S+"))
                    this.invalidCommandInExecute();
                else {
                    String type = input.split("\\s")[2];
                    String userName = input.split("\\s")[3];
                    System.out.println("Please enter your password");
                    String passWord = scanner.nextLine();
                    System.out.println("Please enter your name");
                    String name = scanner.nextLine();
                    if(!name.matches("([A-Z]|[a-z]) ([A-Z]|[a-z])")){

                    }

                    try {
                        LoginPageController.processLogin(userName, passWord);
                        System.out.println("login successful");
                        this.execute();
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
                        this.execute();
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
