package client.graphicView.userRegion.userAccount.sellerAccount;

import client.Main;
import client.graphicView.mainMenu.MainMenu;
import client.graphicView.userRegion.loginPanel.LoginPanelController;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class AllProductsForAuctionPageController implements Initializable {
    private final int port = 9005;
    private final String ip = "127.0.0.1";
    @FXML
    public TableView<Product> auctionTable;
    @FXML
    public TableColumn<Product, Label> productName;
    @FXML
    public TableColumn<Product, StringProperty> productPrice;
    private DataOutputStream out;
    private DataInputStream in;

    public void process() {
        try {
            Socket socket = new Socket(ip, port);
            out = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        } catch (
                IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        process();
        productName.setCellValueFactory(new PropertyValueFactory<>("name"));
        productPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        try {
            auctionTable.setItems(getProduct());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    ObservableList<Product> getProduct() throws IOException {
        ObservableList<Product> allProducts = FXCollections.observableArrayList();
        out.writeUTF("get_seller_product_to_sell_size");
        out.flush();
        int size = Integer.parseInt(in.readUTF());
        for (int i = 0; i < size; i++) {
            out.writeUTF("check " + i);
            out.flush();
            String flag = in.readUTF();
            if (flag.equals("yes")) {
                out.writeUTF("get_product_info " + i);
                out.flush();
                String response = in.readUTF();
                String name = response.split("\\s")[0];
                String price = response.split("\\s")[1];
                allProducts.add(new Product(name, price));
            }
        }
        return allProducts;
    }

    @FXML
    public void back() throws IOException {
        AllProductsForAuctionPage.primaryStage.close();
        SellerAccountPage.display();
    }

    @FXML
    public void logout() throws IOException {
        out.writeUTF("logout " + LoginPanelController.token);
        out.flush();
        LoginPanelController.setLoggedInAccount(null);
        AllProductsForAuctionPage.primaryStage.close();
        MainMenu.display(Main.window);

    }

    public class Product {
        private Label name;
        private StringProperty price;

        public Product(String name, String price) {
            this.name = new Label(name);
            this.price = new SimpleStringProperty(price);
            this.name.setOnMouseClicked(mouseEvent -> {
                AllProductsForAuctionPage.primaryStage.close();
                try {
                    SellerAuctionController.setProductToAuction(name);
                    SellerAuction.display();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }

        public Label getName() {
            return name;
        }

        public String getPrice() {
            return price.get();
        }

        public StringProperty priceProperty() {
            return price;
        }
    }
}
