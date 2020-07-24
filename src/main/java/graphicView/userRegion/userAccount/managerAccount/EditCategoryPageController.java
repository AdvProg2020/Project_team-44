package graphicView.userRegion.userAccount.managerAccount;

import controller.LoginPageController;
import controller.ManagerAccountController;
import exception.CategoryAlreadyExistsException;
import exception.CategoryNotExistsException;
import graphicView.mainMenu.MainMenu;
import graphicView.userRegion.loginPanel.LoginPanelController;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import main.Main;

import java.io.IOException;

public class EditCategoryPageController {
    @FXML
    public TextField newName;
    @FXML
    public Label alertMessage;

    @FXML
    public void logout() throws IOException {
        LoginPageController.logout();
        LoginPanelController.setLoggedInAccount(null);
        EditCategoryPage.primaryStage.close();
        MainMenu.display(Main.window);
    }

    @FXML
    public void back() throws IOException {
        EditCategoryPage.primaryStage.close();
        CategoryPage.display();
    }

    @FXML
    public void edit() throws CategoryNotExistsException, CategoryAlreadyExistsException {
        String name = newName.getText();
        ManagerAccountController.processEditCategoryEachForName(
                CategoryPageController.getCurrentCategory().getName() , name);
        alertMessage.setText("Edit category name successful!");
    }

}
