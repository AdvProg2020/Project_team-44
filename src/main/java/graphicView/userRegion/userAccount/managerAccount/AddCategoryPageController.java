package graphicView.userRegion.userAccount.managerAccount;

import controller.LoginPageController;
import controller.ManagerAccountController;
import exception.CategoryNotExistsException;
import graphicView.mainMenu.MainMenu;
import graphicView.userRegion.loginPanel.LoginPanelController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import main.Main;

import java.io.IOException;

public class AddCategoryPageController {
    public TextField categoryName;
    public TextField parentName;
    public TextField imageName;
    public Label alertMessage;

    @FXML
    public void logout() throws IOException {
        AddCategoryPage.primaryStage.close();
        LoginPageController.logout();
        LoginPanelController.setLoggedInAccount(null);
        MainMenu.display(Main.window);
    }
    @FXML
    public void back() throws IOException {
        AddCategoryPage.primaryStage.close();
        ViewAllCategoriesPage.display();
    }
    @FXML
    public void add(){
        String name = categoryName.getText();
        String parent = parentName.getText();
        String image = imageName.getText();
        try {
            ManagerAccountController.processAddCategoryEach(name,parent,image);
            alertMessage.setTextFill(Color.GREEN);
            alertMessage.setText("Add Category successful!");
        } catch (CategoryNotExistsException e) {
            alertMessage.setTextFill(Color.RED);
            alertMessage.setText(e.getMessage());
        }
    }
}
