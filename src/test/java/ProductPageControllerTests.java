import controller.LoginPageController;
import controller.ProductPageController;
import controller.ProductsPageController;
import exception.ProductAlreadyExistsInCartException;
import exception.SellerUserNameNotExists;
import model.account.Purchaser;
import model.account.Seller;
import model.product.Product;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class ProductPageControllerTests {
    @Test
    public void processAddProductToCartEachTest(){
        Purchaser purchaser = DataBaseForTest.purchaser1;
        Product inCartProduct = DataBaseForTest.product1;
        HashMap<Product,Integer> purchaserCart = new HashMap<>();
        purchaserCart.put(inCartProduct,1);
        purchaser.setCart(purchaserCart);
        Product selectedProduct1  = DataBaseForTest.product2;
        ProductsPageController.setSelectedProduct(selectedProduct1);
        LoginPageController.setLoggedInAccount(purchaser);
        try {
            ProductPageController.processAddProductToCartEach();
            Assert.assertTrue(purchaser.getCartProducts().contains(selectedProduct1));
        } catch (ProductAlreadyExistsInCartException e) {
            Assert.assertEquals(10,9);
        }
    }
    @Test
    public void processSelectSellerEachTest(){
        Seller seller= DataBaseForTest.seller2;
        Product selectedProduct = DataBaseForTest.product1;
        ProductsPageController.setSelectedProduct(selectedProduct);
        ArrayList<Seller> allSellersForSelectedProduct = new ArrayList<>();
        allSellersForSelectedProduct.add(seller);
        selectedProduct.setAllSellers(allSellersForSelectedProduct);
        try {
            ProductPageController.processSelectSellerEach("Cr7");
            Assert.assertEquals(seller,ProductPageController.selectedSeller);
        } catch (SellerUserNameNotExists sellerUserNameNotExists) {
            Assert.assertEquals(10,9);
        }
        try {
            ProductPageController.processSelectSellerEach("Cr7f");
            Assert.assertEquals(10,9);
        } catch (SellerUserNameNotExists sellerUserNameNotExists) {
            Assert.assertEquals(10,10);
        }

    }
    @Test
    public void processAddCommentTest(){

    }
}
