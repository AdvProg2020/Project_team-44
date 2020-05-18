package model;

import model.product.Product;

import java.util.ArrayList;

public class Category {
    private String name;
    private ArrayList<Category> subCategories = new ArrayList<>();
    private ArrayList<Product> allSubProducts = new ArrayList<>();
    private ArrayList<String> attributes = new ArrayList<>();
    private static ArrayList<Category> allCategories = new ArrayList<>();

    public Category(String name) {
        this.name = name;
        allCategories.add(this);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAttributes(ArrayList<String> attributes) {
        this.attributes = attributes;
    }

    public ArrayList<String> getAttributes() {
        return attributes;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Category> getSubCategories() {
        return subCategories;
    }

    public ArrayList<Product> getAllSubProducts() {
        return allSubProducts;
    }

    public static ArrayList<Category> getAllCategories() {
        return allCategories;
    }

    public static Category getCategoryByName(String categoryName) {
        for (Category allCategory : allCategories) {
            if (allCategory.getName().equalsIgnoreCase(categoryName)){
                return allCategory;
            }
        }
        return null;
    }
    public static ArrayList<String> getAllCategoryNames(){
        ArrayList<String> names = new ArrayList<>();
        for (Category allCategory : allCategories) {
            names.add(allCategory.getName());
        }
        return names;
    }
}
