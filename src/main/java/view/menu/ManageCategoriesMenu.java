package view.menu;

import controller.LoginPageController;
import controller.ManagerAccountController;
import exception.CategoryNotExistsException;

import java.util.HashMap;

public class ManageCategoriesMenu extends Menu {
    public ManageCategoriesMenu(Menu parent) {
        super("Manage Categories Menu", parent);
        HashMap<Integer, Menu> submenus = new HashMap<>();
        submenus.put(1, getEditCategoryMenu());
        submenus.put(2, getAddCategoryMenu());
        submenus.put(3, getRemoveCategoryMenu());
        this.setSubmenus(submenus);
    }

    @Override
    public void menuWork() {
        ManagerAccountController.processManageCategories();
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
                if (input.equalsIgnoreCase("back"))
                    this.backInExecute();
                else if (!input.matches("edit \\w+"))
                    this.invalidCommandInExecute();
                else {
                    String category = input.substring(5);
                    try {
                        ManagerAccountController.processEditCategoryEach(category);
                        System.out.println("edit category successful");
                        this.execute();
                    } catch (CategoryNotExistsException editCategoryError) {
                        System.out.println(editCategoryError.getMessage());
                        this.execute();
                    }

                }
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
                if (input.equalsIgnoreCase("back"))
                    this.backInExecute();
                else if (!input.matches("add \\w+"))
                    this.invalidCommandInExecute();
                else {
                    String category = input.substring(4);
                    try {
                        ManagerAccountController.processAddCategoryEach(category);
                        System.out.println("Add category successful");
                        this.execute();
                    } catch (CategoryNotExistsException addCategoryError) {
                        System.out.println(addCategoryError.getMessage());
                        this.execute();
                    }

                }
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
                if (input.equalsIgnoreCase("back"))
                    this.backInExecute();
                else if (!input.matches("remove \\w+"))
                    this.invalidCommandInExecute();
                else {
                    String category = input.substring(7);
                    try {
                        ManagerAccountController.processRemoveCategoryEach(category);
                        System.out.println("remove category successful");
                        this.execute();
                    } catch (CategoryNotExistsException removeCategoryError) {
                        System.out.println(removeCategoryError.getMessage());
                        this.execute();
                    }

                }
            }
        };
    }
}
