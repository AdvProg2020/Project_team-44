package model.product;

import com.google.gson.Gson;
import model.Category;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

public class Auction extends Product {
    private static ArrayList<Product> allAuctions = new ArrayList<>();

    public Auction(Category category, String name, String companyName, double price, String explanationText, String imageName) {
        super(category, name, companyName, price, explanationText, imageName);
        addToAll();
    }

    public static void setAllAuctions(ArrayList<Product> allAuctions) {
        Auction.allAuctions = allAuctions;
    }

    public static ArrayList<Product> getAllAuctions() {
        return allAuctions;
    }

    @Override
    public void addToAll() {
        allAuctions.add(this);
    }

    @Override
    public void createAndUpdateJson(Product product) {
        try {
            Writer writer = new FileWriter("src/main/resources/Auction/" + this.getName() + ".json");
            new Gson().toJson(product, writer);
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage() + "!!!!!!!!!!!!!!");
        }
    }
}
