package model.product;

import model.Category;

public class File extends Product {
    private String fileUrlPath;

    public File(Category category, String name, String companyName, double price, String explanationText, String imageName, String fileUrlPath) {
        super(category, name, companyName, price, explanationText, imageName);
        this.fileUrlPath = fileUrlPath;
    }

    public String getFileUrlPath() {
        return fileUrlPath;
    }

    public void setFileUrlPath(String fileUrlPath) {
        this.fileUrlPath = fileUrlPath;
    }
}
