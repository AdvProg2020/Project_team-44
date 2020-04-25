package view.menu;

public class MainMenu extends Menu {

    private MainMenu(Menu parent) {
        super("Main Menu", parent);
        //TODO: add subMenus
    }

    private static MainMenu singleton = new MainMenu(null);

    public static MainMenu getInstance() {
        return singleton;
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
