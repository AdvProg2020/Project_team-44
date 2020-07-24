package client.graphicView.userRegion.userAccount.sellerAccount;

import client.Main;
import client.graphicView.mainMenu.MainMenu;
import client.graphicView.userRegion.loginPanel.LoginPanelController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import server.controller.LoginPageController;
import server.controller.SellerAccountController;
import server.exception.ProductIdNotExistsException;
import server.model.product.Product;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProductPageInfoForSellerController implements Initializable {
    public ImageView productImage;
    public Label productName;
    public Label productPrice;
    public Label productId;
    public Label productCategory;
    public Label productCompanyName;
    public Label productExplanationText;
    public Label alertMessage;

    @FXML
    public void writeInformation(Product product) throws FileNotFoundException {
        productName.setText(product.getName());
        productCategory.setText(product.getCategory().getName());
        productPrice.setText(String.valueOf(product.getPrice()));
        productCompanyName.setText(product.getCompanyName());
        productId.setText(product.getProductID());
        productExplanationText.setText(product.getExplanationText());
        ImageView imageView = new ImageView(new Image(new FileInputStream("src/server.main/resources/media/image/" + product.getImageName())));
        productImage.setImage(imageView.getImage());
    }

    @FXML
    public void logout() throws IOException {
        ProductPageInfoForSeller.primaryStage.close();
        LoginPageController.logout();
        LoginPanelController.setLoggedInAccount(null);
        MainMenu.display(Main.window);
    }

    @FXML
    public void back() throws IOException {
        ProductPageInfoForSeller.primaryStage.close();
        ViewAllProductsForSellerPage.display();
    }

    @FXML
    public void remove() {
        try {
            SellerAccountController.processRemoveProduct(ViewAllProductsForSellerPageController.
                    currentProduct.getProductID());
            alertMessage.setText("Your request for remove this product send to manager!");
        } catch (ProductIdNotExistsException e) {
            System.err.println(e.getMessage());
        }
    }

    @FXML
    public void edit() throws IOException {
        ProductPageInfoForSeller.primaryStage.close();
        EditProductForSeller.display();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            writeInformation(ViewAllProductsForSellerPageController.currentProduct);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
