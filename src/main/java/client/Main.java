package client;

import client.graphicView.mainMenu.MainMenu;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    public static Stage window;

    public static void setMediaPlayer(String songName) {
//        server.main.Main.mediaPlayer = new MediaPlayer(new Media(new File("src/server.main/resources/media/sound/" + songName).toURI().toString()));
//        server.main.Main.mediaPlayer.setAutoPlay(true);
//        server.main.Main.mediaPlayer.setOnEndOfMedia(new Runnable() {
//            public void run() {
//                mediaPlayer.seek(Duration.ZERO);
//            }
//        });
    }

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
