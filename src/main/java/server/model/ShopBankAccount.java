package server.model;

import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

public class ShopBankAccount {
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private int accountId;
    public static ArrayList<ShopBankAccount> allShopBankAccount = new ArrayList<>();

    public ShopBankAccount(String firstName, String lastName, String userName, String password, int accountId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.accountId = accountId;
        allShopBankAccount.add(this);
        createAndUpdateJson();
    }

    public void createAndUpdateJson() {
        try {
            Writer writer = new FileWriter("src/server.main/resources/Bank Account/" + this.accountId + ".json");
            new Gson().toJson(this, writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public int getAccountId() {
        return accountId;
    }

    public static void setAllShopBankAccount(ArrayList<ShopBankAccount> allShopBankAccount) {
        ShopBankAccount.allShopBankAccount = allShopBankAccount;
    }

    public String getUsernameById(int id) {
        for (ShopBankAccount bankAccount : allShopBankAccount) {
            if (bankAccount.getAccountId() == id)
                return bankAccount.getUserName();
        }
        return null;
    }

    public static ShopBankAccount getShopBankAccount() {
        for (ShopBankAccount bankAccount : allShopBankAccount) {
            if (bankAccount.getUserName().equals("managermanager"))
                return bankAccount;
        }
        return null;
    }
}
