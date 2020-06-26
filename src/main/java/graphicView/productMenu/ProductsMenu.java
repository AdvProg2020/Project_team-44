package graphicView.productMenu;

import controller.LoginPageController;
import controller.ProductPageController;
import controller.ProductsPageController;
import exception.FilterNotExistsException;
import exception.ProductAlreadyExistsInCartException;
import exception.ProductIdNotExistsException;
import exception.SellerUserNameNotExists;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import main.Main;
import model.Category;
import model.Rating;
import model.account.Purchaser;
import model.account.Seller;
import model.comment.Comment;
import model.product.Product;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class ProductsMenu {
    public Menu id;
    public Pane root;
    private TableView<CategoryProperty> tableView;
    private ArrayList<Product> productsToShow = new ArrayList<>();
    private ArrayList<Product> categoryFilterProducts = new ArrayList<>();
    private static ArrayList<CategoryProperty> allCategoryProperty = new ArrayList<>();
    private ArrayList<Category> allowedCategory = new ArrayList<>();
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

    public MenuButton categoriesMenuButton;
    public CheckMenuItem timeItem;
    public CheckMenuItem scoreItem;
    public CheckMenuItem viewItem;
    public CheckMenuItem priceItem;


    private ObservableList<CategoryProperty> getCategoryProperties(ArrayList<Category> subCategories) {
        allCategoryProperty.clear();
        ObservableList<CategoryProperty> categoryProperties = FXCollections.observableArrayList();
        for (Category subCategory : subCategories) {
            categoryProperties.add(new CategoryProperty(subCategory));
        }
        return categoryProperties;
    }

    private ObservableList<CommentProperty> getCommentProperties(Product product) {
        allCategoryProperty.clear();
        ObservableList<CommentProperty> commentProperties = FXCollections.observableArrayList();
        for (Comment comment : product.getAllComments()) {
            commentProperties.add(new CommentProperty(comment.getCommentText(), comment.getCommenter().getUserName()));
        }
        return commentProperties;
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
    public void onCategories(Event event) {
        categoriesMenuButton.getItems().clear();
        for (Category category : Category.getAllParents()) {
            Menu menu = new Menu(category.getName());
            categoriesMenuButton.getItems().add(menu);
            for (Category subCategory : category.getSubCategories()) {
                MenuItem subMenu = new MenuItem(subCategory.getName());
                subMenu.setOnAction(actionEvent -> {
                    allowedCategory = Category.getCategoryByName(subMenu.getText()).getSubCategories();
                    productsToShow.clear();
                    categoryFilterProducts.clear();
                    try {
                        setTableView(Category.getCategoryByName(subMenu.getText()));
                        for (Category subCategory1 : Category.getCategoryByName(subMenu.getText()).getSubCategories()) {
                            System.out.println(subCategory1.getAllSubProducts().size());
                            categoryFilterProducts.addAll(subCategory1.getAllSubProducts());

                        }
                        for (Product allSubProduct : Category.getCategoryByName(subMenu.getText()).getAllSubProducts()) {
                            if (!categoryFilterProducts.contains(allSubProduct))
                                categoryFilterProducts.add(allSubProduct);
                        }
                        productsToShow.addAll(categoryFilterProducts);
                        setPreviousScene(Main.window.getScene());
                        openTheSecondaryCategory(true);
                    } catch (FileNotFoundException | FilterNotExistsException e) {
                        e.printStackTrace();
                    }
                });
                menu.getItems().add(subMenu);
            }
        }
    }

    public void openTheSecondaryCategory(boolean firstTime) throws FileNotFoundException, FilterNotExistsException {

        if (firstTime) {
            secondRoot = new Pane();
            back = new Button("Back");
            back.setFont(Font.font(20));
            back.setLayoutX(1200);
            back.setOnAction(actionEvent -> Main.window.setScene(getPreviousScene()));
            secondRoot.getChildren().add(back);
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
            } catch (FilterNotExistsException | FileNotFoundException e) {
                e.printStackTrace();
            }
        });

        maxPriceField.setOnAction(actionEvent -> {
            try {
                priceAction(maxPriceField.getText(), minPriceField.getText());
                secondRoot.getChildren().remove(productRoot);
                processShowProducts();
            } catch (FilterNotExistsException | FileNotFoundException e) {
                e.printStackTrace();
            }
        });
        search.setOnAction(actionEvent -> {
            try {
                productNameAction(search.getText());
                secondRoot.getChildren().remove(productRoot);
                processShowProducts();
            } catch (FileNotFoundException | FilterNotExistsException e) {
                e.printStackTrace();
            }
        });
        processShowProducts();
        ScrollPane scrollPane = new ScrollPane(secondRoot);
        Main.window.setScene(new Scene(scrollPane));
    }

    public void processShowProducts() throws FileNotFoundException, FilterNotExistsException {
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
        secondRoot.setBackground(new Background(new BackgroundFill(Color.LIGHTSKYBLUE, null, null)));
    }

    public void openProductPage(Scene previousScene, Product product) throws FileNotFoundException {
        Pane productRoot = new Pane();
        Button back = new Button("Back");
        back.setLayoutX(1200);
        back.setFont(Font.font(20));
        back.setOnAction(actionEvent -> Main.window.setScene(previousScene));
        ImageView imageView = new ImageView(new Image(new FileInputStream("src/main/resources/media/image/" + product.getImageName())));
        imageView.setFitWidth(379);
        imageView.setFitHeight(379);
        imageView.setLayoutX(85);
        imageView.setLayoutY(69);
        Text price = new Text("Price : " + product.getPrice() + " $");
        price.setFont(Font.font(21));
        price.setLayoutX(376);
        price.setLayoutY(500);
        Text rate = new Text("Rate : " + product.getAverageRating());
        rate.setFont(Font.font(18));
        rate.setLayoutX(100);
        rate.setLayoutY(500);
        VBox first = new VBox();
        first.setSpacing(15);
        Text name = new Text(product.getName());
        name.setFont(Font.font(29));
        name.setTranslateY(-10);
        Label productExplanation = new Label("Explanation");
        productExplanation.setFont(Font.font(24));
        productExplanation.setTextFill(Color.MEDIUMSEAGREEN);
        Text explanation = new Text(product.getExplanationText());
        explanation.setFont(Font.font(19));
        explanation.setTranslateY(4);
        Label categoryLabel = new Label("Category attributes :");
        categoryLabel.setFont(Font.font(24));
        categoryLabel.setTranslateY(23);
        categoryLabel.setTextFill(Color.MEDIUMSEAGREEN);
        StringBuilder stringBuilder = new StringBuilder();
        if (product.getCategory().getAttributes().size() > 0)
            for (String s : product.getCategory().getAttributes()) {
                stringBuilder.append(s + "\n\n");
            }
        Text categoryAttribute = new Text(stringBuilder.toString());
        categoryAttribute.setTranslateY(20);
        categoryAttribute.setFont(Font.font(19));
        Button comment = new Button("Comment");
        comment.setFont(Font.font(16));
        comment.setTranslateY(35);
        comment.setOnAction(actionEvent -> new Comment(LoginPageController.getLoggedInAccount(), product, comment.getText(), null));
        TextField commentField = new TextField("");
        commentField.setTranslateY(-10);
        commentField.setTranslateX(comment.getLayoutX() + 150);
        Button rating = new Button("Rate this product");
        rate.setFont(Font.font(18));
        TextField rateField = new TextField("");
        rateField.setTranslateY(-40);
        rateField.setTranslateX(rating.getLayoutX() + 150);
        rating.setOnAction(actionEvent -> {
            if (product.isPurchasedByPurchaser((Purchaser) (LoginPageController.getLoggedInAccount())))
                new Rating(product, (Purchaser) (LoginPageController.getLoggedInAccount()), Integer.parseInt(rateField.getText()));
        });
        Button add = new Button("Add to cart");
        add.setFont(Font.font(19));
        add.setLayoutY(55);
        add.setOnAction(actionEvent -> {
            try {
                ProductsPageController.processShowProduct(product.getProductID());
                ProductPageController.processAddProductToCartEach();

            } catch (ProductAlreadyExistsInCartException | ProductIdNotExistsException e) {
                e.printStackTrace();
            }
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
                    for (CheckBox allSellerCheckBox : allSellerCheckBoxes) {
                        if (allSellerCheckBox != checkBox)
                            allSellerCheckBox.setSelected(false);
                    }
                    try {
                        ProductPageController.processSelectSellerEach(allSeller.getUserName());
                    } catch (SellerUserNameNotExists sellerUserNameNotExists) {
                        sellerUserNameNotExists.printStackTrace();
                    }
                }
            });
        }
        hBox.setLayoutX(79);
        hBox.setLayoutY(550);
        VBox second = new VBox();
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
        productRoot.getChildren().addAll(back, imageView, price, rate, first, hBox, second);
        ScrollPane scrollPane = new ScrollPane(productRoot);
        Main.window.setScene(new Scene(scrollPane));
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

    public void checkSatisfiedInFilters() {
        for (String allFilter : ProductsPageController.getAllFilters()) {
            if (allFilter.startsWith("BY_PRICE_RANGE_")) {
                int min = Integer.parseInt(allFilter.substring(15).split(",")[0]);
                int max = Integer.parseInt(allFilter.substring(15).split(",")[1]);
                for (int i = 0; i < productsToShow.size(); i++) {
                    if (productsToShow.get(i).getPrice() < min || productsToShow.get(i).getPrice() > max) {
                        productsToShow.remove(i);
                        i--;
                    }
                }
            }
            if (allFilter.startsWith("BY_NAME_")) {
                String name = allFilter.substring(8);
                for (int i = 0; i < productsToShow.size(); i++) {
                    if (!productsToShow.get(i).getName().startsWith(name)) {
                        productsToShow.remove(i);
                        i--;
                    }
                }
            }
        }
    }

    public class CategoryProperty {
        private Category category;
        private CheckBox checkBox;
        private StringProperty name;

        public CategoryProperty(Category category) {
            this.category = category;
            this.checkBox = new CheckBox();
            this.name = new SimpleStringProperty(category.getName());
            allCategoryProperty.add(this);
            checkBox.setOnAction(actionEvent -> {
                if (checkBox.isSelected()) {
                    try {
                        productsToShow.clear();
                        allowedCategory.clear();
                        for (CategoryProperty categoryProperty : allCategoryProperty) {
                            if (categoryProperty.getCheckBox().isSelected()) {
                                allowedCategory.add(categoryProperty.getCategory());
                                productsToShow.addAll(categoryProperty.getCategory().getAllSubProducts());

                            }
                        }
                        openTheSecondaryCategory(false);
                    } catch (FileNotFoundException | FilterNotExistsException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        productsToShow.removeAll(this.getCategory().getAllSubProducts());
                        allowedCategory.remove(this.getCategory());
                        if (allowedCategory.size() == 0) {
                            for (CategoryProperty categoryProperty : allCategoryProperty) {
                                allowedCategory.add(categoryProperty.getCategory());
                            }
                        }
                        if (productsToShow.size() == 0) {
                            for (CategoryProperty categoryProperty : allCategoryProperty) {
                                productsToShow.addAll(categoryProperty.getCategory().getAllSubProducts());
                            }
                        }
                        openTheSecondaryCategory(false);
                    } catch (FileNotFoundException | FilterNotExistsException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        public Category getCategory() {
            return category;
        }

        public CheckBox getCheckBox() {
            return checkBox;
        }


        public StringProperty nameProperty() {
            return name;
        }
    }

    public void clickTime(ActionEvent actionEvent) {
        scoreItem.setSelected(false);
        viewItem.setSelected(false);
        priceItem.setSelected(false);
        ProductsPageController.processSortByTime(true);
    }

    public void clickScore(ActionEvent actionEvent) {
        timeItem.setSelected(false);
        viewItem.setSelected(false);
        priceItem.setSelected(false);
        if (scoreItem.isSelected()) {
            ProductsPageController.processSortByScore(true);
        } else ProductsPageController.processDisableSortEach();
    }

    public void clickView(ActionEvent actionEvent) {
        scoreItem.setSelected(false);
        timeItem.setSelected(false);
        priceItem.setSelected(false);
        if (viewItem.isSelected()) {
            ProductsPageController.processSortByView(true);
        } else ProductsPageController.processDisableSortEach();
    }

    public void clickPrice(ActionEvent actionEvent) {
        scoreItem.setSelected(false);
        timeItem.setSelected(false);
        viewItem.setSelected(false);
        if (priceItem.isSelected()) {
            ProductsPageController.processSortByPrice(true);
        } else ProductsPageController.processDisableSortEach();
    }

    public void productNameAction(String productName) {
        try {
            if (productName != null)
                ProductsPageController.processFilter("BY_NAME", productName, categoryFilterProducts, productsToShow, allowedCategory);
        } catch (FilterNotExistsException e) {
            e.printStackTrace();
        }
    }

    public void priceAction(String maxPriceField, String minPriceField) throws FilterNotExistsException {
        if (!minPriceField.isBlank() && maxPriceField.isBlank()) {
            ProductsPageController.processFilter("BY_PRICE", Integer.parseInt(minPriceField) + "," + Integer.MAX_VALUE, categoryFilterProducts, productsToShow, allowedCategory);
        } else if (!maxPriceField.isBlank() && minPriceField.isBlank()) {
            ProductsPageController.processFilter("BY_PRICE", 0 + "," + Integer.parseInt(maxPriceField), categoryFilterProducts, productsToShow, allowedCategory);
        } else if (!minPriceField.isBlank() && !maxPriceField.isBlank()) {
            ProductsPageController.processFilter("BY_PRICE", Integer.parseInt(minPriceField) + "," + Integer.parseInt(maxPriceField), categoryFilterProducts, productsToShow, allowedCategory);
        } else
            ProductsPageController.processFilter("BY_PRICE", -1 + "," + Integer.MAX_VALUE, categoryFilterProducts, productsToShow, allowedCategory);
    }
}
