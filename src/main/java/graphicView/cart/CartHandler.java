package graphicView.cart;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class CartHandler {
    private IntegerProperty quantity = new SimpleIntegerProperty(1);
    private IntegerProperty totalAmount = new SimpleIntegerProperty();
    private Button removeButton = new Button();
    private Button increaseButton = new Button();
    private Button decreaseButton = new Button();

    // image of buttons
    {
        try {
            removeButton.setGraphic(new ImageView(new Image(new FileInputStream("media/image/deleteIcon.png"))));
            increaseButton.setGraphic(new ImageView(new Image(new FileInputStream("media/image/deleteIcon.png"))));
            decreaseButton.setGraphic(new ImageView(new Image(new FileInputStream("media/image/deleteIcon.png"))));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void handleCart() {
//        totalAmount.bind();
    }
}
