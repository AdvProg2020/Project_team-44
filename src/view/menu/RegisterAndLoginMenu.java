package view.menu;

import controller.LoginPageController;
import exception.UsernameNotExistsException;
import exception.WrongPasswordException;
import model.account.Account;

public class RegisterAndLoginMenu extends Menu {
    public RegisterAndLoginMenu(Menu parent, Account account) {
        super("Register And Login Menu", parent, account);
    }
    private Menu getLoginMenu(){
        return new Menu("Login Menu" , this , this.getCurrentUserLoggedIn()) {
            @Override
            public void show() {
                System.out.println(this.getName()+":");
                System.out.println("Please enter your username and password");
            }

            @Override
            public void execute() {
                String input1 = scanner.nextLine();
                String passWord = scanner.nextLine();
                if(!input1.matches("login \\w+")){
                    System.err.println("invalid command!");
                    this.execute();
                }
                else{
                    String userName = input1.substring(6);
                    try {
                        LoginPageController.processLogin(userName,passWord);
                        System.out.println("login successful");
                    }
                    catch (UsernameNotExistsException userNameError){
                        userNameError.getMessage();
                        this.execute();
                    }
                    catch (WrongPasswordException passWordError){
                        passWordError.getMessage();
                        this.execute();
                    }
                }
            }
        };
    }
}
