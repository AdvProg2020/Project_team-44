package graphicView.productMenu;

import controller.ProductsPageController;
import exception.CategoryNotExistsException;
import exception.FilterNotExistsException;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import main.Main;
import model.Category;
import model.product.Product;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import static controller.ProductsPageController.processFilter;

public class ProductsMenu {
    public TextField minPriceId;
    public TextField maxPriceId;
    public Menu id;
    public Pane root;
    private TableView<CategoryProperty> tableView;
    private ArrayList<Product> productsToShow = new ArrayList<>();
    private static ArrayList<CategoryProperty> allCategoryProperty = new ArrayList<>();
    private Scene previousScene;
    private ArrayList<Product> sorted = ProductsPageController.getAllFilteredProducts();
    private boolean isCalledFromOnCategories = false;
    @FXML
    public MenuButton productsMenuButton;
    public MenuButton categoriesMenuButton;
    public MenuButton filterMenuButton;
    public CheckMenuItem timeItem;
    public CheckMenuItem scoreItem;
    public CheckMenuItem viewItem;
    public CheckMenuItem priceItem;

    private ObservableList<CategoryProperty> getCategoryProperties(ArrayList<Category> subCategories) {
        ObservableList<CategoryProperty> categoryProperties = FXCollections.observableArrayList();
        for (Category subCategory : subCategories) {
            categoryProperties.add(new CategoryProperty(subCategory));
        }
        return categoryProperties;
    }

    public boolean isCalledFromOnCategories() {
        return isCalledFromOnCategories;
    }

    public void setCalledFromOnCategories(boolean calledFromOnCategories) {
        isCalledFromOnCategories = calledFromOnCategories;
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
        tableView.setLayoutX(1200 - tableView.getWidth() - 240);
        tableView.setLayoutY(125);
        this.tableView = tableView;
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
    public void onGoods(Event event) {
        productsMenuButton.getItems().clear();
        for (String processShowProduct : ProductsPageController.processShowProducts()) {
            productsMenuButton.getItems().add(new MenuItem(processShowProduct));
        }
    }

    @FXML
    public void onCategories(Event event) {
        categoriesMenuButton.getItems().clear();
        for (Category category : Category.getAllParents()) {
            Menu menu = new Menu(category.getName());
            menu.setOnAction(actionEvent -> {
                try {
                    setTableView(Category.getCategoryByName(menu.getText()));
                    setCalledFromOnCategories(true);
                    for (Category subCategory : Category.getCategoryByName(menu.getText()).getSubCategories()) {
                        productsToShow.addAll(subCategory.getAllSubProducts());
                    }
                    openTheSecondaryCategory(Main.window.getScene());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            });
            categoriesMenuButton.getItems().add(menu);
            for (Category subCategory : category.getSubCategories()) {
                Menu subMenu = new Menu(subCategory.getName());
                subMenu.setOnAction(actionEvent -> {
                    try {
                        setTableView(Category.getCategoryByName(subMenu.getText()));
                        setCalledFromOnCategories(true);
                        for (Category subCategory1 : Category.getCategoryByName(subMenu.getText()).getSubCategories()) {
                            productsToShow.addAll(subCategory1.getAllSubProducts());
                        }
                        openTheSecondaryCategory(Main.window.getScene());
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                });
                menu.getItems().add(subMenu);
            }
        }
    }

    public void openTheSecondaryCategory(Scene previousScene) throws FileNotFoundException {
        Pane root = new Pane();
        Button button = new Button("Back");
        button.setFont(Font.font(20));
        if (isCalledFromOnCategories()) {
            setPreviousScene(previousScene);
            setCalledFromOnCategories(false);
        }
        button.setOnAction(actionEvent -> Main.window.setScene(getPreviousScene()));
        root.getChildren().add(button);
        root.getChildren().add(getTableView());
        int i = 0;
        int yLayout = 200;
        int xLayout = 100;
        for (Product subProduct : productsToShow) {
            if (i == 3) {
                yLayout += 300;
                i = 0;
            }
            ImageView imageView = new ImageView(new Image(new FileInputStream("src/main/resources/media/image/" + subProduct.getImageName())));
            imageView.setFitWidth(169);
            imageView.setFitHeight(169);
            Text productName = new Text(subProduct.getName());
            productName.setFont(Font.font(20));
            Text productExplanation = new Text(subProduct.getExplanationText());
            productExplanation.setFont(Font.font(20));
            Text productPrice = new Text(String.valueOf(subProduct.getPrice()));
            productPrice.setFont(Font.font(20));
            VBox vBox = new VBox();
            vBox.setAlignment(Pos.CENTER);
            vBox.setSpacing(7);
            vBox.getChildren().addAll(imageView, productName, productExplanation, productPrice);
            vBox.setLayoutX(xLayout + i * 300);
            vBox.setLayoutY(yLayout);
            root.getChildren().add(vBox);
            i++;
        }
        Label minPrice = new Label("Min Price");
        TextField minPriceField = new TextField("");
        minPrice.setFont(Font.font(20));
        minPriceField.setFont(Font.font(20));
        minPrice.setAlignment(Pos.TOP_CENTER);
        ScrollPane scrollPane = new ScrollPane(root);
        Main.window.setScene(new Scene(scrollPane, 1200, 720));
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
            checkBox.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    if (checkBox.isSelected()) {
                        try {
                            productsToShow.clear();
                            for (CategoryProperty categoryProperty : allCategoryProperty) {
                                if (categoryProperty.getCheckBox().isSelected()) {
                                    productsToShow.addAll(categoryProperty.getCategory().getAllSubProducts());
                                }
                            }
                            openTheSecondaryCategory(Main.window.getScene());
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    } else {
                        try {
                            productsToShow.removeAll(category.getAllSubProducts());
                            if (productsToShow.size() == 0) {
                                for (CategoryProperty categoryProperty : allCategoryProperty) {
                                    productsToShow.addAll(categoryProperty.getCategory().getAllSubProducts());
                                }
                            }
                            openTheSecondaryCategory(Main.window.getScene());
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
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
        sorted = ProductsPageController.getAllFilteredProducts();
        onGoods(actionEvent);
    }

    public void clickScore(ActionEvent actionEvent) {
        timeItem.setSelected(false);
        viewItem.setSelected(false);
        priceItem.setSelected(false);
        if (scoreItem.isSelected()) {
            ProductsPageController.processSortByScore(true);
            sorted = ProductsPageController.getAllFilteredProducts();
        } else ProductsPageController.processDisableSortEach();
        onGoods(actionEvent);
    }

    public void clickView(ActionEvent actionEvent) {
        scoreItem.setSelected(false);
        timeItem.setSelected(false);
        priceItem.setSelected(false);
        if (viewItem.isSelected()) {
            ProductsPageController.processSortByView(true);
            sorted = ProductsPageController.getAllFilteredProducts();
        } else ProductsPageController.processDisableSortEach();
    }

    public void clickPrice(ActionEvent actionEvent) {
        scoreItem.setSelected(false);
        timeItem.setSelected(false);
        viewItem.setSelected(false);
        if (priceItem.isSelected()) {
            ProductsPageController.processSortByPrice(true);
            sorted = ProductsPageController.getAllFilteredProducts();
        } else ProductsPageController.processDisableSortEach();
        onGoods(actionEvent);
    }

    public void minAction(ActionEvent actionEvent) {

    }

    public void maxAction(ActionEvent actionEvent) {
    }

    public void productNameAction(ActionEvent actionEvent) {
//        try {
//            ProductsPageController.processFilter("BY_NAME", productNameId.getText());
//            sorted = ProductsPageController.getAllFilteredProducts();
//            onGoods(actionEvent);
//        } catch (CategoryNotExistsException e) {
//            e.printStackTrace();
//        } catch (FilterNotExistsException e) {
//            e.printStackTrace();
//        }
    }

    public void priceAction(ActionEvent actionEvent) throws CategoryNotExistsException, FilterNotExistsException {
        if (!minPriceId.getText().isBlank() && maxPriceId.getText().isBlank()) {
            processFilter("BY_PRICE", Integer.parseInt(minPriceId.getText()) + "," + Integer.MAX_VALUE);
        } else if (!maxPriceId.getText().isBlank() && minPriceId.getText().isBlank()) {
            processFilter("BY_PRICE", 0 + "," + Integer.parseInt(maxPriceId.getText()));
        } else if (!minPriceId.getText().isBlank() && !maxPriceId.getText().isBlank()) {
            processFilter("BY_PRICE", Integer.parseInt(minPriceId.getText()) + "," + Integer.parseInt(maxPriceId.getText()));
        } else processFilter("BY_PRICE", -1 + "," + (-1));
    }
}
