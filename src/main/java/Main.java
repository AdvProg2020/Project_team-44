import controller.LoginPageController;
import exception.UsernameExistsException;
import view.CommandProcessor;

public class Main {
    public static void main(String[] args) {
        CommandProcessor.runCommandProcessorByMenu();
        try {
            LoginPageController.processCreateAccount("a", "", ""
                    , "", "", "", "", "");
        } catch (UsernameExistsException e) {
            e.printStackTrace();
        }

    }
}
