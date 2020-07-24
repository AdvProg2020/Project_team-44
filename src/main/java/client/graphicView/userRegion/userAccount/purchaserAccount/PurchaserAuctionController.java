package client.graphicView.userRegion.userAccount.purchaserAccount;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class PurchaserAuctionController implements Initializable {
    private final int port = 9004;
    private final String ip = "127.0.0.1";
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
    private DataOutputStream out;
    private DataInputStream in;


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

    public void input() {
        while (true) {
            String input;
            try {
                input = in.readUTF();
                if (input.startsWith("")) {

                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public void output() {

    }

    public void process() {
        try {
            Socket socket = new Socket(ip, port);
            out = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            new Thread(this::output).start();
            new Thread(this::input).start();
        } catch (
                IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        process();
        int size = 0;
        try {
            out.writeUTF("get_auction_size");
            out.flush();
            size = Integer.parseInt(in.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < size; i++) {
            String response = null;
            try {
                out.writeUTF("get_auction_info_by_num " + i);
                out.flush();
                response = in.readUTF();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String name = response.split("\\s")[0];
            String price = response.split("\\s")[1];
            String explanationText = response.substring(name.length() + 1 + price.length() + 1);
            String imageName = null;
            try {
                out.writeUTF("get_image_name " + i);
                out.flush();
                imageName = in.readUTF();
            } catch (IOException e) {
                e.printStackTrace();
            }
            ImageView imageView = null;
            try {
                imageView = new ImageView(new Image(new FileInputStream("src/main/resources/media/image/" + imageName)));
                imageView.setFitWidth(169);
                imageView.setFitHeight(169);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            Text productName = new Text(name);
            productName.setFont(Font.font(20));
            int finalI = i;
            productName.setOnMouseClicked(mouseEvent -> {
                try {
                    PurchaserAuction.primaryStage.close();
                    PurchaserAuctionChat.display(finalI);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            Text productExplanation = new Text(explanationText);
            productExplanation.setFont(Font.font(19));

            Text productPrice = new Text("Price : " + price + " $");
            productPrice.setFont(Font.font(18));

            getVBox(i).getChildren().addAll(imageView, productName, productExplanation, productPrice);
        }
    }
}
