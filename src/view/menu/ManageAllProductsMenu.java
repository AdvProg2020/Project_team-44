package view.menu;

public class ManageAllProductsMenu extends Menu {
    public ManageAllProductsMenu(Menu parent) {
        super("Manage All Products Menu", parent);
        submenus.put(1, getRemoveProductMenu());
    }

    private Menu getRemoveProductMenu() {
        return new Menu("Remove Product Menu", this) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Please enter the product Id:");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
            }
        };
    }
}
