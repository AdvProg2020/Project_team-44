module team {
    requires javafx.fxml;
    requires javafx.graphics;
    requires com.google.gson;
    requires javafx.controls;
    requires java.desktop;
    opens main;
    opens graphicView.userRegion.loginPanel to javafx.fxml;
}