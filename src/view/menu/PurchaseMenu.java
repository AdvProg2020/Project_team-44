package view.menu;

import controller.ManagerAccountController;
import model.account.Account;

import java.util.HashMap;

public class PurchaseMenu extends Menu {
    public PurchaseMenu(Menu parent, Account account) {
        super("Purchase Menu", parent, account);
        HashMap<Integer , Menu> submenus = new HashMap<>();
        submenus.put(1,getReceiverInformationMenu());
        this.setSubmenus(submenus);
    }
    private Menu getReceiverInformationMenu(){
        return new Menu("Receiver Information Menu",this,this.getCurrentUserLoggedIn()) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Please enter your information");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
                if(input.equalsIgnoreCase("back"))
                    this.backInExecute();
                else if(input.equalsIgnoreCase("logout") && getCurrentUserLoggedIn()!= null)
                    this.logoutInExecute();
                else if (!input.matches(""))
                    this.invalidCommandInExecute();
                else {


                }
            }
        };
    }
}
