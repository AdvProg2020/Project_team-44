package graphicView.cart;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class CartHandler {
    private Integer quantity = 1;
    private Integer totalAmount;
    private Button removeButton = new Button();
    private Button increaseButton = new Button();
    private Button decreaseButton = new Button();
    {
        try {
            removeButton.setGraphic(new ImageView(new Image(new FileInputStream("/"))));
            increaseButton.setGraphic(new ImageView(new Image(new FileInputStream("/"))));
            decreaseButton.setGraphic(new ImageView(new Image(new FileInputStream("/"))));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
