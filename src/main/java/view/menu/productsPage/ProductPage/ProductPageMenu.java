package view.menu.productsPage.ProductPage;

import controller.ProductPageController;
import exception.ProductAlreadyExistsInCartException;
import exception.ProductIdNotExistsException;
import exception.SellerUserNameNotExists;
import view.menu.Menu;

import java.util.HashMap;

public class ProductPageMenu extends Menu {
    public ProductPageMenu(Menu parent) {
        super("Product Page Menu", parent);
        HashMap<Integer, Menu> submenus = new HashMap<>();
        submenus.put(1, getDigestMenu());
        submenus.put(2, getAddToCartMenu());
        submenus.put(3, getSelectSellerMenu());
        submenus.put(4, getAttributesMenu());
        submenus.put(5, getCompareProductMenu());
        submenus.put(6, new CommentsMenu(this));
        this.setSubmenus(submenus);
    }

    private Menu getDigestMenu() {
        return new Menu("Digest Menu", this) {
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
                for (String showDigest : ProductPageController.processShowDigest()) {
                    System.out.println(showDigest);
                }
            }
        };
    }

    private Menu getAddToCartMenu() {
        return new Menu("Add To Cart Menu", this) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Are you sure that?");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("back") || input.equalsIgnoreCase("no"))
                    backInExecute();
                else if (input.equalsIgnoreCase("yes")) {
                    try {
                        ProductPageController.processAddProductToCartEach();
                        System.out.println("This product add to your cart");
                        this.backInExecute();
                    } catch (ProductAlreadyExistsInCartException addProductError) {
                        System.err.println(addProductError.getMessage());
                        this.backInExecute();
                    }
                } else
                    this.invalidCommandInExecute();
            }
        };
    }

    private Menu getSelectSellerMenu() {
        return new Menu("Select Seller Menu", this) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Please enter the seller_username");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("back"))
                    this.backInExecute();
                else if (!input.matches("select seller \\w+"))
                    this.invalidCommandInExecute();
                else {
                    String userName = input.substring(14);
                    try {
                        ProductPageController.processSelectSellerEach(userName);
                        System.out.println("select successful");
                        this.execute();
                    } catch (SellerUserNameNotExists selectSellerError) {
                        System.err.println(selectSellerError.getMessage());
                        this.execute();
                    }

                }

            }
        };
    }

    private Menu getAttributesMenu() {
        return new Menu("Attributes Menu", this) {
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
                ProductPageController.processShowAttributes();
            }
        };
    }

    private Menu getCompareProductMenu() {
        return new Menu("Compare Product Menu", this) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Please enter your productId :");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("back"))
                    this.backInExecute();
                else if (!input.matches("compare \\w+"))
                    this.invalidCommandInExecute();
                else {
                    String productId = input.substring(8);
                    try {
                        for (String compare : ProductPageController.processCompare(productId)) {
                            System.out.println(compare);
                        }
                        this.execute();
                    } catch (ProductIdNotExistsException compareProductError) {
                        System.err.println(compareProductError.getMessage());
                        this.execute();
                    }

                }
            }
        };
    }
}
