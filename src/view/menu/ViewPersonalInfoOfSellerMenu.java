package view.menu;

public class ViewPersonalInfoOfSellerMenu extends Menu {
    public ViewPersonalInfoOfSellerMenu(Menu parent) {
        super("View Personal Info Of Seller Menu", parent);
        submenus.put(1, getEditFieldMenu());
    }

    @Override
    public void show() {

    }

    private Menu getEditFieldMenu() {
        return new Menu("Edit Field Menu", this) {

            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Please enter your field :");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();

            }
        };
    }
}
