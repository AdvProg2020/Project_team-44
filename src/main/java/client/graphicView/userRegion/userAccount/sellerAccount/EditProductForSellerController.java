package client.graphicView.userRegion.userAccount.sellerAccount;

import server.controller.LoginPageController;
import server.controller.SellerAccountController;
import server.exception.ProductFieldsNotException;
import server.exception.ProductIdNotExistsException;
import client.graphicView.mainMenu.MainMenu;
import client.graphicView.userRegion.loginPanel.LoginPanelController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import server.Main;
import server.model.product.Product;

import java.io.IOException;

import static javafx.scene.paint.Color.GREEN;

public class EditProductForSellerController {

    public TextField newProductName;
    public TextField newProductPrice;
    public TextField newProductExplanationText;
    public Label alertMessage;
    public TextField newCompanyName;
    public TextField newImageName;

    @FXML
    public void RequestForEdit() throws ProductIdNotExistsException, ProductFieldsNotException {
        Product product = ViewAllProductsForSellerPageController.currentProduct;
        String productName = newProductName.getText();
        String productPrice = newProductPrice.getText();
        String productExplanationText = newProductExplanationText.getText();
        String productImageName = newImageName.getText();
        String productCompanyName = newCompanyName.getText();
        if(!productName.matches("\\w+")){
            alertMessage.setText("Please enter server.Main valid new name!");
            return;
        }
        if(!productPrice.matches("\\d+.\\d+")){
            alertMessage.setText("Please enter server.Main valid new price!");
            return;
        }
        if(!productExplanationText.matches("\\w+")){
            alertMessage.setText("Please enter server.Main valid new explanation text!");
            return;
        }
        if(!productCompanyName.matches("\\w+")){
            alertMessage.setText("Please enter server.Main valid company name!");
            return;
        }
        if(!productImageName.matches(".+")){
            alertMessage.setText("Please enter server.Main valid image name!");
            return;
        }
        double price = Double.parseDouble(productPrice);
        SellerAccountController.processEditProduct(product,productName,productCompanyName,price,
                productExplanationText,productImageName);
        alertMessage.setText("");
        alertMessage.setTextFill(GREEN);
        alertMessage.setText("Your request for edit send to the manager");

    }

    @FXML
    public void back() throws IOException {
        EditProductForSeller.primaryStage.close();
        ProductPageInfoForSeller.display();
    }
    @FXML
    public void logout() throws IOException {
        EditProductForSeller.primaryStage.close();
        LoginPageController.logout();
        LoginPanelController.setLoggedInAccount(null);
        MainMenu.display(Main.window);
    }
}
