package model.product;

import com.google.gson.Gson;
import model.Category;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;

public class Auction extends Product {
    private static ArrayList<Auction> allAuctions = new ArrayList<>();
    private Date finalDate;

    public void setFinalDate(Date finalDate) {
        this.finalDate = finalDate;
    }

    public Date getFinalDate() {
        return finalDate;
    }

    public Auction(Category category, String name, String companyName, double price, String explanationText, String imageName , Date finalDate) {
        super(category, name, companyName, price, explanationText, imageName);
        this.finalDate = finalDate;
        addToAll();
    }

    public static void setAllAuctions(ArrayList<Auction> allAuctions) {
        Auction.allAuctions = allAuctions;
    }

    public static ArrayList<Auction> getAllAuctions() {
        return allAuctions;
    }

    @Override
    public void addToAll() {
        allAuctions.add(this);
        createAndUpdateJson(this);
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
