package client;

import client.graphicView.mainMenu.MainMenu;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    public static Stage window;

    public static void main(String[] args) {
        launch(args);
    }

    public static void setAccountRegionStage(Stage accountRegionStage) {
        Main.window = accountRegionStage;
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setWidth(1275);
        stage.setHeight(720);
        MainMenu.display(stage);
    }
}
