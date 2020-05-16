package controller;

import exception.UsernameExistsException;
import exception.UsernameNotExistsException;
import exception.WrongPasswordException;
import model.account.Account;
import model.account.Manager;
import model.account.Purchaser;
import model.requests.RequestForSeller;

public abstract class LoginPageController {
    static boolean isMainManagerRegistered;
    static Account loggedInAccount;

    public static boolean isIsMainManagerRegistered() {
        return isMainManagerRegistered;
    }

    public static Account getLoggedInAccount() {
        return loggedInAccount;
    }

    public static void processCreateAccount(String type, String username, String password, String firstName, String lastName, String email
            , String telephoneNumber, String companyName) throws UsernameExistsException {

        ValidationController.checkUsernameForRegistration(username);
        if (type.equalsIgnoreCase("seller")) {
            RequestForSeller requestForSeller = new RequestForSeller(companyName, username, firstName,
                    lastName, email, telephoneNumber, password);

        } else if (type.equalsIgnoreCase("manager")) {
            Manager manager = new Manager(username, firstName, lastName, email, telephoneNumber, password);
            isMainManagerRegistered = true;

        } else {
            Purchaser purchaser = new Purchaser(username, firstName, lastName, email, telephoneNumber, password);
        }
    }

    public static void processLogin(String username, String password) throws UsernameNotExistsException, WrongPasswordException {
        ValidationController.checkUsernameForLogin(username);
        ValidationController.checkPasswordForLogin(username, password);
        loggedInAccount = Account.getAccountByUsername(username);
        loggedInAccount.setLoggedIn(true);
    }

    public static void logout() {
        loggedInAccount.setLoggedIn(false);
        loggedInAccount = null;
    }

}
