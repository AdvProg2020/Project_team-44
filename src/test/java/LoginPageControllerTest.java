import server.controller.LoginPageController;
import server.exception.UsernameExistsException;
import server.exception.UsernameNotExistsException;
import server.exception.WrongPasswordException;
import server.model.account.Account;
import server.model.account.Purchaser;
import org.junit.Assert;
import org.junit.Test;


public class LoginPageControllerTest {
    @Test
    public void processCreateAccountTest() throws UsernameExistsException {
        Account sellerAccount = LoginPageController.processCreateAccount("seller", "DJ1", "1234", "" +
                        "David", "James", "David@gmial.com", "22222222", "Nike", "am7",
                "12345678");
        Assert.assertEquals(sellerAccount.getFirstName(), "David");
        Assert.assertEquals(sellerAccount.getLastName(), "James");
        Assert.assertEquals(sellerAccount.getTelephoneNumber(), "22222222");
        Assert.assertEquals(sellerAccount.getUserName(), "DJ1");
        Account managerAccount = LoginPageController.processCreateAccount("head manager", "ali1", "12", "ali",
                "moni", "M2@com", "123", "iran", "street", "12342");
        Assert.assertEquals(managerAccount.getUserName(), "ali1");
        Assert.assertTrue(LoginPageController.isIsMainManagerRegistered());
        Account purchaserAccount = LoginPageController.processCreateAccount("purchaser", "Amirabbas", "234",
                "amir", "daei", "amirrr@gmial.com", "09122", "", "", "");
        Assert.assertEquals(purchaserAccount.getTelephoneNumber(), "09122");
        Assert.assertEquals(purchaserAccount.getFirstName(), "amir");
    }

    @Test
    public void processLoginAndLogoutTest() throws UsernameNotExistsException, WrongPasswordException {
        String username = "Lm10";
        String password = "lll";
        Purchaser purchaser = DataBaseForTest.purchaser3;
        LoginPageController.processLogin(username, password);
        Assert.assertTrue(purchaser.isLoggedIn());
        Assert.assertEquals(LoginPageController.getLoggedInAccount().getUserName(), "Lm10");
        Assert.assertEquals(LoginPageController.getLoggedInAccount().getPassword(), "lll");
        LoginPageController.logout();
        Assert.assertNull(LoginPageController.getLoggedInAccount());
    }

}
