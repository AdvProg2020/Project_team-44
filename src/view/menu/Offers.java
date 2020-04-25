package view.menu;

public class Offers extends Menu {

    private Menu offs() {

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

    private Offers() {
        super("", null);
    }

    private static Offers singleton = new Offers();

    public static Offers getInstance() {
        return singleton;
    }
}
