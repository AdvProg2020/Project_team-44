module team {
    requires javafx.fxml;
    requires javafx.graphics;
    requires com.google.gson;
    requires javafx.controls;
    //requires java.desktop;
    opens main;
    opens graphicView.mainMenu to javafx.fxml;
    opens graphicView.productMenu to javafx.fxml;
    opens model to com.google.gson;
    opens model.account to com.google.gson;
    opens model.sellLog to com.google.gson;
    opens model.product to com.google.gson;
    opens model.offer to com.google.gson;
    opens model.buyLog to com.google.gson;
    opens model.comment to com.google.gson;
}