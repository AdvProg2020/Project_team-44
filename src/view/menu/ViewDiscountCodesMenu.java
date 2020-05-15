package view.menu;

public class ViewDiscountCodesMenu extends Menu {
    public ViewDiscountCodesMenu(Menu parent) {
        super("View Discount Codes Menu", parent);
        submenus.put(1, getViewOfDiscountCodeMenu());
        submenus.put(2, getEditDiscountCodeMenu());
        submenus.put(3, getRemoveDiscountCodeMenu());
    }

    @Override
    public void show() {

    }

    private Menu getViewOfDiscountCodeMenu() {
        return new Menu("View Of Discount Code Menu", this) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Please enter your code:");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
            }
        };
    }

    private Menu getEditDiscountCodeMenu() {
        return new Menu("Edit Discount Code Menu", this) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Please enter your code:");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
            }
        };
    }

    private Menu getRemoveDiscountCodeMenu() {
        return new Menu("Remove Discount Code Menu", this) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Please enter your code:");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
            }
        };
    }
}
