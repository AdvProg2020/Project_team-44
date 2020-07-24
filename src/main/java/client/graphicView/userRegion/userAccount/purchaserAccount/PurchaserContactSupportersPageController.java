package client.graphicView.userRegion.userAccount.purchaserAccount;

import client.graphicView.userRegion.loginPanel.LoginPanelController;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class PurchaserContactSupportersPageController implements Initializable {

    @FXML
    private TableView<Supporter> supportersTableView;

    @FXML
    private TableColumn<Supporter, Label> supporterUsernameColumn;
    private final int port = 9014;
    private final String ip = "127.0.0.1";
    private DataOutputStream out;
    private DataInputStream in;

    public HashMap<String, Stage> userToStage = new HashMap<>();
    public HashMap<String, Scene> userToScene = new HashMap<>();
    public HashMap<String, Integer> userToVBoxNum = new HashMap<>();
    public HashMap<String, Pane> userToRoot = new HashMap<>();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        process();
        supporterUsernameColumn.setCellValueFactory(new PropertyValueFactory<>("supporterUsername"));
        try {
            supportersTableView.setItems(getSupporters());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void input() {
        while (true) {
            String input = null;
            try {
                input = in.readUTF();
                while (true) {
                    if (input.startsWith("")) {

                    }
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
        } catch (IOException e) {
            e.printStackTrace();
        }
        new Thread(this::output).start();
        new Thread(this::input).start();
    }


    // only displays the online supporters
    private ObservableList<Supporter> getSupporters() throws IOException {
        ObservableList<Supporter> supporters = FXCollections.observableArrayList();
        ArrayList<String> allSupporter = new ArrayList<>();
        out.writeUTF("get_all_supporter");
        out.flush();
        String response = in.readUTF();
        for (String s : response.split(" - ")) {
            allSupporter.add(s);
        }
        for (String s : allSupporter) {
            supporters.add(new Supporter(s));
        }
        return supporters;
    }

    public class Supporter {
        private final Label supporterUsername;

        public Supporter(String supporterUsername) {
            this.supporterUsername = new Label(supporterUsername);
            this.supporterUsername.setOnMouseClicked(actionEvent -> {
                userToStage.get(supporterUsername).show();
                processChat(supporterUsername);

            });
            Platform.runLater(() -> {
                Stage stage = new Stage();
                Pane root = new Pane();
                ScrollPane scrollPane = new ScrollPane(root);
                Scene scene = new Scene(scrollPane, 300, 300);
                stage.setScene(scene);
                userToScene.put(supporterUsername, scene);
                userToStage.put(supporterUsername, stage);
                userToRoot.put(supporterUsername, root);
                userToVBoxNum.put(supporterUsername, 0);
            });


        }

        private void processChat(String supporterUsername) {


        }

        public Label getSupporterUsername() {
            return supporterUsername;
        }
    }
}