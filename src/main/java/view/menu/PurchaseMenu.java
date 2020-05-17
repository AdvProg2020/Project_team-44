package view.menu;

import controller.ManagerAccountController;
import controller.ProductsPageController;
import controller.PurchaserAccountController;
import exception.NotEnoughMoneyToPayException;
import exception.PurchaserNotOwnsCodedDiscountException;
import model.account.Account;

import java.util.HashMap;

public class PurchaseMenu extends Menu {
    public PurchaseMenu(Menu parent, Account account) {
        super("Purchase Menu", parent, account);
        HashMap<Integer, Menu> submenus = new HashMap<>();
        submenus.put(1, getReceiverInformationMenu(this));
        this.setSubmenus(submenus);
    }

    private Menu getReceiverInformationMenu(Menu currentMenu) {
        return new Menu("Receiver Information Menu", this, this.getCurrentUserLoggedIn()) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Please enter your phoneNumber And address:");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("back"))
                    this.backInExecute();
                else if (!input.matches("\\w+_\\w+"))
                    this.invalidCommandInExecute();
                else {
                    String address = input.split("_")[0];
                    String phoneNumber = input.split("_")[1];
                    PurchaserAccountController.receiveInfo(address, phoneNumber);
                    getDiscountCodeMenu().show();
                    getDiscountCodeMenu().execute();

                }
            }

            private Menu getDiscountCodeMenu() {
                return new Menu("Discount Code Menu", this, this.getCurrentUserLoggedIn()) {
                    @Override
                    public void show() {
                        System.out.println(this.getName() + ":");
                        System.out.println("Please enter your discount code:");
                    }

                    @Override
                    public void execute() {
                        String discountCodeInput = scanner.nextLine();
                        if (discountCodeInput.equalsIgnoreCase("back"))
                            this.backInExecute();
                        else if (!discountCodeInput.matches("\\w+"))
                            this.invalidCommandInExecute();
                        else {
                            try {
                                PurchaserAccountController.getCodedDiscount(discountCodeInput);
                                getPaymentMenu().show();
                                getPaymentMenu().execute();
                            } catch (PurchaserNotOwnsCodedDiscountException discountCodeError) {
                                System.out.println(discountCodeError.getMessage());
                                this.execute();
                            }
                        }

                    }

                    private Menu getPaymentMenu() {
                        return new Menu("Payment Menu", this, this.getCurrentUserLoggedIn()) {
                            @Override
                            public void show() {
                                System.out.println(this.getName() + ":");
                                System.out.println("Are you sure that?");
                            }

                            @Override
                            public void execute() {
                                String input1 = scanner.nextLine();
                                if (input1.equalsIgnoreCase("back"))
                                    this.backInExecute();
                                else if (input1.equalsIgnoreCase("no")) {
                                    currentMenu.show();
                                    currentMenu.execute();
                                }
                                else{
                                    try {
                                        PurchaserAccountController.processPayment();
                                        System.out.println("payment successful");
                                        currentMenu.show();
                                        currentMenu.execute();
                                    }
                                    catch (NotEnoughMoneyToPayException paymentError){
                                        System.err.println(paymentError.getMessage());
                                        currentMenu.show();
                                        currentMenu.execute();
                                    }
                                }
                            }
                        };
                    }
                };
            }
        };
    }
}
