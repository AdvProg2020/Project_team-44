import controller.LoginPageController;
import exception.UsernameExistsException;
import exception.UsernameNotExistsException;
import model.account.Account;
import model.account.Purchaser;
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
       // new Purchaser("d", "a", "aa", "aaaa", "436843", "dkgufsf", "sdfsdf");
    }
}
