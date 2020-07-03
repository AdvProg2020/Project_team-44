package graphicView.userRegion.userAccount.sellerAccount;

import controller.LoginPageController;
import controller.SellerAccountController;
import exception.ProductFieldsNotException;
import exception.ProductIdNotExistsException;
import graphicView.mainMenu.MainMenu;
import graphicView.userRegion.loginPanel.LoginPanelController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import main.Main;
import model.product.Product;

import java.io.IOException;

import static javafx.scene.paint.Color.GREEN;

public class EditProductForSellerController {

    public TextField newProductName;
    public TextField newProductPrice;
    public TextField newProductExplanationText;
    public Label alertMessage;

    @FXML
    public void RequestForEdit() throws ProductIdNotExistsException, ProductFieldsNotException {
        Product product = ViewAllProductsForSellerPageController.currentProduct;
        String productName = newProductName.getText();
        String productPrice = newProductPrice.getText();
        String productExplanationText = newProductExplanationText.getText();
        if(!productName.matches("\\w+")){
            alertMessage.setText("Please enter a valid new name!");
            return;
        }
        if(!productPrice.matches("\\d+.\\d+")){
            alertMessage.setText("Please enter a valid new price!");
            return;
        }
        if(!productExplanationText.matches("\\w+")){
            alertMessage.setText("Please enter a valid new explanation text!");
            return;
        }
        SellerAccountController.processEditProduct(product.getProductID(),"NAME",productName);
        SellerAccountController.processEditProduct(product.getProductID(),"PRICE",productPrice);
        SellerAccountController.processEditProduct(product.getProductID(),"EXPLANATION_TEXT",productExplanationText);
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
