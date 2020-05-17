package view.menu;

import controller.LoginPageController;
import controller.ManagerAccountController;
import exception.RequestNotExistsException;

import java.util.HashMap;

public class ManageRequestsMenu extends Menu {
    public ManageRequestsMenu(Menu parent) {
        super("Manager Requests Menu", parent);
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
        return new Menu("Details Of Request Menu", this) {
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
                else if (!input.matches("details \\w+"))
                    this.invalidCommandInExecute();
                else {
                    String requestId = input.substring(8);
                    try {
                        ManagerAccountController.processShowRequestDetailsEach(requestId);
                        this.execute();
                    } catch (RequestNotExistsException requestDetailsError) {
                        System.out.println(requestDetailsError.getMessage());
                        this.execute();
                    }

                }
            }
        };
    }

    private Menu getAcceptRequestMenu() {
        return new Menu("Accept Request Menu", this) {
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
                else if (!input.matches("accept \\w+"))
                    this.invalidCommandInExecute();
                else {
                    String requestId = input.substring(7);
                    try {
                        ManagerAccountController.processAcceptRequestEach(requestId);
                        System.out.println("Accept request");
                        this.execute();
                    } catch (RequestNotExistsException requestAcceptError) {
                        System.out.println(requestAcceptError.getMessage());
                        this.execute();
                    }

                }

            }
        };
    }


    private Menu getDeclineRequestMenu() {
        return new Menu("Decline Request Menu", this) {
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
                else if (!input.matches("decline \\w+"))
                    this.invalidCommandInExecute();
                else {
                    String requestId = input.substring(8);
                    try {
                        ManagerAccountController.processDeclineRequestEach(requestId);
                        System.out.println("decline request");
                        this.execute();
                    } catch (RequestNotExistsException requestAcceptError) {
                        System.out.println(requestAcceptError.getMessage());
                        this.execute();
                    }

                }

            }
        };
    }
}