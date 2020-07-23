module team {
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.controls;
    requires java.desktop;
    requires com.google.gson;
    requires javafx.base;
    requires javafx.media;
    requires com.jfoenix;

    opens server.main;
    opens client.graphicView.userRegion.loginPanel to javafx.fxml;
    opens client.graphicView.mainMenu to javafx.fxml;
    opens client.graphicView.productMenu to javafx.fxml, javafx.base;
    opens client.graphicView.cart to javafx.fxml, javafx.base;
    opens client.graphicView.purchasePage to javafx.fxml;
    opens client.graphicView.sellLogPage to javafx.fxml, javafx.base;
    opens client.graphicView.buyLogPage to javafx.fxml, javafx.base;
    opens client.graphicView.discountCodes to javafx.fxml, javafx.base;
   // opens client.graphicView.userRegion.userAccount to javafx.fxml;
    opens client.graphicView.userRegion.userAccount.managerRequestions.addOff to javafx.fxml, javafx.base;
    opens client.graphicView.userRegion.userAccount.managerRequestions.editOff to javafx.fxml, javafx.base;
    opens client.graphicView.userRegion.userAccount.managerRequestions.addProduct to javafx.fxml, javafx.base;
    opens client.graphicView.userRegion.userAccount.managerRequestions.removeProduct to javafx.fxml, javafx.base;
    opens client.graphicView.userRegion.userAccount.managerRequestions.addSeller to javafx.fxml, javafx.base;
//    opens client.graphicView to javafx.media;
    opens server.model to com.google.gson, javafx.base;
    opens server.model.account to com.google.gson;
    opens server.model.sellLog to com.google.gson;
    opens server.model.product to com.google.gson, javafx.base;
    opens server.model.offer to com.google.gson;
    opens server.model.buyLog to com.google.gson;
    opens server.model.comment to com.google.gson;
    opens client.graphicView.userRegion.userAccount.managerAccount to javafx.fxml;
    opens client.graphicView.userRegion.userAccount.sellerAccount to javafx.fxml;
    opens client.graphicView.userRegion.userAccount.purchaserAccount to javafx.fxml;
}