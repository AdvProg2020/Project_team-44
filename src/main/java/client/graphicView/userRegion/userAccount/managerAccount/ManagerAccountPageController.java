package client.graphicView.userRegion.userAccount.managerAccount;

import client.Main;
import client.graphicView.mainMenu.MainMenu;
import client.graphicView.purchasePage.PurchasePageController;
import client.graphicView.userRegion.loginPanel.LoginPanelController;
import client.graphicView.userRegion.userAccount.managerRequestions.addOff.AddOffRequest;
import client.graphicView.userRegion.userAccount.managerRequestions.addProduct.AddProductRequest;
import client.graphicView.userRegion.userAccount.managerRequestions.addSeller.AddSellerRequest;
import client.graphicView.userRegion.userAccount.managerRequestions.editOff.EditOffRequest;
import client.graphicView.userRegion.userAccount.managerRequestions.editProduct.EditProductRequest;
import client.graphicView.userRegion.userAccount.managerRequestions.removeProduct.RemoveProductRequest;
import client.graphicView.userRegion.userAccount.sellerAccount.SellerWalletController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class ManagerAccountPageController implements Initializable {
    @FXML
    public ImageView managerImage;
    @FXML
    public Label managerName;
    @FXML
    public Label managerUsername;
    @FXML
    public Label managerPhoneNumber;
    @FXML
    public Label managerEmail;
    @FXML
    public Label managerPassword;
    @FXML
    public TextField setMinAmountId;
    @FXML
    public TextField setWageId;
    @FXML
    private MenuButton requests;
    @FXML
    private Button viewAllCategories;
    @FXML
    private Button viewAllDiscountCodes;
    @FXML
    private Button viewAllAccounts;
    @FXML
    private Button editInfo;
    @FXML
    private MenuItem addOff;
    @FXML
    private MenuItem editOff;
    @FXML
    private MenuItem addProduct;
    @FXML
    private MenuItem editProduct;
    @FXML
    private MenuItem removeProduct;
    @FXML
    private MenuItem addSeller;
    @FXML
    private Button logout;
    private final int port = 9000;
    private final String ip = "127.0.0.1";
    private DataOutputStream out;
    private DataInputStream in;

    @FXML
    private void logout() throws IOException {
        out.writeUTF("logout " + LoginPanelController.token);
        out.flush();
        LoginPanelController.setLoggedInAccount(null);
        ManagerAccountPage.primaryStage.close();
        MainMenu.display(Main.window);
    }

    @FXML
    private Button backButton;

    @FXML
    private void goPreviousScene() throws IOException {
        ManagerAccountPage.primaryStage.close();
        MainMenu.display(Main.window);
    }

    public void processWriteInformation(String firstName, String lastName, String username, String telephone, String eMail, String password) {
        managerName.setText(firstName + " " + lastName);
        managerUsername.setText(username);
        managerPhoneNumber.setText(telephone);
        managerEmail.setText(eMail);
        managerPassword.setText(password);
    }

    public void writeInformationForManager() {
        try {
            out.writeUTF("get_information " + LoginPanelController.token);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void input() {
        while (true) {
            String input;
            try {
                input = in.readUTF();
                if (input.startsWith("information is : ")) {
                    String[] part = input.substring(17).split("\\s");
                    processWriteInformation(part[0], part[1], part[2], part[3], part[4], part[5]);
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
            new Thread(this::output).start();
            new Thread(this::input).start();
        } catch (
                IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        process();
        writeInformationForManager();
        addOff.setOnAction(actionEvent -> {
            try {
                AddOffRequest.display();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        editOff.setOnAction(actionEvent -> {
            try {
                EditOffRequest.display();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        addProduct.setOnAction(actionEvent -> {
            try {
                AddProductRequest.display();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        editProduct.setOnAction(actionEvent -> {
            try {
                EditProductRequest.display();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        removeProduct.setOnAction(actionEvent -> {
            try {
                RemoveProductRequest.display();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        addSeller.setOnAction(actionEvent -> {
            try {
                AddSellerRequest.display();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

    }

    @FXML
    public void edit() throws IOException {
        ManagerAccountPage.primaryStage.close();
        ManagerEditInfoPage.display();
    }

    @FXML
    public void atLeastMoneyAction(ActionEvent actionEvent) {
        SellerWalletController.atLeastAmount = Integer.parseInt(setMinAmountId.getText());
        setMinAmountId.setText("");
        try {
            out.writeUTF("set_min_amount " + setMinAmountId.getText());
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void createBankAccountAction() throws IOException {
        ManagerAccountPage.primaryStage.close();
        ManagerAccountBankAccount.display();
    }

    @FXML
    public void wageAction(ActionEvent actionEvent) {
        PurchasePageController.wage = Integer.parseInt(setWageId.getText());
        setWageId.setText("");
        try {
            out.writeUTF("set_wage " + setWageId.getText());
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
