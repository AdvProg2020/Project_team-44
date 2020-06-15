package sample.controller;

import sample.exception.UsernameExistsException;
import sample.exception.UsernameNotExistsException;
import sample.exception.WrongPasswordException;
import model.account.Account;
import model.account.Manager;
import model.account.Purchaser;
import model.requests.RequestForSeller;

public abstract class LoginPageController {
    static Account loggedInAccount;
    static boolean isMainManagerRegistered = false;

    public static boolean isIsMainManagerRegistered() {
        return isMainManagerRegistered;
    }

    public static Account getLoggedInAccount() {
        return loggedInAccount;
    }

    public static Account processCreateAccount(String type, String username, String password, String firstName, String lastName, String email
            , String telephoneNumber, String companyName, String companyAddress, String companyTelephoneNumber) throws UsernameExistsException {
        ValidationController.checkUsernameForRegistration(username);
        if (type.equalsIgnoreCase("head manager")) {
            isMainManagerRegistered = true;
            return new Manager(username, firstName, lastName, email, telephoneNumber, password);
        } else if (type.equalsIgnoreCase("seller")) {
            RequestForSeller requestForSeller = new RequestForSeller(companyName, companyAddress, companyTelephoneNumber, username, firstName,
                    lastName, email, telephoneNumber, password);
        }
        return new Purchaser(username, firstName, lastName, email, telephoneNumber, password, companyAddress);
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
