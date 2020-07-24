package client.graphicView.productMenu;

import client.Main;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ProductsMenu {
    public Menu id;
    public Pane root;
    private TableView<CategoryProperty> tableView;
    //    private ArrayList<Product> productsToShow = new ArrayList<>();
//    private ArrayList<Product> categoryFilterProducts = new ArrayList<>();
    private static ArrayList<CategoryProperty> allCategoryProperty = new ArrayList<>();
    //    private ArrayList<Category> allowedCategory = new ArrayList<>();
    private Scene previousScene;
    private double xLayoutTable;
    private double yLayoutTable;
    private Pane secondRoot;
    private Pane productRoot;
    private TextField minPriceField;
    private TextField maxPriceField;
    private TextField search;
    private Button back;
    private Label minPrice;
    private Label maxPrice;
    public static Scene mainMenuScene;
    public MenuButton categoriesMenuButton;

    private final int port = 9010;
    private final String ip = "127.0.0.1";
    private DataOutputStream out;
    private DataInputStream in;

    public void input() {
        while (true) {
            String input = null;
            try {
                input = in.readUTF();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (input.startsWith("")) {

            }
        }
    }

    public void output() {

    }

    public void process() throws IOException {
        Socket socket = new Socket(ip, port);
        out = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
        in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        new Thread(this::output).start();
        new Thread(this::input).start();
    }

    private ObservableList<CategoryProperty> getCategoryProperties(ArrayList<Category> subCategories) {
        allCategoryProperty.clear();
        ObservableList<CategoryProperty> categoryProperties = FXCollections.observableArrayList();
        for (Category subCategory : subCategories) {
            categoryProperties.add(new CategoryProperty(subCategory));
        }
        return categoryProperties;
    }

    x

    private ObservableList<CommentProperty> getCommentProperties(Product product) {
        out.writeUTF("product_get_all_comment " +);
        ObservableList<CommentProperty> commentProperties = FXCollections.observableArrayList();
        for (Comment comment : product.getAllComments()) {
            commentProperties.add(new CommentProperty(comment.getCommentText(), comment.getCommenter().getUserName()));
        }
        return commentProperties;
    }

    public Scene getMainMenuScene() {
        return mainMenuScene;
    }

    public void setTableView(Category category) {
        TableView tableView = new TableView();
        TableColumn<CategoryProperty, String> brand = new TableColumn("Brand");
        brand.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<CategoryProperty, CheckBox> checkBox = new TableColumn<>("");
        checkBox.setCellValueFactory(new PropertyValueFactory<>("checkBox"));
        tableView.getColumns().addAll(brand, checkBox);
        tableView.setItems(getCategoryProperties(category.getSubCategories()));
        brand.setPrefWidth(200);
        tableView.setPrefWidth(brand.getWidth() + checkBox.getWidth() - 41);
        tableView.setLayoutX(Main.window.getScene().getWidth() - tableView.getPrefWidth());
        tableView.setLayoutY(125);
        setXLayoutTable(tableView.getLayoutX());
        setYLayoutTable(tableView.getLayoutY() + 440);
        tableView.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, null, null)));
        this.tableView = tableView;
    }

    public void setCommentMenu(VBox second, Product product) {
        second.setSpacing(15);
        TableView commentMenu = new TableView();
        TableColumn<CommentProperty, String> commenter = new TableColumn("Account");
        commenter.setCellValueFactory(new PropertyValueFactory<>("commenterUserName"));
        TableColumn<CommentProperty, String> text = new TableColumn<>("Commented");
        text.setCellValueFactory(new PropertyValueFactory<>("comment"));
        commentMenu.getColumns().addAll(commenter, text);
        commentMenu.setItems(getCommentProperties(product));
        commenter.setPrefWidth(200);
        text.setPrefWidth(800);
        second.getChildren().add(commentMenu);
        second.setLayoutY(777);
        second.setLayoutX(150);
    }

    public void showProductRate(Text rate, Product product) {
        rate.setText("Rate : " + product.getAverageRating());
        rate.setFont(Font.font(18));
        rate.setLayoutX(100);
        rate.setLayoutY(500);
    }

    public void setYLayoutTable(double yLayoutTable) {
        this.yLayoutTable = yLayoutTable;
    }

    public void setXLayoutTable(double xLayoutTable) {
        this.xLayoutTable = xLayoutTable;
    }

    public double getYLayoutTable() {
        return yLayoutTable;
    }

    public double getXLayoutTable() {
        return xLayoutTable;
    }

    public TableView<CategoryProperty> getTableView() {
        return tableView;
    }

    public void setPreviousScene(Scene previousScene) {
        this.previousScene = previousScene;
    }

    public Scene getPreviousScene() {
        return previousScene;
    }

    @FXML
    public void onCategories(Event event) throws IOException {
        categoriesMenuButton.getItems().clear();
        out.writeUTF("category_all_parent_size");
        out.flush();
        int size = Integer.parseInt(in.readUTF());
        for (int i = 0; i < size; i++) {
            out.writeUTF("get_category_name " + i);
            out.flush();
            String categoryName = in.readUTF();
            Menu menu = new Menu(categoryName);
            categoriesMenuButton.getItems().add(menu);
            out.writeUTF("sub_category_size_from_parent " + i);
            out.flush();
            int subSize = Integer.parseInt(in.readUTF());
            for (int j = 0; j < subSize; j++) {
                out.writeUTF("get_sub_category_name_From_Parent " + i + " " + j);
                out.flush();
                String subName = in.readUTF();
                MenuItem subMenu = new MenuItem(subName);
                subMenu.setOnAction(actionEvent -> {
                    try {
                        out.writeUTF("sub_menu_ " + subMenu.getText());
                        out.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                    try {
                        setTableView(Category.getCategoryByName(subMenu.getText()));


                        setPreviousScene(Main.window.getScene());
                        openTheSecondaryCategory(true);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                menu.getItems().add(subMenu);
            }
        }
    }

    public void setSorts(CheckMenuItem priceUpToDown, CheckMenuItem priceDownToUp, CheckMenuItem
            dateUpToDown, CheckMenuItem dateDownToUp, CheckMenuItem scoreUpToDown, CheckMenuItem
                                 scoreDownToUp, CheckMenuItem viewUpToDown, CheckMenuItem viewDownToUp) {
        priceUpToDown.setOnAction(actionEvent -> {
            if (priceUpToDown.isSelected()) {
                if (priceDownToUp.isSelected())
                    priceDownToUp.setSelected(false);
                if (dateUpToDown.isSelected())
                    dateUpToDown.setSelected(false);
                if (dateDownToUp.isSelected())
                    dateDownToUp.setSelected(false);
                if (viewUpToDown.isSelected())
                    viewUpToDown.setSelected(false);
                if (viewDownToUp.isSelected())
                    viewDownToUp.setSelected(false);
                if (scoreUpToDown.isSelected())
                    scoreUpToDown.setSelected(false);
                if (scoreDownToUp.isSelected())
                    scoreDownToUp.setSelected(false);
                clickPrice(true);
            } else {
                ProductsPageController.processDisableSortEach(productsToShow);
            }
            secondRoot.getChildren().remove(productRoot);
            processShowProducts();
        });
        priceDownToUp.setOnAction(actionEvent -> {
            if (priceDownToUp.isSelected()) {
                if (priceUpToDown.isSelected())
                    priceUpToDown.setSelected(false);
                if (scoreUpToDown.isSelected())
                    scoreUpToDown.setSelected(false);
                if (scoreDownToUp.isSelected())
                    scoreDownToUp.setSelected(false);
                if (viewUpToDown.isSelected())
                    viewUpToDown.setSelected(false);
                if (viewDownToUp.isSelected())
                    viewDownToUp.setSelected(false);
                if (dateUpToDown.isSelected())
                    dateUpToDown.setSelected(false);
                if (dateDownToUp.isSelected())
                    dateDownToUp.setSelected(false);
                clickPrice(false);
            } else {
                ProductsPageController.processDisableSortEach(productsToShow);
            }
            secondRoot.getChildren().remove(productRoot);
            processShowProducts();
        });
        scoreUpToDown.setOnAction(actionEvent -> {
            if (scoreUpToDown.isSelected()) {
                if (priceUpToDown.isSelected())
                    priceUpToDown.setSelected(false);
                if (priceDownToUp.isSelected())
                    priceDownToUp.setSelected(false);
                if (scoreDownToUp.isSelected())
                    scoreDownToUp.setSelected(false);
                if (viewUpToDown.isSelected())
                    viewUpToDown.setSelected(false);
                if (viewDownToUp.isSelected())
                    viewDownToUp.setSelected(false);
                if (dateUpToDown.isSelected())
                    dateUpToDown.setSelected(false);
                if (dateDownToUp.isSelected())
                    dateDownToUp.setSelected(false);
                clickScore(true);
            } else {
                ProductsPageController.processDisableSortEach(productsToShow);
            }
            secondRoot.getChildren().remove(productRoot);
            processShowProducts();
        });
        scoreDownToUp.setOnAction(actionEvent -> {
            if (scoreDownToUp.isSelected()) {
                if (priceUpToDown.isSelected())
                    priceUpToDown.setSelected(false);
                if (scoreUpToDown.isSelected())
                    scoreUpToDown.setSelected(false);
                if (priceDownToUp.isSelected())
                    priceDownToUp.setSelected(false);
                if (viewUpToDown.isSelected())
                    viewUpToDown.setSelected(false);
                if (viewDownToUp.isSelected())
                    viewDownToUp.setSelected(false);
                if (dateUpToDown.isSelected())
                    dateUpToDown.setSelected(false);
                if (dateDownToUp.isSelected())
                    dateDownToUp.setSelected(false);
                clickScore(false);
            } else {
                ProductsPageController.processDisableSortEach(productsToShow);
            }
            secondRoot.getChildren().remove(productRoot);
            processShowProducts();
        });
        viewUpToDown.setOnAction(actionEvent -> {
            if (viewUpToDown.isSelected()) {
                if (priceUpToDown.isSelected())
                    priceUpToDown.setSelected(false);
                if (scoreUpToDown.isSelected())
                    scoreUpToDown.setSelected(false);
                if (scoreDownToUp.isSelected())
                    scoreDownToUp.setSelected(false);
                if (priceDownToUp.isSelected())
                    priceDownToUp.setSelected(false);
                if (viewDownToUp.isSelected())
                    viewDownToUp.setSelected(false);
                if (dateUpToDown.isSelected())
                    dateUpToDown.setSelected(false);
                if (dateDownToUp.isSelected())
                    dateDownToUp.setSelected(false);
                clickView(true);
            } else {
                ProductsPageController.processDisableSortEach(productsToShow);
            }
            secondRoot.getChildren().remove(productRoot);
            processShowProducts();
        });
        viewDownToUp.setOnAction(actionEvent -> {
            if (viewDownToUp.isSelected()) {
                if (priceUpToDown.isSelected())
                    priceUpToDown.setSelected(false);
                if (scoreUpToDown.isSelected())
                    scoreUpToDown.setSelected(false);
                if (scoreDownToUp.isSelected())
                    scoreDownToUp.setSelected(false);
                if (viewUpToDown.isSelected())
                    viewUpToDown.setSelected(false);
                if (priceDownToUp.isSelected())
                    priceDownToUp.setSelected(false);
                if (dateUpToDown.isSelected())
                    dateUpToDown.setSelected(false);
                if (dateDownToUp.isSelected())
                    dateDownToUp.setSelected(false);
                clickView(false);
            } else {
                ProductsPageController.processDisableSortEach(productsToShow);
            }
            secondRoot.getChildren().remove(productRoot);
            processShowProducts();
        });
        dateUpToDown.setOnAction(actionEvent -> {
            if (dateUpToDown.isSelected()) {
                if (priceUpToDown.isSelected())
                    priceUpToDown.setSelected(false);
                if (scoreUpToDown.isSelected())
                    scoreUpToDown.setSelected(false);
                if (scoreDownToUp.isSelected())
                    scoreDownToUp.setSelected(false);
                if (viewUpToDown.isSelected())
                    viewUpToDown.setSelected(false);
                if (viewDownToUp.isSelected())
                    viewDownToUp.setSelected(false);
                if (priceDownToUp.isSelected())
                    priceDownToUp.setSelected(false);
                if (dateDownToUp.isSelected())
                    dateDownToUp.setSelected(false);
                clickTime(true);
            } else {
                ProductsPageController.processDisableSortEach(productsToShow);
            }
            secondRoot.getChildren().remove(productRoot);
            processShowProducts();
        });
        dateDownToUp.setOnAction(actionEvent -> {
            if (dateDownToUp.isSelected()) {
                if (priceUpToDown.isSelected())
                    priceUpToDown.setSelected(false);
                if (scoreUpToDown.isSelected())
                    scoreUpToDown.setSelected(false);
                if (scoreDownToUp.isSelected())
                    scoreDownToUp.setSelected(false);
                if (viewUpToDown.isSelected())
                    viewUpToDown.setSelected(false);
                if (viewDownToUp.isSelected())
                    viewDownToUp.setSelected(false);
                if (dateUpToDown.isSelected())
                    dateUpToDown.setSelected(false);
                if (priceDownToUp.isSelected())
                    priceDownToUp.setSelected(false);
                clickTime(false);
            } else {
                ProductsPageController.processDisableSortEach(productsToShow);
            }
            secondRoot.getChildren().remove(productRoot);
            processShowProducts();
        });
    }

    public void openTheSecondaryCategory(boolean firstTime) throws IOException {
        if (firstTime) {
            secondRoot = new Pane();
            secondRoot.setBackground(new Background(new BackgroundFill(Color.SILVER, null, null)));
            back = new Button("Back");
            back.setFont(Font.font(20));
            back.setLayoutX(1200);
            back.setOnAction(actionEvent -> {
                try {
                    out.writeUTF("back_in_secondary");
                    out.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Main.window.setScene(getPreviousScene());
            });
            secondRoot.getChildren().add(back);
            MenuButton sort = new MenuButton("Sort");
            CheckMenuItem priceUpToDown = new CheckMenuItem("Price Up to Down");
            CheckMenuItem dateUpToDown = new CheckMenuItem("Date Up to Down");
            CheckMenuItem scoreUpToDown = new CheckMenuItem("Score Up to Down");
            CheckMenuItem viewUpToDown = new CheckMenuItem("View Up to Down");
            CheckMenuItem priceDownToUp = new CheckMenuItem("Price Down to Up");
            CheckMenuItem dateDownToUp = new CheckMenuItem("Date Down to Up");
            CheckMenuItem scoreDownToUp = new CheckMenuItem("Score Down to Up");
            CheckMenuItem viewDownToUp = new CheckMenuItem("View Down to Up");
            sort.getItems().addAll(priceUpToDown, priceDownToUp, dateUpToDown, dateDownToUp, scoreUpToDown, scoreDownToUp, viewUpToDown, viewDownToUp);
            sort.setLayoutX(888);
            sort.setLayoutY(120);
            sort.setFont(Font.font(16));
            setSorts(priceUpToDown, priceDownToUp, dateUpToDown, dateDownToUp, scoreUpToDown, scoreDownToUp, viewUpToDown, viewDownToUp);
            secondRoot.getChildren().add(sort);
            secondRoot.getChildren().add(getTableView());
            minPrice = new Label("Min Price");
            minPriceField = new TextField("");
            minPriceField.setFont(Font.font(12));
            minPrice.setFont(Font.font(16));
            minPrice.setLayoutX(getXLayoutTable());
            minPrice.setLayoutY(getYLayoutTable());
            minPriceField.setLayoutX(getXLayoutTable() + 85);
            minPriceField.setLayoutY(getYLayoutTable());
            maxPrice = new Label("Max Price");
            maxPriceField = new TextField("");
            maxPriceField.setFont(Font.font(12));
            maxPrice.setFont(Font.font(16));
            maxPrice.setLayoutX(getXLayoutTable());
            maxPrice.setLayoutY(getYLayoutTable() + 39);
            maxPriceField.setLayoutX(getXLayoutTable() + 85);
            maxPriceField.setLayoutY(getYLayoutTable() + 39);
            search = new TextField("");
            search.setPromptText("Search Products");
            search.setLayoutX(1050);
            search.setLayoutY(50);
            search.setFont(Font.font(17));
            secondRoot.getChildren().addAll(minPrice, minPriceField, maxPrice, maxPriceField, search);
        } else secondRoot.getChildren().remove(productRoot);
        minPriceField.setOnAction(actionEvent -> {
            try {
                priceAction(maxPriceField.getText(), minPriceField.getText());
                secondRoot.getChildren().remove(productRoot);
                processShowProducts();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        maxPriceField.setOnAction(actionEvent -> {
            try {
                priceAction(maxPriceField.getText(), minPriceField.getText());
                secondRoot.getChildren().remove(productRoot);
                processShowProducts();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        search.setOnAction(actionEvent -> {
            try {
                productNameAction(search.getText());
                secondRoot.getChildren().remove(productRoot);
                processShowProducts();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        processShowProducts();
        ScrollPane scrollPane = new ScrollPane(secondRoot);
        Main.window.setScene(new Scene(scrollPane));
    }

    public void processShowProducts() throws IOException {
        productRoot = new Pane();
        int i = 0;
        int yLayout = 200;
        int xLayout = 100;
        checkSatisfiedInFilters();
        for (Product subProduct : productsToShow) {
            if (i == 3) {
                yLayout += 350;
                i = 0;
            }
            ImageView imageView = new ImageView(new Image(new FileInputStream("src/main/resources/media/image/" + subProduct.getImageName())));
            imageView.setFitWidth(169);
            imageView.setFitHeight(169);
            Text productName = new Text(subProduct.getName());
            productName.setFont(Font.font(20));
            productName.setOnMouseClicked(mouseEvent -> {
                try {
                    ProductsPageController.setSelectedProduct(Product.getProductByName(productName.getText()));
                    openProductPage(Main.window.getScene(), Product.getProductByName(productName.getText()));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            });
            Text productExplanation = new Text(subProduct.getExplanationText());
            productExplanation.setFont(Font.font(19));
            Text productPrice = new Text("Price : " + subProduct.getPrice() + " $");
            productPrice.setFont(Font.font(18));
            Text rating = new Text("Rate : " + subProduct.getAverageRating());
            rating.setFont(Font.font(17));
            Text available;
            if (subProduct.isAvailable())
                available = new Text("Available : Yes");
            else available = new Text("Available : No");
            available.setFont(Font.font(16));
            VBox vBox = new VBox();
            vBox.setAlignment(Pos.CENTER);
            vBox.setSpacing(7);
            vBox.getChildren().addAll(imageView, productName, productExplanation, productPrice, rating, available);
            if (subProduct.getOffer() != null) {
                Text offer = new Text("This is in Offer!");
                offer.setFont(Font.font(17));
                vBox.getChildren().add(offer);
            }

            vBox.setLayoutX(xLayout + i * 300);
            vBox.setLayoutY(yLayout);

            productRoot.getChildren().add(vBox);
            i++;
        }
        secondRoot.getChildren().add(productRoot);
    }

    public void openProductPage(Scene previousScene, String productName) throws IOException {
        out.writeUTF("get_product_price_imageName_explanation_by_name " + productName);
        out.flush();
        String response = in.readUTF();
        int productPrice = Integer.parseInt(response.split("\\s")[0]);
        String productImageName = response.split("\\s")[1];
        String productExplanationContext = response.substring(response.split("\\s")[0].length() + 1 + response.split("\\s")[1].length() + 1);
        Pane productRoot = new Pane();
        Button back = new Button("Back");
        back.setLayoutX(1200);
        back.setFont(Font.font(20));
        back.setOnAction(actionEvent -> Main.window.setScene(previousScene));
        ImageView imageView = new ImageView(new Image(new FileInputStream("src/main/resources/media/image/" + productImageName)));
        imageView.setFitWidth(379);
        imageView.setFitHeight(379);
        imageView.setLayoutX(85);
        imageView.setLayoutY(69);
        Text price = new Text("Price : " + productPrice + " $");
        price.setFont(Font.font(21));
        price.setLayoutX(376);
        price.setLayoutY(500);
        Text rate = new Text();
        showProductRate(rate, product);
        VBox first = new VBox();
        first.setSpacing(15);
        Text name = new Text(productName);
        name.setFont(Font.font(29));
        name.setTranslateY(-10);
        Label productExplanation = new Label("Explanation");
        productExplanation.setFont(Font.font(24));
        productExplanation.setTextFill(Color.MEDIUMSEAGREEN);
        Text explanation = new Text(productExplanationContext);
        explanation.setFont(Font.font(19));
        explanation.setTranslateY(4);
        Label categoryLabel = new Label("Category attributes :");
        categoryLabel.setFont(Font.font(24));
        categoryLabel.setTranslateY(23);
        categoryLabel.setTextFill(Color.MEDIUMSEAGREEN);
        StringBuilder stringBuilder = new StringBuilder();
        out.writeUTF("get_category_attributes_product_size " + productName);
        out.flush();
        int length = Integer.parseInt(in.readUTF());
        for (int i = 0; i < length; i++) {

        }
        out.writeUTF("get_category_attributes_product " + productName);
        out.flush();
        for (String s : product.getCategory().getAttributes()) {
            stringBuilder.append(s + "\n\n");
        }
        Text categoryAttribute = new Text(stringBuilder.toString());
        categoryAttribute.setTranslateY(20);
        categoryAttribute.setFont(Font.font(19));
        VBox second = new VBox();
        setCommentMenu(second, product);
        Button comment = new Button("Comment");
        TextField commentField = new TextField("");
        commentField.setTranslateY(-10);
        commentField.setTranslateX(comment.getLayoutX() + 150);
        comment.setFont(Font.font(16));
        comment.setTranslateY(35);
        comment.setOnAction(actionEvent -> {
            new Comment(LoginPageController.getLoggedInAccount(), product, commentField.getText(), null);
            second.getChildren().clear();
            setCommentMenu(second, product);
            commentField.setText("");
        });

        Button rating = new Button("Rate this product");
        rate.setFont(Font.font(18));
        TextField rateField = new TextField("");
        rateField.setTranslateY(-40);
        rateField.setTranslateX(rating.getLayoutX() + 150);
        rating.setOnAction(actionEvent -> {
            if (product.isPurchasedByPurchaser((Purchaser) (LoginPageController.getLoggedInAccount()))) {
                new Rating(product, (Purchaser) (LoginPageController.getLoggedInAccount()), Integer.parseInt(rateField.getText()));
                showProductRate(rate, product);
                rateField.setText("");
            }
        });
        Button add = new Button("Add to cart");
        add.setDisable(true);
        add.setFont(Font.font(19));
        add.setLayoutY(55);
        add.setOnAction(actionEvent -> {
            ProductsPageController.processShowProduct(product.getProductID());
            ProductPageController.processAddProductToCartEach();
        });
        first.getChildren().addAll(name, productExplanation, explanation, categoryLabel, categoryAttribute, comment, commentField, rating, rateField, add);
        first.setLayoutX(666);
        first.setLayoutY(100);
        HBox hBox = new HBox();
        hBox.setSpacing(15);
        ArrayList<CheckBox> allSellerCheckBoxes = new ArrayList<>();
        for (Seller allSeller : product.getAllSellers()) {
            Text seller = new Text(allSeller.getCompanyName());
            seller.setTranslateX(15);
            seller.setFont(Font.font(19));
            CheckBox checkBox = new CheckBox();
            checkBox.setTranslateX(10);
            allSellerCheckBoxes.add(checkBox);
            hBox.getChildren().addAll(seller, checkBox);
            checkBox.setOnAction(actionEvent -> {
                if (checkBox.isSelected()) {
                    add.setDisable(false);
                    for (CheckBox allSellerCheckBox : allSellerCheckBoxes) {
                        if (allSellerCheckBox != checkBox)
                            allSellerCheckBox.setSelected(false);
                    }
                    ProductPageController.processSelectSellerEach(allSeller.getUserName());
                } else {
                    add.setDisable(true);
                }
            });
        }
        hBox.setLayoutX(79);
        hBox.setLayoutY(550);

        productRoot.getChildren().addAll(back, imageView, price, rate, first, hBox, second);
        ScrollPane scrollPane = new ScrollPane(productRoot);
        Main.window.setScene(new Scene(scrollPane));
    }

    public void OnBackProductsMenu() {
        Main.window.setScene(mainMenuScene);
    }

    public class CommentProperty {
        private StringProperty comment;
        private StringProperty commenterUserName;

        public CommentProperty(String comment, String commenterUserName) {
            this.comment = new SimpleStringProperty(comment);
            this.commenterUserName = new SimpleStringProperty(commenterUserName);
        }

        public String getComment() {
            return comment.get();
        }

        public StringProperty commentProperty() {
            return comment;
        }

        public String getCommenterUserName() {
            return commenterUserName.get();
        }

        public StringProperty commenterUserNameProperty() {
            return commenterUserName;
        }
    }

    public void checkSatisfiedInFilters() throws IOException {
        out.writeUTF("get_all_filter_size");
        out.flush();
        int size1 = Integer.parseInt(in.readUTF());
        for (int j = 0; j < size1; j++) {
            out.writeUTF("get_filter_by_num " + j);
            out.flush();
            String allFilter = in.readUTF();
            if (allFilter.startsWith("BY_PRICE_RANGE_")) {
                int min = Integer.parseInt(allFilter.substring(15).split(",")[0]);
                int max = Integer.parseInt(allFilter.substring(15).split(",")[1]);
                out.writeUTF("get_product_to_show_size");
                out.flush();
                int size = Integer.parseInt(in.readUTF());
                for (int i = 0; i < size; i++) {
                    out.writeUTF("check_satisfied_in_filter_first " + i + " " + min + " " + max);
                    out.flush();
                    i = Integer.parseInt(in.readUTF());
                }
            }
            if (allFilter.startsWith("BY_NAME_")) {
                String name = allFilter.substring(8);
                out.writeUTF("get_product_to_show_size");
                out.flush();
                int size = Integer.parseInt(in.readUTF());
                for (int i = 0; i < size; i++) {
                    out.writeUTF("check_satisfied_in_filter_second " + i + " " + name);
                    out.flush();
                    i = Integer.parseInt(in.readUTF());
                }
            }
        }
        out.writeUTF("switch_for_sort");
        out.flush();
        String response = in.readUTF();
        switch (response) {
            case "click_time_true" -> {
                clickTime(true);
                break;
            }
            case "click_time_false" -> {
                clickTime(false);
                break;
            }
            case "click_view_true" -> {
                clickView(true);
                break;
            }
            case "click_view_false" -> {
                clickView(false);
                break;
            }
            case "click_price_true" -> {
                clickPrice(true);
                break;
            }
            case "click_price_false" -> {
                clickPrice(false);
                break;
            }
            case "click_score_true" -> {
                clickScore(true);
                break;
            }
            case "click_score_false" -> {
                clickScore(false);
                break;
            }
        }
//        for (Seller seller : Seller.getAllSeller()) {
//            for (Product product : seller.getAuction()) {
//                for (int i = 0; i < productsToShow.size(); i++) {
//                    if (productsToShow.get(i).getProductID().equals(product.getProductID())) {
//                        productsToShow.remove(i);
//                        i--;
//                    }
//                }
//            }
//        }

    }

    public class CategoryProperty {
        private String categoryName;
        private CheckBox checkBox;
        private StringProperty name;

        public CategoryProperty(String categoryName) {
            this.categoryName = categoryName;
            this.checkBox = new CheckBox();
            this.name = new SimpleStringProperty(categoryName);
            allCategoryProperty.add(this);
            checkBox.setOnAction(actionEvent -> {
                if (checkBox.isSelected()) {
                    try {
                        out.writeUTF("category_property_check_box");
                        out.flush();
                        for (CategoryProperty categoryProperty : allCategoryProperty) {
                            if (categoryProperty.getCheckBox().isSelected()) {
                                out.writeUTF("category_property_check_box_action " + categoryProperty.getCategoryName());
                                out.flush();
                            }
                        }
                        openTheSecondaryCategory(false);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        out.writeUTF("category_property_check_box_in_else " + categoryName);
                        out.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        out.writeUTF("get_allowed_category_size");
                        out.flush();
                        int flag = Integer.parseInt(in.readUTF());
                        if (flag == 0) {
                            for (CategoryProperty categoryProperty : allCategoryProperty) {
                                out.writeUTF("category_property_add " + categoryProperty.getCategoryName());
                                out.flush();
                            }
                        }
                        out.writeUTF("get_product_to_show_size ");
                        out.flush();
                        flag = Integer.parseInt(in.readUTF());
                        if (flag == 0) {
                            for (CategoryProperty categoryProperty : allCategoryProperty) {
                                out.writeUTF("category_property_add_down " + categoryProperty.getCategoryName());
                                out.flush();
                            }
                        }
                        openTheSecondaryCategory(false);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        public String getName() {
            return name.get();
        }

        public String getCategoryName() {
            return categoryName;
        }

        public CheckBox getCheckBox() {
            return checkBox;
        }


        public StringProperty nameProperty() {
            return name;
        }
    }

    public void clickTime(boolean isUp) throws IOException {
        if (isUp) {
            out.writeUTF("click_time_true");
        } else {
            out.writeUTF("click_time_false");
        }
        out.flush();
    }

    public void clickScore(boolean isUp) throws IOException {
        if (isUp) {
            out.writeUTF("click_score_true");
        } else {
            out.writeUTF("click_score_false");
        }
        out.flush();
    }

    public void clickView(boolean isUp) throws IOException {
        if (isUp) {
            out.writeUTF("click_view_true");
        } else {
            out.writeUTF("click_view_false");
        }
        out.flush();
    }

    public void clickPrice(boolean isUp) throws IOException {
        if (isUp) {
            out.writeUTF("click_price_true");
        } else {
            out.writeUTF("click_price_false");
        }
        out.flush();
    }

    public void productNameAction(String productName) throws IOException {
        out.writeUTF("product_name_action_ " + productName);
        out.flush();
    }

    public void priceAction(String maxPriceField, String minPriceField) throws IOException {
        out.writeUTF("price_action_ " + minPriceField + " " + maxPriceField);
        out.flush();
    }
}
