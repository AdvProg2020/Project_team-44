package view.menu.purchaseRegion;

import controller.PurchaserAccountController;
import exception.CodedDiscountExpiresException;
import exception.NotEnoughMoneyToPayException;
import exception.PurchaserNotOwnsCodedDiscountException;
import view.menu.Menu;

import java.util.HashMap;

public class PurchaseMenu extends Menu {
    public PurchaseMenu(Menu parent) {
        super("Purchase Menu", parent);
        HashMap<Integer, Menu> submenus = new HashMap<>();
        submenus.put(1, getReceiverInformationMenu(this));
        this.setSubmenus(submenus);
    }

    private Menu getReceiverInformationMenu(final Menu currentMenu) {
        return new Menu("Receiver Information Menu", this) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Please enter your address and phoneNumber:");
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
                return new Menu("Discount Code Menu", this) {
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
                            } catch (PurchaserNotOwnsCodedDiscountException | CodedDiscountExpiresException discountCodeError) {
                                System.err.println(discountCodeError.getMessage());
                                this.execute();
                            }
                        }

                    }

                    private Menu getPaymentMenu() {
                        return new Menu("Payment Menu", this) {
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
                                } else {
                                    try {
                                        PurchaserAccountController.processPayment();
                                        System.out.println("payment successful");
                                        currentMenu.show();
                                        currentMenu.execute();
                                    } catch (NotEnoughMoneyToPayException paymentError) {
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
