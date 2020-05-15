package view.menu;

public class ManageCategoriesMenu extends Menu {
    public ManageCategoriesMenu(Menu parent) {
        super("Manage Categories Menu", parent);
        submenus.put(1, getEditCategoryMenu());
        submenus.put(2, getAddCategoryMenu());
        submenus.put(3, getRemoveCategoryMenu());
    }

    @Override
    public void show() {

    }

    private Menu getEditCategoryMenu() {
        return new Menu("Edit Category Menu", this) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Please enter your category:");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
            }
        };
    }

    private Menu getAddCategoryMenu() {
        return new Menu("Add Category Menu", this) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Please enter your category:");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
            }
        };
    }

    private Menu getRemoveCategoryMenu() {
        return new Menu("Remove Category Menu", this) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Please enter your category:");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
            }
        };
    }
}
