package view.menu.managerRegion;

import controller.ManagerAccountController;
import exception.CategoryAlreadyExistsException;
import exception.CategoryNotExistsException;
import view.menu.Menu;

import java.util.ArrayList;
import java.util.Collections;
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
        int i = 1;
        for (String category : ManagerAccountController.processManageCategories()) {
            System.out.println(i + "- " + category);
            i++;
        }
    }

    private Menu getEditCategoryMenu() {
        return new Menu("Edit Category Menu", this) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Please enter your category");
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
                    System.out.println("Please enter your field");
                    String field = scanner.nextLine();
                    if (field.equalsIgnoreCase("back"))
                        this.backInExecute();
                    if (!field.matches("(name|attributes)"))
                        this.invalidCommandInExecute();
                    if (field.equalsIgnoreCase("name")) {
                        System.out.println("Please enter category new name");
                        String newName = scanner.nextLine();
                        if (newName.equalsIgnoreCase("back"))
                            this.backInExecute();
                        try {
                            ManagerAccountController.processEditCategoryEachForName(category, newName);
                            System.out.println("edit category successful");
                            this.execute();
                        } catch (CategoryNotExistsException | CategoryAlreadyExistsException editCategoryError) {
                            System.err.println(editCategoryError.getMessage());
                            this.execute();
                        }
                    }
                    int counter = 1;
                    ArrayList<HashMap<String, ArrayList<String>>> attributesForEdit = new ArrayList<>();
                    try {
                        for (String categoryProduct : ManagerAccountController.getCategoryProducts(category)) {
                            HashMap<String, ArrayList<String>> attributesForThisCategory = new HashMap<>();
                            System.out.println(counter + "- Please enter the new attributes for " + categoryProduct);
                            String newAttributes = scanner.nextLine();
                            if (newAttributes.equalsIgnoreCase("back"))
                                this.backInExecute();
                            ArrayList<String> attributes = new ArrayList<>();
                            Collections.addAll(attributes, newAttributes.split(","));
                            attributesForThisCategory.put(category, attributes);
                            attributesForEdit.add(attributesForThisCategory);
                        }
                        ManagerAccountController.processEditCategoryEachForAttributes(category, attributesForEdit);
                        System.out.println("edit category successful");
                        this.execute();
                    } catch (CategoryNotExistsException categoryNotExitsError) {
                        System.err.println(categoryNotExitsError.getMessage());
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
                    System.out.println("Please enter the father category");
                    String fatherCategory = scanner.nextLine();
                    if (fatherCategory.equalsIgnoreCase("back"))
                        this.backInExecute();
                    try {
                        ManagerAccountController.processAddCategoryEach(category, fatherCategory);
                        System.out.println("Add category successful");
                        this.execute();
                    } catch (CategoryNotExistsException addCategoryError) {
                        System.err.println(addCategoryError.getMessage());
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
                        System.err.println(removeCategoryError.getMessage());
                        this.execute();
                    }

                }
            }
        };
    }
}
