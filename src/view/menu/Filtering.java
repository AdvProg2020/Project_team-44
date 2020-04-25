package view.menu;

public class Filtering extends Menu {
    private static int objectCount = 0;
    private static Filtering instance;
    private static final int MAX_OBJECT_NUM = 2;

    private Filtering() {
        super();
    }

    public static Filtering getInstance() {
        if (objectCount < MAX_OBJECT_NUM) {
            instance = new Filtering();
        }
        return instance;
    }

    private Menu showAvailableFilters() {

    }

    private Menu filter() {

    }

    private Menu currentFilters() {

    }

    private Menu disableFilter() {

    }
}
