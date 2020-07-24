package server.viewServer.productMenu;

import server.controller.LoginPageController;
import server.controller.ProductPageController;
import server.controller.ProductsPageController;
import server.exception.FilterNotExistsException;
import server.exception.ProductAlreadyExistsInCartException;
import server.exception.ProductIdNotExistsException;
import server.exception.SellerUserNameNotExists;
import server.model.Category;
import server.model.Rating;
import server.model.Sort.Sort;
import server.model.account.Purchaser;
import server.model.account.Seller;
import server.model.comment.Comment;
import server.model.product.Product;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class productsMenuServer {
    private final int port = 9010;
    private static ArrayList<Product> productsToShow = new ArrayList<>();
    private static ArrayList<Product> categoryFilterProducts = new ArrayList<>();
    private static ArrayList<Category> allowedCategory = new ArrayList<>();

    public productsMenuServer() throws IOException {
        ServerSocket shopServer = new ServerSocket(port);
        new Thread(() -> {
            while (true) {
                try {
                    Socket socket = shopServer.accept();
                    DataInputStream in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
                    DataOutputStream out = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
                    new productsMenuServer.ClientHandler(out, in, socket).start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    static class ClientHandler extends Thread {
        private DataOutputStream dataOutputStream;
        private DataInputStream dataInputStream;
        private Socket socket;

        private ClientHandler(DataOutputStream dataOutputStream, DataInputStream dataInputStream, Socket socket) {
            this.dataOutputStream = dataOutputStream;
            this.dataInputStream = dataInputStream;
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    String input = dataInputStream.readUTF();
                    if (input.startsWith("price_action_ ")) {
                        processPriceAction(input.substring(14));
                    } else if (input.startsWith("product_name_action_ ")) {
                        processProductNameAction(input.substring(21));
                    } else if (input.startsWith("click_price_")) {
                        processClickPrice(input.substring(12));
                    } else if (input.startsWith("click_view_")) {
                        processClickView(input.substring(11));
                    } else if (input.startsWith("click_score_")) {
                        processClickScore(input.substring(12));
                    } else if (input.startsWith("click_time_")) {
                        processClickTime(input.substring(11));
                    } else if (input.equals("switch_for_sort")) {
                        processSwitchForSort();
                    } else if (input.equals("back_in_secondary")) {
                        processBackInSecondary();
                    } else if (input.equals("category_all_parent_size")) {
                        processCategoryAllParentSize();
                    } else if (input.startsWith("get_category_name ")) {
                        processGetCategoryName(Integer.parseInt(input.substring(18)));
                    } else if (input.startsWith("sub_category_size_from_parent ")) {
                        processSubCategorySizeFromParent(Integer.parseInt(input.substring(18)));
                    } else if (input.startsWith("get_sub_category_name_From_Parent ")) {
                        processGetSubCategoryNameFromParent(input.substring(34));
                    } else if (input.startsWith("sub_menu_ ")) {
                        processSubMenu(input.substring(10));
                    } else if (input.equals("category_property_check_box")) {
                        processCategoryPropertyCheckBox();
                    } else if (input.startsWith("category_property_check_box_action ")) {
                        processCategoryPropertyCheckBoxAction(input.substring(35));
                    } else if (input.startsWith("category_property_check_box_in_else ")) {
                        processCategoryPropertyCheckBoxInElse(input.substring(36));
                    } else if (input.equals("get_allowed_category_size")) {
                        processGetAllowedCategorySize();
                    } else if (input.equals("get_product_to_show_size")) {
                        processGetProductToShowSize();
                    } else if (input.startsWith("category_property_add ")) {
                        processCategoryPropertyAdd(input.substring(22));
                    } else if (input.startsWith("category_property_add_down ")) {
                        processCategoryPropertyAddDown(input.substring(27));
                    } else if (input.startsWith("check_satisfied_in_filter_first ")) {
                        processCheckSatisfiedInFilterFirst(input.substring(32));
                    } else if (input.startsWith("check_satisfied_in_filter_second ")) {
                        processCheckSatisfiedInFilterSecond(input.substring(33));
                    } else if (input.equals("get_all_filter_size")) {
                        processGetAllFilterSize();
                    } else if (input.startsWith("get_filter_by_num ")) {
                        processGetFilterByNum(Integer.parseInt(input.substring(18)));
                    } else if (input.startsWith("get_product_price_imageName_explanation_by_name ")) {
                        processGetProductPriceExplanationByName(input.substring(48));
                    } else if (input.startsWith("get_category_attributes_product_size ")) {
                        processGetCategoryAttributesProductSize(input.substring(37));
                    } else if (input.startsWith("get_category_attributes_product ")) {
                        processGetCategoryAttributesProduct(input.substring(32));
                    } else if (input.startsWith("comment_action ")) {
                        processCommentAction(input.substring(15));
                    } else if (input.startsWith("rating_action ")) {
                        processRatingAction(input.substring(14));
                    } else if (input.startsWith("add_to_cart_action ")) {
                        processAddToCartAction(input.substring(19));
                    } else if (input.startsWith("get_product_all_seller ")) {
                        processGetProductAllSeller(input.substring(23));
                    } else if (input.startsWith("get_company_name_seller ")) {
                        processGetCompanyNameSeller(input.substring(24));
                    } else if (input.startsWith("checkBox_product_seller ")) {
                        processCheckBoxProductSeller(input.substring(24));
                    } else if (input.startsWith("get_average_rating_product ")) {
                        processGetAverageRatingProduct(input.substring(27));
                    } else if (input.startsWith("product_get_all_comment_size ")) {
                        processProductGetAllCommentSize(input.substring(29));
                    } else if (input.startsWith("do_each_product_comment ")) {
                        processDoEachProductComment(input.substring(24));
                    } else if (input.equals("disable_sort_each")) {
                        processDisableSortEach();
                    } else if (input.startsWith("get_sub_category ")) {
                        processGetSubCategory(input.substring(17));
                    } else if (input.startsWith("get_product_info_from_product_to_show ")) {
                        processGetProductNameFromProductToShow(Integer.parseInt(input.substring(38)));
                    } else if (input.startsWith("product_name_on_action ")) {
                        processProductNameOnAction(input.substring(23));
                    } else if (input.startsWith("check_product_is_available ")) {
                        processCheckProductIsAvailable(input.substring(27));
                    } else if (input.startsWith("check_product_is_in_offer ")) {
                        processCheckProductIsInOffer(input.substring(26));
                    } else if (input.startsWith("get_product_imageName ")) {
                        processGetProductImageName(Integer.parseInt(input.substring(22)));
                    }
                }
            } catch (IOException | FilterNotExistsException | ProductIdNotExistsException | ProductAlreadyExistsInCartException | SellerUserNameNotExists e) {
                e.printStackTrace();
            }
        }

        public void processPriceAction(String path) throws FilterNotExistsException {
            String minPriceField = path.split("\\s")[0];
            String maxPriceField = path.split("\\s")[1];
            if (!minPriceField.isBlank() && maxPriceField.isBlank()) {
                ProductsPageController.processFilter("BY_PRICE", Integer.parseInt(minPriceField) + "," + Integer.MAX_VALUE, categoryFilterProducts, productsToShow, allowedCategory);
            } else if (!maxPriceField.isBlank() && minPriceField.isBlank()) {
                ProductsPageController.processFilter("BY_PRICE", 0 + "," + Integer.parseInt(maxPriceField), categoryFilterProducts, productsToShow, allowedCategory);
            } else if (!minPriceField.isBlank() && !maxPriceField.isBlank()) {
                ProductsPageController.processFilter("BY_PRICE", Integer.parseInt(minPriceField) + "," + Integer.parseInt(maxPriceField), categoryFilterProducts, productsToShow, allowedCategory);
            } else
                ProductsPageController.processFilter("BY_PRICE", -1 + "," + Integer.MAX_VALUE, categoryFilterProducts, productsToShow, allowedCategory);

        }

        public void processProductNameAction(String productName) throws FilterNotExistsException {
            if (productName != null)
                ProductsPageController.processFilter("BY_NAME", productName, categoryFilterProducts, productsToShow, allowedCategory);
        }

        public void processClickPrice(String bool) {
            boolean isUp;
            if (bool.equals("true"))
                isUp = true;
            else isUp = false;
            ProductsPageController.processSortByPrice(isUp, productsToShow);
        }

        public void processClickView(String bool) {
            boolean isUp;
            if (bool.equals("true"))
                isUp = true;
            else isUp = false;
            ProductsPageController.processSortByView(isUp, productsToShow);
        }

        public void processClickScore(String bool) {
            boolean isUp;
            if (bool.equals("true"))
                isUp = true;
            else isUp = false;
            ProductsPageController.processSortByScore(isUp, productsToShow);
        }

        public void processClickTime(String bool) {
            boolean isUp;
            if (bool.equals("true"))
                isUp = true;
            else isUp = false;
            ProductsPageController.processSortByTime(isUp, productsToShow);
        }

        public void processSwitchForSort() throws IOException {
            switch (ProductsPageController.getCurrentProductsSort()) {
                case TIME_UP -> {
                    dataOutputStream.writeUTF("click_time_true");
                    dataOutputStream.flush();
                    break;
                }
                case TIME_DOWN -> {
                    dataOutputStream.writeUTF("click_time_false");
                    dataOutputStream.flush();
                    break;
                }
                case VIEW_UP -> {
                    dataOutputStream.writeUTF("click_view_true");
                    dataOutputStream.flush();
                    break;
                }
                case VIEW_DOWN -> {
                    dataOutputStream.writeUTF("click_view_false");
                    dataOutputStream.flush();
                    break;
                }
                case PRICE_UP -> {
                    dataOutputStream.writeUTF("click_price_true");
                    dataOutputStream.flush();
                    break;
                }
                case PRICE_DOWN -> {
                    dataOutputStream.writeUTF("click_price_false");
                    dataOutputStream.flush();
                    break;
                }
                case SCORE_UP -> {
                    dataOutputStream.writeUTF("click_score_true");
                    dataOutputStream.flush();
                    break;
                }
                case SCORE_DOWN -> {
                    dataOutputStream.writeUTF("click_score_false");
                    dataOutputStream.flush();
                    break;
                }
            }
        }

        public void processBackInSecondary() {
            ProductsPageController.getAllFilters().clear();
            ProductsPageController.setCurrentProductsSort(Sort.TIME_UP);
        }

        public void processCategoryAllParentSize() throws IOException {
            dataOutputStream.writeUTF(String.valueOf(Category.getAllParents().size()));
            dataOutputStream.flush();
        }

        public void processGetCategoryName(int i) throws IOException {
            Category category = Category.getAllParents().get(i);
            dataOutputStream.writeUTF(category.getName());
            dataOutputStream.flush();
        }

        public void processSubCategorySizeFromParent(int i) throws IOException {
            dataOutputStream.writeUTF(String.valueOf(Category.getAllParents().get(i).getSubCategories().size()));
            dataOutputStream.flush();
        }

        public void processGetSubCategoryNameFromParent(String path) throws IOException {
            int i = Integer.parseInt(path.split("\\s")[0]);
            int j = Integer.parseInt(path.split("\\s")[1]);
            Category category = Category.getAllParents().get(i).getSubCategories().get(j);
            dataOutputStream.writeUTF(category.getName());
            dataOutputStream.flush();
        }

        public void processSubMenu(String subMenuText) {
            allowedCategory.clear();
            allowedCategory.addAll(Category.getCategoryByName(subMenuText).getSubCategories());
            productsToShow.clear();
            categoryFilterProducts.clear();

            for (Category subCategory1 : Category.getCategoryByName(subMenuText).getSubCategories()) {
                categoryFilterProducts.addAll(subCategory1.getAllSubProducts());
            }
            for (Product allSubProduct : Category.getCategoryByName(subMenuText).getAllSubProducts()) {
                if (!categoryFilterProducts.contains(allSubProduct))
                    categoryFilterProducts.add(allSubProduct);
            }
            productsToShow.addAll(categoryFilterProducts);


        }

        public void processCategoryPropertyCheckBox() {
            productsToShow.clear();
            allowedCategory.clear();
        }

        public void processCategoryPropertyCheckBoxAction(String categoryName) {
            Category category = Category.getCategoryByName(categoryName);
            allowedCategory.add(category);
            productsToShow.addAll(category.getAllSubProducts());
        }

        public void processCategoryPropertyCheckBoxInElse(String categoryName) {
            Category category = Category.getCategoryByName(categoryName);
            productsToShow.removeAll(category.getAllSubProducts());
            allowedCategory.remove(category);

        }

        public void processGetAllowedCategorySize() throws IOException {
            dataOutputStream.writeUTF(String.valueOf(allowedCategory.size()));
            dataOutputStream.flush();
        }

        public void processGetProductToShowSize() throws IOException {
            dataOutputStream.writeUTF(String.valueOf(productsToShow.size()));
            dataOutputStream.flush();
        }

        public void processCategoryPropertyAdd(String categoryName) {
            allowedCategory.add(Category.getCategoryByName(categoryName));
        }

        public void processCategoryPropertyAddDown(String categoryName) {
            productsToShow.addAll(Category.getCategoryByName(categoryName).getAllSubProducts());
        }

        public void processCheckSatisfiedInFilterFirst(String path) throws IOException {
            int i = Integer.parseInt(path.split("\\s")[0]);
            int min = Integer.parseInt(path.split("\\s")[1]);
            int max = Integer.parseInt(path.split("\\s")[2]);
            if (productsToShow.get(i).getPrice() < min || productsToShow.get(i).getPrice() > max || !allowedCategory.contains(productsToShow.get(i).getCategory())) {
                productsToShow.remove(i);
                i--;
            }
            dataOutputStream.writeUTF(String.valueOf(i));
            dataOutputStream.flush();
        }

        public void processCheckSatisfiedInFilterSecond(String path) throws IOException {
            int i = Integer.parseInt(path.split("\\s")[0]);
            String name = path.substring(path.split("\\s")[0].length() + 1);
            if (!productsToShow.get(i).getName().startsWith(name) || !allowedCategory.contains(productsToShow.get(i).getCategory())) {
                productsToShow.remove(i);
                i--;
            }
            dataOutputStream.writeUTF(String.valueOf(i));
            dataOutputStream.flush();
        }

        public void processGetAllFilterSize() throws IOException {
            dataOutputStream.writeUTF(String.valueOf(ProductsPageController.getAllFilters().size()));
            dataOutputStream.flush();
        }

        public void processGetFilterByNum(int i) throws IOException {
            dataOutputStream.writeUTF(ProductsPageController.getAllFilters().get(i));
            dataOutputStream.flush();
        }

        public void processGetProductPriceExplanationByName(String productName) throws IOException {
            Product product = Product.getProductByName(productName);
            dataOutputStream.writeUTF(product.getPrice() + " " + product.getImageName() + " " + product.getExplanationText());
            dataOutputStream.flush();
        }

        public void processGetCategoryAttributesProductSize(String productName) throws IOException {
            Product product = Product.getProductByName(productName);
            dataOutputStream.writeUTF(String.valueOf(product.getCategory().getAllSubProducts().size()));
            dataOutputStream.flush();
        }

        public void processGetCategoryAttributesProduct(String path) throws IOException {
            String productName = path.split("\\s")[0];
            int i = Integer.parseInt(path.substring(productName.length() + 1));
            dataOutputStream.writeUTF(Product.getProductByName(productName).getCategory().getAttributes().get(i));
            dataOutputStream.flush();
        }

        public void processCommentAction(String path) {
            String productName = path.split("\\s")[0];
            String commentField = path.substring(productName.length() + 1);
            new Comment(LoginPageController.getLoggedInAccount(), Product.getProductByName(productName), commentField, null);
        }

        public void processRatingAction(String path) throws IOException {
            String productName = path.split("\\s")[0];
            int rate = Integer.parseInt(path.substring(productName.length() + 1));
            if (Product.getProductByName(productName).isPurchasedByPurchaser((Purchaser) (LoginPageController.getLoggedInAccount()))) {
                new Rating(Product.getProductByName(productName), (Purchaser) (LoginPageController.getLoggedInAccount()), rate);
                dataOutputStream.writeUTF("yes_in_if");
            } else {
                dataOutputStream.writeUTF("no_in_if");
            }
            dataOutputStream.flush();
        }

        public void processAddToCartAction(String productName) throws ProductIdNotExistsException, ProductAlreadyExistsInCartException {
            Product product = Product.getProductByName(productName);
            ProductsPageController.processShowProduct(product.getProductID());
            ProductPageController.processAddProductToCartEach();
        }

        public void processGetProductAllSeller(String productName) throws IOException {
            StringBuilder stringBuilder = new StringBuilder();
            for (Seller allSeller : Product.getProductByName(productName).getAllSellers()) {
                stringBuilder.append(allSeller.getUserName() + " ");
            }
            dataOutputStream.writeUTF(stringBuilder.toString());
            dataOutputStream.flush();
        }

        public void processGetCompanyNameSeller(String username) throws IOException {
            dataOutputStream.writeUTF(Seller.getSellerByUsername(username).getCompanyName());
            dataOutputStream.flush();
        }

        public void processCheckBoxProductSeller(String username) throws SellerUserNameNotExists {
            ProductPageController.processSelectSellerEach(username);
        }

        public void processGetAverageRatingProduct(String productName) throws IOException {
            dataOutputStream.writeUTF(String.valueOf(Product.getProductByName(productName).getAverageRating()));
            dataOutputStream.flush();
        }

        public void processProductGetAllCommentSize(String productName) throws IOException {
            dataOutputStream.writeUTF(String.valueOf(Product.getProductByName(productName).getAllComments().size()));
            dataOutputStream.flush();
        }

        public void processDoEachProductComment(String path) throws IOException {
            int i = Integer.parseInt(path.split("\\s")[0]);
            String productName = path.substring(path.split("\\s")[0].length() + 1);
            Comment comment = Product.getProductByName(productName).getAllComments().get(i);
            dataOutputStream.writeUTF(comment.getCommenter().getUserName() + " " + comment.getCommentText());
            dataOutputStream.flush();
        }

        public void processDisableSortEach() {
            ProductsPageController.processDisableSortEach(productsToShow);
        }

        public void processGetSubCategory(String categoryName) throws IOException {
            Category category = Category.getCategoryByName(categoryName);
            StringBuilder all = new StringBuilder();
            for (Category subCategory : category.getSubCategories()) {
                all.append(subCategory.getName() + " ");
            }
            dataOutputStream.writeUTF(all.toString());
            dataOutputStream.flush();
        }

        public void processGetProductNameFromProductToShow(int i) throws IOException {
            dataOutputStream.writeUTF(productsToShow.get(i).getName() + " " + productsToShow.get(i).getPrice() +
                    " " + productsToShow.get(i).getAverageRating() + " " +
                    productsToShow.get(i).getExplanationText());
            dataOutputStream.flush();
        }

        public void processProductNameOnAction(String productName) {
            ProductsPageController.setSelectedProduct(Product.getProductByName(productName));
        }

        public void processCheckProductIsAvailable(String productName) throws IOException {
            if (Product.getProductByName(productName).isAvailable()) {
                dataOutputStream.writeUTF("yes_is_available");
            } else dataOutputStream.writeUTF("no_not_available");
            dataOutputStream.flush();
        }

        public void processCheckProductIsInOffer(String productName) throws IOException {
            if (Product.getProductByName(productName).getOffer() != null) {
                dataOutputStream.writeUTF("yes_is_offer");
            } else dataOutputStream.writeUTF("no_not_offer");
            dataOutputStream.flush();
        }

        public void processGetProductImageName(int i) throws IOException {
            dataOutputStream.writeUTF(productsToShow.get(i).getImageName());
            dataOutputStream.flush();
        }
    }
}
