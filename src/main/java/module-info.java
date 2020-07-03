module team {
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.controls;
    requires java.desktop;
    requires com.google.gson;
    requires javafx.base;
    requires javafx.media;
//    requires com.jfoenix;

    opens main;
    opens graphicView.userRegion.loginPanel to javafx.fxml;
    opens graphicView.mainMenu to javafx.fxml;
    opens graphicView.productMenu to javafx.fxml, javafx.base;
    opens graphicView.cart to javafx.fxml, javafx.base;
    opens graphicView.purchasePage to javafx.fxml;
    opens graphicView.sellLogPage to javafx.fxml, javafx.base;
    opens graphicView.buyLogPage to javafx.fxml, javafx.base;
    opens graphicView.discountCodes to javafx.fxml, javafx.base;
    opens graphicView.userRegion.userAccount to javafx.fxml;
    opens graphicView.userRegion.userAccount.sellerAccount to javafx.fxml, javafx.base;
//    opens graphicView to javafx.media;
    opens model to com.google.gson, javafx.base;
    opens model.account to com.google.gson;
    opens model.sellLog to com.google.gson;
    opens model.product to com.google.gson, javafx.base;
    opens model.offer to com.google.gson;
    opens model.buyLog to com.google.gson;
    opens model.comment to com.google.gson;
    //    opens java.lang.reflect to com.jfoenix;
}