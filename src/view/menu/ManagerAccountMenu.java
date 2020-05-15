package view.menu;

public class ManagerAccountMenu extends Menu {
    public ManagerAccountMenu(Menu parent) {
        super("Manager Account Menu", parent);
        submenus.put(1, new ViewPersonalInfoOfManagerMenu(this));
        submenus.put(2, new ManagerUsersMenu(this));
        submenus.put(3, new ManageAllProductsMenu(this));
        submenus.put(4, getCreateDiscountCodeMenu());
        submenus.put(5, new ViewDiscountCodesMenu(this));
        submenus.put(6, new ManageRequestsMenu(this));
        submenus.put(7, new ManageCategoriesMenu(this));

    }

    private Menu getCreateDiscountCodeMenu() {
        return new Menu("Create Discount Code Menu", this) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Please enter the discount code information");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
            }
        };
    }
}
