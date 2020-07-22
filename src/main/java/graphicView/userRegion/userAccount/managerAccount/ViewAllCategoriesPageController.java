package graphicView.userRegion.userAccount.managerAccount;

import controller.LoginPageController;
import graphicView.mainMenu.MainMenu;
import graphicView.userRegion.loginPanel.LoginPanelController;
import javafx.fxml.FXML;
import main.Main;

import java.io.IOException;

public class ViewAllCategoriesPageController {
    @FXML
    public void logout() throws IOException {
        LoginPageController.logout();
        LoginPanelController.setLoggedInAccount(null);
        ViewAllCategoriesPage.primaryStage.close();
        MainMenu.display(Main.window);
    }
    @FXML
    public void back() throws IOException {
        ViewAllCategoriesPage.primaryStage.close();
        ManagerAccountPage.display();
    }
}
