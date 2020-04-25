package model;

import model.product.Product;

import java.util.ArrayList;

public class Category {
    private String name;
    private ArrayList<Category> subCategories = new ArrayList<>();
    private ArrayList<Product> allSubProducts = new ArrayList<>();
    private ArrayList<String> attributes = new ArrayList<>();

    public Category(String name) {
        this.name = name;
    }
}
