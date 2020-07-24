package client.graphicView.productMenu;

import client.Main;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ProductsMenu implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            process();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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

    public void process() throws IOException {
        Socket socket = new Socket(ip, port);
        out = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
        in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
    }

    private ObservableList<CategoryProperty> getCategoryProperties(ArrayList<String> subCategories) {
        allCategoryProperty.clear();
        ObservableList<CategoryProperty> categoryProperties = FXCollections.observableArrayList();
        for (String subCategory : subCategories) {
            categoryProperties.add(new CategoryProperty(subCategory));
        }
        return categoryProperties;
    }

    private ObservableList<CommentProperty> getCommentProperties(String productName) throws IOException {
        out.writeUTF("product_get_all_comment_size " + productName);
        out.flush();
        int length = Integer.parseInt(in.readUTF());
        ObservableList<CommentProperty> commentProperties = FXCollections.observableArrayList();
        for (int i = 0; i < length; i++) {
            out.writeUTF("do_each_product_comment " + i + " " + productName);
            out.flush();
            String response = in.readUTF();
            String username = response.split("\\s")[0];
            String text = response.substring(username.length() + 1);
            commentProperties.add(new CommentProperty(text, username));

        }
        return commentProperties;
    }

    public Scene getMainMenuScene() {
        return mainMenuScene;
    }

    public void setTableView(String categoryName) throws IOException {
        TableView tableView = new TableView();
        TableColumn<CategoryProperty, String> brand = new TableColumn("Brand");
        brand.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<CategoryProperty, CheckBox> checkBox = new TableColumn<>("");
        checkBox.setCellValueFactory(new PropertyValueFactory<>("checkBox"));
        tableView.getColumns().addAll(brand, checkBox);
        ArrayList<String> subCategories = new ArrayList<>();
        out.writeUTF("get_sub_category " + categoryName);
        out.flush();
        String all = in.readUTF();
        for (String s : all.split("\\s")) {
            subCategories.add(s);
        }
        tableView.setItems(getCategoryProperties(subCategories));
        brand.setPrefWidth(200);
        tableView.setPrefWidth(brand.getWidth() + checkBox.getWidth() - 41);
        tableView.setLayoutX(Main.window.getScene().getWidth() - tableView.getPrefWidth());
        tableView.setLayoutY(125);
        setXLayoutTable(tableView.getLayoutX());
        setYLayoutTable(tableView.getLayoutY() + 440);
        tableView.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, null, null)));
        this.tableView = tableView;
    }

    public void setCommentMenu(VBox second, String productName) throws IOException {
        second.setSpacing(15);
        TableView commentMenu = new TableView();
        TableColumn<CommentProperty, String> commenter = new TableColumn("Account");
        commenter.setCellValueFactory(new PropertyValueFactory<>("commenterUserName"));
        TableColumn<CommentProperty, String> text = new TableColumn<>("Commented");
        text.setCellValueFactory(new PropertyValueFactory<>("comment"));
        commentMenu.getColumns().addAll(commenter, text);
        commentMenu.setItems(getCommentProperties(productName));
        commenter.setPrefWidth(200);
        text.setPrefWidth(800);
        second.getChildren().add(commentMenu);
        second.setLayoutY(777);
        second.setLayoutX(150);
    }

    public void showProductRate(Text rate, String productName) throws IOException {
        out.writeUTF("get_average_rating_product " + productName);
        out.flush();
        rate.setText("Rate : " + in.readUTF());
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
    public void onCategories() throws IOException {
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
                        setTableView(subMenu.getText());
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
                try {
                    clickPrice(true);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    out.writeUTF("disable_sort_each");
                    out.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            secondRoot.getChildren().remove(productRoot);
            try {
                processShowProducts();
            } catch (IOException e) {
                e.printStackTrace();
            }
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
                try {
                    clickPrice(false);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    out.writeUTF("disable_sort_each");
                    out.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            secondRoot.getChildren().remove(productRoot);
            try {
                processShowProducts();
            } catch (IOException e) {
                e.printStackTrace();
            }
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
                try {
                    clickScore(true);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    out.writeUTF("disable_sort_each");
                    out.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            secondRoot.getChildren().remove(productRoot);
            try {
                processShowProducts();
            } catch (IOException e) {
                e.printStackTrace();
            }
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
                try {
                    clickScore(false);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    out.writeUTF("disable_sort_each");
                    out.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            secondRoot.getChildren().remove(productRoot);
            try {
                processShowProducts();
            } catch (IOException e) {
                e.printStackTrace();
            }
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
                try {
                    clickView(true);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    out.writeUTF("disable_sort_each");
                    out.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            secondRoot.getChildren().remove(productRoot);
            try {
                processShowProducts();
            } catch (IOException e) {
                e.printStackTrace();
            }
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
                try {
                    clickView(false);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    out.writeUTF("disable_sort_each");
                    out.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            secondRoot.getChildren().remove(productRoot);
            try {
                processShowProducts();
            } catch (IOException e) {
                e.printStackTrace();
            }
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
                try {
                    clickTime(true);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    out.writeUTF("disable_sort_each");
                    out.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            secondRoot.getChildren().remove(productRoot);
            try {
                processShowProducts();
            } catch (IOException e) {
                e.printStackTrace();
            }
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
                try {
                    clickTime(false);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    out.writeUTF("disable_sort_each");
                    out.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            secondRoot.getChildren().remove(productRoot);
            try {
                processShowProducts();
            } catch (IOException e) {
                e.printStackTrace();
            }
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
        out.writeUTF("get_product_to_show_size");
        out.flush();
        int length = Integer.parseInt(in.readUTF());
        for (int j = 0; j < length; j++) {
            out.writeUTF("get_product_info_from_product_to_show " + j);
            out.flush();
            String response = in.readUTF();
            String nameProduct = response.split("\\s")[0];
            String priceProduct = response.split("\\s")[1];
            String averageRatingProduct = response.split("\\s")[2];
            String explanationProduct = response.substring(nameProduct.length() + 1 + averageRatingProduct.length() + 1 + priceProduct.length() + 1);
//        for (Product subProduct : productsToShow) {
            if (i == 3) {
                yLayout += 350;
                i = 0;
            }
            out.writeUTF("get_product_imageName " + j);
            out.flush();
            String imageNameProduct = in.readUTF();
            ImageView imageView = new ImageView(new Image(new FileInputStream("src/main/resources/media/image/" + imageNameProduct)));
            imageView.setFitWidth(169);
            imageView.setFitHeight(169);
            Text productName = new Text(nameProduct);
            productName.setFont(Font.font(20));
            productName.setOnMouseClicked(mouseEvent -> {
                try {
                    out.writeUTF("product_name_on_action " + productName.getText());
                    out.flush();
                    openProductPage(Main.window.getScene(), productName.getText());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            Text productExplanation = new Text(explanationProduct);
            productExplanation.setFont(Font.font(19));
            Text productPrice = new Text("Price : " + priceProduct + " $");
            productPrice.setFont(Font.font(18));
            Text rating = new Text("Rate : " + averageRatingProduct);
            rating.setFont(Font.font(17));
            Text available = null;
            out.writeUTF("check_product_is_available " + productName);
            out.flush();
            String isAvailable = in.readUTF();
            if (isAvailable.equals("yes_is_available")) {
                available = new Text("Available : Yes");
            } else if (isAvailable.equals("no_not_available")) {
                available = new Text("Available : No");
            }
            available.setFont(Font.font(16));
            VBox vBox = new VBox();
            vBox.setAlignment(Pos.CENTER);
            vBox.setSpacing(7);
            vBox.getChildren().addAll(imageView, productName, productExplanation, productPrice, rating, available);
            out.writeUTF("check_product_is_in_offer " + productName);
            out.flush();
            String isOffer = in.readUTF();
            if (isOffer.equals("yes_is_offer")) {
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
        showProductRate(rate, productName);
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
            out.writeUTF("get_category_attributes_product " + productName + " " + i);
            out.flush();
            stringBuilder.append(in.readUTF() + "\n\n");
        }
        Text categoryAttribute = new Text(stringBuilder.toString());
        categoryAttribute.setTranslateY(20);
        categoryAttribute.setFont(Font.font(19));
        VBox second = new VBox();
        setCommentMenu(second, productName);
        Button comment = new Button("Comment");
        TextField commentField = new TextField("");
        commentField.setTranslateY(-10);
        commentField.setTranslateX(comment.getLayoutX() + 150);
        comment.setFont(Font.font(16));
        comment.setTranslateY(35);
        comment.setOnAction(actionEvent -> {
            try {
                out.writeUTF("comment_action " + productName + " " + commentField.getText());
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            second.getChildren().clear();
            try {
                setCommentMenu(second, productName);
            } catch (IOException e) {
                e.printStackTrace();
            }
            commentField.setText("");
        });

        Button rating = new Button("Rate this product");
        rate.setFont(Font.font(18));
        TextField rateField = new TextField("");
        rateField.setTranslateY(-40);
        rateField.setTranslateX(rating.getLayoutX() + 150);
        rating.setOnAction(actionEvent -> {
            String flag = null;
            try {
                out.writeUTF("rating_action " + productName + " " + rateField.getText());
                out.flush();
                flag = in.readUTF();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (flag.equals("yes_in_if")) {
                try {
                    showProductRate(rate, productName);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                rateField.setText("");
            }

        });
        Button add = new Button("Add to cart");
        add.setDisable(true);
        add.setFont(Font.font(19));
        add.setLayoutY(55);
        add.setOnAction(actionEvent -> {
            try {
                out.writeUTF("add_to_cart_action " + productName);
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        first.getChildren().addAll(name, productExplanation, explanation, categoryLabel, categoryAttribute, comment, commentField, rating, rateField, add);
        first.setLayoutX(666);
        first.setLayoutY(100);
        HBox hBox = new HBox();
        hBox.setSpacing(15);
        ArrayList<CheckBox> allSellerCheckBoxes = new ArrayList<>();
        out.writeUTF("get_product_all_seller " + productName);
        out.flush();
        String all = in.readUTF();
        String[] allSellers = all.split("\\s");
        for (String allSeller : allSellers) {
            out.writeUTF("get_company_name_seller " + allSeller);
            out.flush();
            Text seller = new Text(in.readUTF());
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
                    try {
                        out.writeUTF("checkBox_product_seller " + allSeller);
                        out.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
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
