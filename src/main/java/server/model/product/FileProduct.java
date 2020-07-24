package server.model.product;

import server.model.Category;

public class FileProduct extends Product {
    public FileProduct(Category category, String name, String companyName, double price, String explanationText, String imageName) {
        super(category, name, companyName, price, explanationText, imageName);
    }
}
