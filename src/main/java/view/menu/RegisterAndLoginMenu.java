package view.menu;

import controller.LoginPageController;
import exception.UsernameNotExistsException;
import exception.WrongPasswordException;

import java.util.HashMap;


public class RegisterAndLoginMenu extends Menu {
    public RegisterAndLoginMenu(Menu parent) {
        super("Register And Login Menu", parent);
        HashMap<Integer, Menu> submenus = new HashMap<Integer, Menu>();
        this.setSubmenus(submenus);
    }

    private Menu getLoginMenu() {
        return new Menu("Login Menu", this) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Please enter your username and password");
            }

            @Override
            public void execute() {
                String input1 = scanner.nextLine();
                String passWord = scanner.nextLine();
                if (input1.equalsIgnoreCase("back"))
                    this.backInExecute();
                else if (!input1.matches("login \\w+"))
                    this.invalidCommandInExecute();
                else {
                    String userName = input1.substring(6);
                    try {
                        LoginPageController.processLogin(userName, passWord);
                        System.out.println("login successful");
                        this.execute();
                    } catch (UsernameNotExistsException userNameError) {
                        System.out.println(userNameError.getMessage());
                        this.execute();
                    } catch (WrongPasswordException passWordError) {
                        System.out.println(passWordError.getMessage());
                        this.execute();
                    }
                }
            }
        };
    }
}
