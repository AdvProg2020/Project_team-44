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
    private static boolean isMainManagerRegistered;
    private static Account loggedInAccount;
//salam salam sad ta salam
    public static boolean isIsMainManagerRegistered() {
        return isMainManagerRegistered;
    }

    public static Account getLoggedInAccount() {
        return loggedInAccount;
    }

    public static void processCreateAccount(String type, String username, String password, String firstName, String lastName, String email
            , String telephoneNumber, String companyName) throws UsernameExistsException {

        checkUsernameForRegistration(username);
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
        checkUsernameForLogin(username);
        checkPasswordForLogin(username, password);
        loggedInAccount = Account.getAccountByUsername(username);
        loggedInAccount.setLoggedIn(true);
    }

    public static void logout() {
        loggedInAccount.setLoggedIn(false);
        loggedInAccount = null;
    }

    public static void checkPasswordForLogin(String username, String password) throws WrongPasswordException {
        if (!Account.getAccountByUsername(username).getPassword().equals(password)) {
            throw new WrongPasswordException("Password is incorrect.");
        }
    }

    public static void checkUsernameForLogin(String username) throws UsernameNotExistsException {
        for (Account account : Account.getAllAccounts()) {
            if (username.equals(account.getUsername())) {
                return;
            }
        }
        throw new UsernameNotExistsException("No user exists with this username.");
    }

    public static void checkUsernameForRegistration(String username) throws UsernameExistsException {
        for (Account account : Account.getAllAccounts()) {
            if (username.equals(account.getUsername())) {
                throw new UsernameExistsException("User exists with this username");
            }
        }
    }
}
