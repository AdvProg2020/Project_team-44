package view.menu;

public class Sorting extends Menu {

    private static int objectCount = 0;
    private static Sorting instance;
    private static final int MAX_OBJECT_NUM = 2;

    private Sorting() {
        super();
    }

    public static Sorting getInstance() {
        if (objectCount < MAX_OBJECT_NUM) {
            instance = new Sorting();
        }
        return instance;
    }

    private Menu showAvailableSorts() {

    }

    private Menu sort() {

    }

    private Menu currentSort() {

    }

    private Menu disableSort() {

    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void execute() {
        super.execute();
    }
}
