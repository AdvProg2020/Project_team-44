package model;

import com.google.gson.Gson;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.product.Product;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

public class Category {
    private String name;
    private ArrayList<Category> subCategories = new ArrayList<>();
    private ArrayList<Product> allSubProducts = new ArrayList<>();
    private ArrayList<String> attributes = new ArrayList<>();
    private static ArrayList<Category> allCategories = new ArrayList<>();
    private transient Category parent = null;
    private String imageName;

    public Category(String name, Category parent, String imageName) {
        this.name = name;
        if (parent != null) {
            this.attributes = parent.getAttributes();
            this.parent = parent;
            this.imageName = imageName;
            parent.getSubCategories().add(this);
            updateAllParent(parent);
        }

        allCategories.add(this);
        createAndUpdateJson(this);
    }


    public String getImageName() {
        return imageName;
    }

    public static ArrayList<Category> getAllCategories() {
        return allCategories;
    }

    public void updateAllParent(Category parent) {
        createAndUpdateJson(parent);
        if (parent.getParent() != null)
            updateAllParent(parent.getParent());
    }

    public void createAndUpdateJson(Category category) {
        try {
            Writer writer = new FileWriter("src/main/resources/Categories/" + category.getName() + ".json");
            new Gson().toJson(category, writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAttributes(ArrayList<String> attributes) {
        this.attributes = attributes;
    }

    public static void setAllCategories(ArrayList<Category> allCategories) {
        Category.allCategories = allCategories;
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

    public Category getParent() {
        return parent;
    }

    public void setAllSubProducts(ArrayList<Product> allSubProducts) {
        this.allSubProducts = allSubProducts;
    }

    public void setSubCategories(ArrayList<Category> subCategories) {
        this.subCategories = subCategories;
    }

    public static Category getCategoryByName(String categoryName) {
        for (Category allCategory : allCategories) {
            if (allCategory.getName().equalsIgnoreCase(categoryName)) {
                return allCategory;
            }
        }
        return null;
    }

    public static ArrayList<String> getAllCategoryNames() {
        ArrayList<String> names = new ArrayList<>();
        for (Category allCategory : allCategories) {
            names.add(allCategory.getName());
        }
        return names;
    }

    public ArrayList<String> getAllSubProductsName() {
        ArrayList<String> names = new ArrayList<>();
        for (Product allSubProduct : this.getAllSubProducts()) {
            names.add(allSubProduct.getName());
        }
        return names;
    }

    public static ArrayList<Category> getAllParents() {
        ArrayList<Category> parents = new ArrayList<>();
        for (Category allCategory : allCategories) {
            if (allCategory.getParent() == null) {
                parents.add(allCategory);
            } else {

            }
        }
        return parents;
    }
}
