package client.graphicView.userRegion.userAccount.sellerAccount;

import server.controller.LoginPageController;
import server.controller.SellerAccountController;
import server.exception.CategoryNotExistsException;
import client.graphicView.mainMenu.MainMenu;
import client.graphicView.userRegion.loginPanel.LoginPanelController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import server.Main;

import java.io.IOException;

import static javafx.scene.paint.Color.GREEN;

public class RequestForAddProductPageController {
    @FXML
    public TextField categoryForAddProduct;
    @FXML
    public TextField productNameForAddProduct;
    @FXML
    public TextField priceForAddProduct;
    @FXML
    public TextField explanationTextForAddProduct;
    @FXML
    public Label alertMessage;

    @FXML
    public void sendRequest() {
        String category = categoryForAddProduct.getText();
        String productName = productNameForAddProduct.getText();
        String priceString = priceForAddProduct.getText();
        String explanationText = explanationTextForAddProduct.getText();
        if (!category.matches("\\w+")) {
            alertMessage.setText("Please enter server.Main valid format of category!");
            return;
        }
        if (!productName.matches("\\w+")) {
            alertMessage.setText("Please enter server.Main valid product name!");
            return;
        }
        if (!priceString.matches("\\d+")) {
            alertMessage.setText("Please enter server.Main valid price!");
            return;
        }
        if (!explanationText.matches("\\w+")) {
            alertMessage.setText("Please enter server.Main valid explanation text!");
            return;
        }
        int price = Integer.parseInt(priceString);
        try {
            SellerAccountController.processAddProduct(category, productName, price, explanationText);
            alertMessage.setText("");
            alertMessage.setTextFill(GREEN);
            alertMessage.setText("your request for add this product send to manager");
        } catch (CategoryNotExistsException e) {
            alertMessage.setText("This category is not exists!");
        }
    }

    @FXML
    public void logout() throws IOException {
        RequestForAddProductPage.primaryStage.close();
        LoginPageController.logout();
        LoginPanelController.setLoggedInAccount(null);
        MainMenu.display(Main.window);
    }

    @FXML
    public void back() throws IOException {
        RequestForAddProductPage.primaryStage.close();
        SellerAccountPage.display();
    }
}
