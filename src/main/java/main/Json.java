package main;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import model.Category;
import model.ShopBankAccount;
import model.account.Account;
import model.account.Manager;
import model.account.Purchaser;
import model.account.Seller;
import model.comment.Comment;
import model.product.Auction;
import model.product.Product;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Json<T> {
    public List<String> getAllFilePathInDirectory(String directoryPath) {
        try {
            Stream<Path> walk = Files.walk(Paths.get(directoryPath));
            List<String> result = walk.map(x -> x.toString()).filter(f -> f.endsWith(".json")).collect(Collectors.toList());
            return result;
        } catch (IOException e) {

        }
        return null;
    }

    public T getManagerObject(String path) {
        Gson gson = new Gson();
        JsonReader reader = null;
        try {
            reader = new JsonReader(new FileReader(path));
        } catch (FileNotFoundException e) {

        }
        Manager manager = gson.fromJson(reader, Manager.class);
        T t = (T) manager;
        return t;
    }

    public T getPurchaserObject(String path) {
        Gson gson = new Gson();
        JsonReader reader = null;
        try {
            reader = new JsonReader(new FileReader(path));
        } catch (FileNotFoundException e) {

        }
        Purchaser purchaser = gson.fromJson(reader, Purchaser.class);
        T t = (T) purchaser;
        return t;
    }

    public T getSellerObject(String path) {
        Gson gson = new Gson();
        JsonReader reader = null;
        try {
            reader = new JsonReader(new FileReader(path));
        } catch (FileNotFoundException e) {

        }
        Seller seller = gson.fromJson(reader, Seller.class);
        T t = (T) seller;
        return t;
    }

    public T getAccountObject(String path) {
        Gson gson = new Gson();
        JsonReader reader = null;
        try {
            reader = new JsonReader(new FileReader(path));
        } catch (FileNotFoundException e) {

        }
        Account account = gson.fromJson(reader, Account.class);
        T t = (T) account;
        return t;
    }

    public T getProductObject(String path) {
        Gson gson = new Gson();
        JsonReader reader = null;
        try {
            reader = new JsonReader(new FileReader(path));
        } catch (FileNotFoundException e) {

        }
        Product product = gson.fromJson(reader, Product.class);
        T t = (T) product;
        return t;
    }

    public T getCategoryObject(String path) {
        Gson gson = new Gson();
        JsonReader reader = null;
        try {
            reader = new JsonReader(new FileReader(path));
        } catch (FileNotFoundException e) {

        }
        Category category = gson.fromJson(reader, Category.class);
        T t = (T) category;
        return t;
    }

    public T getCommentObject(String path) {
        Gson gson = new Gson();
        JsonReader reader = null;
        try {
            reader = new JsonReader(new FileReader(path));
        } catch (FileNotFoundException e) {

        }
        Comment comment = gson.fromJson(reader, Comment.class);
        T t = (T) comment;
        return t;
    }

    public T getBankAccountObject(String path) {
        Gson gson = new Gson();
        JsonReader reader = null;
        try {
            reader = new JsonReader(new FileReader(path));
        } catch (FileNotFoundException e) {

        }
        ShopBankAccount bankAccount = gson.fromJson(reader, ShopBankAccount.class);
        T t = (T) bankAccount;
        return t;
    }

    public T getAuctionObject(String path) {
        Gson gson = new Gson();
        JsonReader reader = null;
        try {
            reader = new JsonReader(new FileReader(path));
        } catch (FileNotFoundException e) {

        }
        Auction auction = gson.fromJson(reader, Auction.class);
        T t = (T) auction;
        return t;
    }

    public ArrayList<T> getAllJson(String folderPath, String kind) {
        ArrayList<T> all = new ArrayList<>();
        for (String s : getAllFilePathInDirectory(folderPath)) {
            switch (kind) {
                case "account":
                    all.add(getAccountObject(s));
                    break;
                case "seller":
                    all.add(getSellerObject(s));
                    break;
                case "purchaser":
                    all.add(getPurchaserObject(s));
                    break;
                case "manager":
                    all.add(getManagerObject(s));
                    break;
                case "product":
                    all.add(getProductObject(s));
                    break;
                case "category":
                    all.add(getCategoryObject(s));
                    break;
                case "comment":
                    all.add(getCommentObject(s));
                    break;
                case "bankAccount":
                    all.add(getBankAccountObject(s));
                    break;
                case "auction":
                    all.add(getAuctionObject(s));
                    break;
            }
        }
        return all;
    }
}
