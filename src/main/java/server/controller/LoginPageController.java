package server.controller;

import server.exception.UsernameExistsException;
import server.exception.UsernameNotExistsException;
import server.exception.WrongPasswordException;
import server.model.account.Account;
import server.model.account.Manager;
import server.model.account.Purchaser;
import server.model.requests.RequestForSeller;

public abstract class LoginPageController {
   public static Account loggedInAccount;
    static boolean isMainManagerRegistered = false;

    public static void setLoggedInAccount(Account loggedInAccount) {
        LoginPageController.loggedInAccount = loggedInAccount;
    }

    public static boolean isIsMainManagerRegistered() {
        return isMainManagerRegistered;
    }

    public static Account getLoggedInAccount() {
        return loggedInAccount;
    }

    public static Account processCreateAccount(String type, String username, String password, String firstName, String lastName, String email, String telephoneNumber, String companyName, String address, String companyTelephoneNumber)
            throws UsernameExistsException {

        ValidationController.checkUsernameForRegistration(username);
        if (type.equalsIgnoreCase("head manager")) {
            isMainManagerRegistered = true;
            return new Manager(username,
                    firstName,
                    lastName,
                    email,
                    telephoneNumber,
                    password);
        } else if (type.equalsIgnoreCase("seller")) {
            new RequestForSeller(companyName,
                    address,
                    companyTelephoneNumber,
                    username,
                    firstName,
                    lastName,
                    email,
                    telephoneNumber,
                    password);
        }
        return new Purchaser(username,
                firstName,
                lastName,
                email,
                telephoneNumber,
                password,
                address);
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
