package view.menu;

import controller.ManagerAccountController;
import exception.RequestNotExistsException;
import model.account.Account;

import java.util.HashMap;

public class ManageRequestsMenu extends Menu {
    public ManageRequestsMenu(Menu parent, Account account) {
        super("Manager Requests Menu", parent, account);
        HashMap<Integer, Menu> submenus = new HashMap<>();
        submenus.put(1, getDetailsOfRequestMenu());
        submenus.put(2, getAcceptRequestMenu());
        submenus.put(3, getDeclineRequestMenu());
        this.setSubmenus(submenus);
    }

    @Override
    public void menuWork() {
        ManagerAccountController.processManageRequests();
    }

    private Menu getDetailsOfRequestMenu() {
        return new Menu("Details Of Request Menu", this, this.getCurrentUserLoggedIn()) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Please enter your request Id:");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("back"))
                    this.backInExecute();
                else if (input.equalsIgnoreCase("logout") && this.getCurrentUserLoggedIn() != null)
                    this.logoutInExecute();
                else if (!input.matches("details \\w+"))
                    this.invalidCommandInExecute();
                else {
                    String requestId = input.substring(8);
                    try {
                        ManagerAccountController.processShowRequestDetailsEach(requestId);
                    } catch (RequestNotExistsException requestDetailsError) {
                        requestDetailsError.getMessage();
                        this.execute();
                    }

                }
            }
        };
    }

    private Menu getAcceptRequestMenu() {
        return new Menu("Accept Request Menu", this, this.getCurrentUserLoggedIn()) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Please enter your request Id:");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("back"))
                    this.backInExecute();
                else if (input.equalsIgnoreCase("logout") && this.getCurrentUserLoggedIn() != null)
                    this.logoutInExecute();
                else if (!input.matches("accept \\w+"))
                    this.invalidCommandInExecute();
                else {
                    String requestId = input.substring(7);
                    try {
                        ManagerAccountController.processAcceptRequestEach(requestId);
                        System.out.println("Accept request");
                    } catch (RequestNotExistsException requestAcceptError) {
                        requestAcceptError.getMessage();
                        this.execute();
                    }

                }

            }
        };
    }


    private Menu getDeclineRequestMenu() {
        return new Menu("Decline Request Menu", this, this.getCurrentUserLoggedIn()) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Please enter your request Id:");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("back"))
                    this.backInExecute();
                else if (input.equalsIgnoreCase("logout") && this.getCurrentUserLoggedIn() != null)
                    this.logoutInExecute();
                else if (!input.matches("decline \\w+"))
                    this.invalidCommandInExecute();
                else {
                    String requestId = input.substring(8);
                    try {
                        ManagerAccountController.processDeclineRequestEach(requestId);
                        System.out.println("decline request");
                    } catch (RequestNotExistsException requestAcceptError) {
                        requestAcceptError.getMessage();
                        this.execute();
                    }

                }

            }
        };
    }
}