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
    static boolean isMainManagerLogged = false;

    public static boolean isIsMainManagerLogged() {
        return isMainManagerLogged;
    }

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
            , String telephoneNumber, String companyName, String companyAddress, String companyTelephoneNumber) throws UsernameExistsException {
        ValidationController.checkUsernameForRegistration(username);
        if (type.equalsIgnoreCase("manager")) {
            Manager manager = new Manager(username, firstName, lastName, email, telephoneNumber, password);
            isMainManagerLogged = true;
        } else if (type.equalsIgnoreCase("seller")) {
            RequestForSeller requestForSeller = new RequestForSeller(companyName, companyAddress, companyTelephoneNumber, username, firstName,
                    lastName, email, telephoneNumber, password);

        } else {
            Purchaser purchaser = new Purchaser(username, firstName, lastName, email, telephoneNumber, password, companyAddress);
        }
    }

    public static Account getLoggedInAccount() {
        return loggedInAccount;
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
