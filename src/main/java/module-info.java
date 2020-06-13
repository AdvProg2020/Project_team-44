module team {
    requires javafx.fxml;
    requires javafx.graphics;
    requires com.google.gson;
    requires javafx.controls;
    requires java.desktop;
    opens main;
    opens graphicView.mainMenu to javafx.fxml;
    exports graphicView.mainMenu to javafx.fxml;
    //requires kotlin.stdlib;
    //requires maven.artifact;
}