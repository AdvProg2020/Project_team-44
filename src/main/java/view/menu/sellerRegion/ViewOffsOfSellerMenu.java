package view.menu.sellerRegion;

import controller.SellerAccountController;
import exception.OfferFieldNotExistsException;
import exception.ProductIdNotExistsException;
import exception.SellerNotOwnsProductException;
import exception.TimeExpiresException;
import view.menu.Menu;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

public class ViewOffsOfSellerMenu extends Menu {
    public ViewOffsOfSellerMenu(Menu parent) {
        super("View Offs Of Seller Menu", parent);
        HashMap<Integer, Menu> submenus = new HashMap<>();
        submenus.put(1, getViewOffMenu());
        submenus.put(2, getEditOffMenu());
        submenus.put(3, getAddOffMenu());
        this.setSubmenus(submenus);
    }

    @Override
    public void menuWork() {
        int i = 1;
        for (String viewOff : SellerAccountController.processViewOffs()) {
            System.out.println(i + "- " + viewOff);
            i++;
        }
    }

    private Menu getViewOffMenu() {
        return new Menu("View Off Menu", this) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Please enter your offId:");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("back"))
                    this.backInExecute();
                else if (!input.matches("view \\w+"))
                    this.invalidCommandInExecute();
                else {
                    String offId = input.substring(5);
                    try {
                        for (String viewOffEach : SellerAccountController.processViewOffEach(offId)) {
                            System.out.println(viewOffEach);
                        }
                        this.execute();
                    } catch (ProductIdNotExistsException viewOffError) {
                        System.err.println(viewOffError.getMessage());
                        this.execute();
                    }

                }
            }
        };
    }

    private Menu getEditOffMenu() {
        return new Menu("Edit Off Menu", this) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Please enter your offId:");
            }

            @Override
            public void execute() {
                String offId = scanner.nextLine();
                if (offId.equalsIgnoreCase("back"))
                    this.backInExecute();
                System.out.println("Please enter your field");
                String field = scanner.nextLine();
                if (field.equalsIgnoreCase("back"))
                    this.backInExecute();
                System.out.println("Please enter your old value");
                System.out.println("Please enter your new value");
                ArrayList<String> newValue = new ArrayList<>();
                while (true) {
                    String input = scanner.nextLine();
                    if (input.equalsIgnoreCase("back"))
                        this.backInExecute();
                    else if (input.equalsIgnoreCase("end"))
                        break;
                    else
                        newValue.add(input);
                }
                try {
                    SellerAccountController.processEditOffEach(offId, field, newValue);
                    System.out.println("edit off successful");
                    this.backInExecute();
                } catch (ProductIdNotExistsException | OfferFieldNotExistsException EditOffError) {
                    System.err.println(EditOffError.getMessage());
                    this.execute();
                }
            }
        };
    }

    private Menu getAddOffMenu() {
        return new Menu("Add Off Menu", this) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Please enter your new off information :");
            }

            @Override
            public void execute() {
                System.out.println("Please enter your productsId's that you want use this off");
                ArrayList<String> productIds = new ArrayList<>();
                while (true) {
                    String productId = scanner.nextLine();
                    if (productId.equalsIgnoreCase("back"))
                        this.backInExecute();
                    else if (productId.equalsIgnoreCase("end"))
                        break;
                    else
                        productIds.add(productId);
                }
                System.out.println("Please enter your initial date");
                String initialDate = scanner.nextLine();
                if (initialDate.equalsIgnoreCase("back"))
                    this.backInExecute();
                if (!initialDate.matches("^([0-2][0-9]|(3)[0-1])(\\/)(((0)[0-9])|((1)[0-2]))(\\/)(\\d){4}$"))
                    this.invalidCommandInExecute();
                System.out.println("Please enter your final date");
                String finalDate = scanner.nextLine();
                if (finalDate.equalsIgnoreCase("back"))
                    this.backInExecute();
                if (!finalDate.matches("^([0-2][0-9]|(3)[0-1])(\\/)(((0)[0-9])|((1)[0-2]))(\\/)(\\d){4}$"))
                    this.invalidCommandInExecute();
                String discountPercentage = scanner.nextLine();
                if (discountPercentage.equalsIgnoreCase("back"))
                    this.backInExecute();
                if (!discountPercentage.matches("[0-9][0-9]"))
                    this.invalidCommandInExecute();
                int percent = Integer.parseInt(discountPercentage);
                try {
                    SellerAccountController.processAddOffEach(productIds, initialDate, finalDate, percent);
                    System.out.println("your request for add this off sent to manger");
                    this.backInExecute();

                } catch (ParseException | SellerNotOwnsProductException | TimeExpiresException addOffError) {
                    System.err.println(addOffError.getMessage());
                    this.execute();
                }
            }
        };
    }
}
