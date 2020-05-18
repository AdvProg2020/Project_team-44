package view.menu;

import controller.PurchaserAccountController;
import exception.ProductNotExistsInCartException;

import java.util.HashMap;

public class ViewCartMenu extends Menu {
    public ViewCartMenu(Menu parent) {
        super("View Cart Menu", parent);
        HashMap<Integer, Menu> submenus = new HashMap<>();
        submenus.put(1, getShowProductsMenu());
        submenus.put(2, getViewProductMenu());
        submenus.put(3, getIncreaseProductMenu());
        submenus.put(4,getDecreaseProductMenu());
        submenus.put(5, getShowTotalPriceMenu());
        submenus.put(6, new PurchaseMenu(this));
        this.setSubmenus(submenus);
    }

    @Override
    public void menuWork() {
        PurchaserAccountController.processViewCart();
    }

    private Menu getShowProductsMenu() {
        return new Menu("Show Products Menu", this) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
                if (input.equals("show products")) {
                    int i=1;
                    for (String showProductsEach : PurchaserAccountController.processShowProductsEach()) {
                        System.out.println(i+"- "+showProductsEach);
                        i++;
                    }
                    this.execute();
                } else if (input.equalsIgnoreCase("back"))
                    this.backInExecute();
                else
                    invalidCommandInExecute();
            }
        };
    }

    private Menu getViewProductMenu() {
        return new Menu("View Product Menu", this) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Please enter product Id :");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("back"))
                    this.backInExecute();
                else if (!input.matches("view \\w+"))
                    this.invalidCommandInExecute();
                else {
                    String productId = input.substring(5);
                    try {
                        PurchaserAccountController.processViewProductsEach(productId);
                        this.execute();
                    } catch (ProductNotExistsInCartException viewProductError) {
                        System.err.println(viewProductError.getMessage());
                        this.execute();
                    }
                }
            }
        };
    }

    private Menu getIncreaseProductMenu() {
        return new Menu("Increase Product Menu", this) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Please enter product Id :");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("back"))
                    this.backInExecute();
                else if (!input.matches("increase \\w+"))
                    this.invalidCommandInExecute();
                else {
                    String productId = input.substring(9);
                    try {
                        PurchaserAccountController.processIncreaseProductEach(productId);
                        System.out.println("increase successful");
                        this.execute();
                    } catch (ProductNotExistsInCartException increaseProductError) {
                        System.err.println(increaseProductError.getMessage());
                        this.execute();
                    }
                }
            }
        };
    }

    private Menu getDecreaseProductMenu() {
        return new Menu("Decrease Product Menu", this) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Please enter product Id :");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("back"))
                    this.backInExecute();
                else if (!input.matches("decrease \\w+"))
                    this.invalidCommandInExecute();
                else {
                    String productId = input.substring(9);
                    try {
                        PurchaserAccountController.processDecreaseProductEach(productId);
                        System.out.println("decrease successful");
                        this.execute();
                    } catch (ProductNotExistsInCartException decreaseProductError) {
                        System.err.println(decreaseProductError.getMessage());
                        this.execute();
                    }
                }
            }
        };
    }

    private Menu getShowTotalPriceMenu() {
        return new Menu("Show Total Price Menu", this) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("back"))
                    this.backInExecute();
                else
                    this.invalidCommandInExecute();
            }

            @Override
            public void menuWork() {
                System.out.println("Total price :"+PurchaserAccountController.processShowTotalPriceEach());
            }
        };
    }

}
