package client.graphicView.userRegion.userAccount.purchaserAccount;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import server.model.product.Auction;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PurchaserAuctionController implements Initializable {
    @FXML
    public Pane root;
    @FXML
    public VBox vBox0;
    @FXML
    public VBox vBox1;
    @FXML
    public VBox vBox2;
    @FXML
    public VBox vBox3;
    @FXML
    public VBox vBox4;
    @FXML
    public VBox vBox5;
    @FXML
    public VBox vBox6;
    @FXML
    public VBox vBox7;
    @FXML
    public VBox vBox8;
    @FXML
    public VBox vBox9;
    @FXML
    public VBox vBox10;
    @FXML
    public VBox vBox11;


    public VBox getVBox(int i) {
        switch (i) {
            case 0:
                return vBox0;
            case 1:
                return vBox1;
            case 2:
                return vBox2;
            case 3:
                return vBox3;
            case 4:
                return vBox4;
            case 5:
                return vBox5;
            case 6:
                return vBox6;
            case 7:
                return vBox7;
            case 8:
                return vBox8;
            case 9:
                return vBox9;
            case 10:
                return vBox10;
            case 11:
                return vBox11;
            default:
                return null;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (int i = 0; i < Auction.getAllAuctions().size(); i++) {
            ImageView imageView = null;
            try {
                imageView = new ImageView(new Image(new FileInputStream("src/server.main/resources/media/image/" + Auction.getAllAuctions().get(i).getImageName())));
                imageView.setFitWidth(169);
                imageView.setFitHeight(169);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            Text productName = new Text(Auction.getAllAuctions().get(i).getName());
            productName.setFont(Font.font(20));
            int finalI = i;
            productName.setOnMouseClicked(mouseEvent -> {
                try {
                    PurchaserAuction.primaryStage.close();
                    PurchaserAuctionChat.display(Auction.getAllAuctions().get(finalI));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            Text productExplanation = new Text(Auction.getAllAuctions().get(i).getExplanationText());
            productExplanation.setFont(Font.font(19));

            Text productPrice = new Text("Price : " + Auction.getAllAuctions().get(i).getPrice() + " $");
            productPrice.setFont(Font.font(18));

            getVBox(i).getChildren().addAll(imageView, productName, productExplanation, productPrice);
        }
    }
}
