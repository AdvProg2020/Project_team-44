package view.menu;

import controller.ManagerAccountController;
import model.account.Account;

import java.util.HashMap;

public class ViewPersonalInfoOfManagerMenu extends Menu {
    public ViewPersonalInfoOfManagerMenu(Menu parent, Account account) {
        super("View Personal Info Of Manager Menu", parent, account);
        HashMap<Integer, Menu> submenus = new HashMap<>();
        submenus.put(1, getEditFieldMenu());
        this.setSubmenus(submenus);

    }

    @Override
    public void menuWork() {
        ManagerAccountController.processViewPersonalInfo();
    }

    private Menu getEditFieldMenu() {
        return new Menu("Edit Field Menu", this, this.getCurrentUserLoggedIn()) {

            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Please enter your field :");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
                if(input.equalsIgnoreCase("back"))
                    this.backInExecute();
                else if(input.equalsIgnoreCase("logout") && getCurrentUserLoggedIn()!= null)
                    this.logoutInExecute();
                else if (!input.matches("edit-\\w+-\\w+"))
                     this.invalidCommandInExecute();
                 else {
                    String field = input.split("-")[1];
                    String newValue = input.split("-")[2];
                    if (field.equals("username")) {
                        System.err.println("you can't edit username");
                        this.execute();
                    } else
                        try{
                            ManagerAccountController.processEditFieldEach(field,newValue);
                            System.out.println("your change done");
                        }
                        catch (){

                        }

                }
            }
        };
    }
}
