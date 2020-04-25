package view.menu;

public class ProductsPage extends Menu {
    private Menu viewCategories() {

    }

    private Menu showAllProducts() {

    }

    private Menu showProduct() {

    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void execute() {
        super.execute();
    }

    private ProductsPage() {
        super();
    }

    private static ProductsPage singleton = new ProductsPage();

    public static ProductsPage getInstance() {
        return singleton;
    }

}
