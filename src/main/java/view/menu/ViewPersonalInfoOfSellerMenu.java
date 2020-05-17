package view.menu;

import controller.SellerAccountController;

import java.util.HashMap;

public class ViewPersonalInfoOfSellerMenu extends Menu {
    public ViewPersonalInfoOfSellerMenu(Menu parent) {
        super("View Personal Info Of Seller Menu", parent);
        HashMap<Integer, Menu> submenus = new HashMap<>();
        submenus.put(1, getEditFieldMenu());
        this.setSubmenus(submenus);
    }

    @Override
    public void menuWork() {
        for (String sellerInfo : SellerAccountController.processViewPersonalInfo()) {
            System.out.println(sellerInfo);
        }
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
                if (input.equalsIgnoreCase("back"))
                    this.backInExecute();
                else if (!input.matches("edit-\\w+-\\w+"))
                    this.invalidCommandInExecute();
                else {
                    String field = input.split("-")[1];
                    String newValue = input.split("-")[2];
                    if (field.equals("username")) {
                        System.err.println("you can't edit username");
                        this.execute();
                    } else {
                        try {
                            SellerAccountController.processEditFieldEach(field, newValue);
                            System.out.println("your change done");
                            this.execute();
                        } catch () {

                        }
                    }
                }
            }
        };
    }
}


