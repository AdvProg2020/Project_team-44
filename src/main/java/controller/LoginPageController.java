package controller;

import exception.UsernameExistsException;
import exception.UsernameNotExistsException;
import exception.WrongPasswordException;
import model.account.Account;
import model.account.Manager;
import model.account.Purchaser;
import model.account.Seller;
import model.requests.RequestForSeller;

public abstract class LoginPageController {
    static Account loggedInAccount;

    public static String getLoggedInAccountType() {
        if (loggedInAccount == null) {
            return "null";
        }
        if (loggedInAccount instanceof Purchaser) {
            return "purchaser";
        } else if (loggedInAccount instanceof Seller) {
            return "seller";
        } else {
            return "manager";
        }
    }

    public static void processCreateAccount(String type, String username, String password, String firstName, String lastName, String email
            , String telephoneNumber, String companyName) throws UsernameExistsException {

        ValidationController.checkUsernameForRegistration(username);
        if (type.equalsIgnoreCase("seller")) {
            RequestForSeller requestForSeller = new RequestForSeller(companyName, username, firstName,
                    lastName, email, telephoneNumber, password);

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
