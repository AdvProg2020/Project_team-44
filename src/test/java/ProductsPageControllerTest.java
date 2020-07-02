import controller.ProductsPageController;
import exception.CategoryNotExistsException;
import exception.FilterNotExistsException;
import model.Category;
import model.product.Product;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class ProductsPageControllerTest {
//    @Test
//    public void processFilterTest() {
//        Product product1 = DataBaseForTest.product1;
//        Product product2 = DataBaseForTest.product2;
//        Product product3 = DataBaseForTest.product3;
//        Product product4 = DataBaseForTest.product4;
//        ArrayList<Product> allFiltersProducts = new ArrayList<>();
//        allFiltersProducts.add(product1);
//        allFiltersProducts.add(product2);
//        allFiltersProducts.add(product3);
//        allFiltersProducts.add(product4);
//        ProductsPageController.processSortByPrice(true);
//        Assert.assertEquals(ProductsPageController.allFilteredProducts.get(0),product3);
//        Assert.assertEquals(ProductsPageController.allFilteredProducts.get(1),product1);
//        ProductsPageController.processSortByPrice(false);
//        Assert.assertEquals(ProductsPageController.allFilteredProducts.get(0),product4);
//        Assert.assertEquals(ProductsPageController.allFilteredProducts.get(1),product2);
//
//        ProductsPageController.setAllFilteredProducts(allFiltersProducts);
//        ArrayList<Product> filteredProductByName = new ArrayList<>();
//        try {
//            ProductsPageController.processFilter("BY_NAME", "per");
//            Assert.assertEquals(ProductsPageController.allFilteredProducts.get(0).getName(), ProductsPageController.processShowProducts().get(0));
//            Assert.assertEquals(ProductsPageController.allFilteredProducts.get(1).getName(), ProductsPageController.processShowProducts().get(1));
//        } catch (CategoryNotExistsException | FilterNotExistsException e) {
//            Assert.assertEquals(10, 9);
//        }
//        try {
//            ProductsPageController.processFilter("BY_PRICE", "11000,13000");
//            Assert.assertEquals(ProductsPageController.allFilteredProducts.get(0), product1);
//        } catch (CategoryNotExistsException | FilterNotExistsException e) {
//            Assert.assertEquals(10, 9);
//        }
//        try {
//            ProductsPageController.processDeleteFilterEach("BY_NAME_per");
//            Assert.assertEquals(ProductsPageController.allFilteredProducts.size(), 1);
//            Assert.assertEquals(ProductsPageController.allFilteredProducts.get(0), product1);
//        } catch (FilterNotExistsException e) {
//            Assert.assertEquals(10, 9);
//        }
//        try {
//            ProductsPageController.processDeleteFilterEach("BY_PRICE_RANGE_11000,13000");
//            Assert.assertEquals(ProductsPageController.allFilters.size(),0);
//        } catch (FilterNotExistsException e) {
//            Assert.assertEquals(10,9);
//        }
//        Category category = DataBaseForTest.category1;
//
//
//    }

}
