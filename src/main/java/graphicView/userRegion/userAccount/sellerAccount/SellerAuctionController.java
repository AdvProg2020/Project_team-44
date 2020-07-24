package graphicView.userRegion.userAccount.sellerAccount;

import controller.LoginPageController;
import graphicView.mainMenu.MainMenu;
import graphicView.userRegion.loginPanel.LoginPanelController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import main.Main;
import model.account.Seller;
import model.product.Auction;
import model.product.Product;

import java.io.IOException;
import java.util.Date;

public class SellerAuctionController {
    public TextField finalYear;
    public TextField finalMonth;
    public TextField finalDay;
    public TextField finalHour;
    public TextField finalMinute;
    public Label alertMessage;
    private static Product productToAuction;

    public static void setProductToAuction(Product productToAuction) {
        SellerAuctionController.productToAuction = productToAuction;
    }

    @FXML
    public void back() throws IOException {
        AllProductsForAuctionPage.primaryStage.close();
        SellerAccountPage.display();
    }
    @FXML
    public void logout() throws IOException {
        AllProductsForAuctionPage.primaryStage.close();
        LoginPageController.logout();
        LoginPanelController.setLoggedInAccount(null);
        MainMenu.display(Main.window);
    }
    @FXML
    public void addAuction(){
        if(!finalYear.getText().matches("\\d\\d\\d\\d")){
            alertMessage.setText("Please enter a valid year!");
            return;
        }
        if(!finalMonth.getText().matches("\\d\\d")){
            alertMessage.setText("Please enter a valid month!");
            return;
        }
        if(!finalDay.getText().matches("\\d\\d")){
            alertMessage.setText("Please enter a valid day!");
            return;
        }
        if(!finalHour.getText().matches("\\d\\d")){
            alertMessage.setText("Please enter a valid hour!");
            return;
        }
        if(!finalMinute.getText().matches("\\d\\d")){
            alertMessage.setText("Please enter a valid minute!");
            return;
        }
        int year = Integer.parseInt(finalYear.getText())-1900;
        int month = Integer.parseInt(finalMonth.getText())-1;
        int day = Integer.parseInt(finalDay.getText());
        int hour = Integer.parseInt(finalHour.getText());
        int minute = Integer.parseInt(finalMinute.getText());
        Date date = new Date(year,month,day,hour,minute);
        Auction auction = new Auction(productToAuction.getCategory(),productToAuction.getName(),
                productToAuction.getCompanyName(),productToAuction.getPrice(),productToAuction.getExplanationText(),
                productToAuction.getImageName(),date);
        Seller seller = (Seller) LoginPageController.loggedInAccount;
        seller.getAuction().add(auction);
        seller.createAndUpdateJson();
        alertMessage.setText("");
        alertMessage.setTextFill(Color.GREEN);
        alertMessage.setText("Add Auction Successful!");

    }
}
