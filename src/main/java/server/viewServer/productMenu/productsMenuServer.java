package server.viewServer.productMenu;

import server.controller.ProductsPageController;
import server.exception.FilterNotExistsException;
import server.model.Category;
import server.model.Sort.Sort;
import server.model.product.Product;
import server.viewServer.purchasePage.PurchasePagePaymentServer;

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
                    }
                }
            } catch (IOException | FilterNotExistsException e) {
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

        public void

    }
}
