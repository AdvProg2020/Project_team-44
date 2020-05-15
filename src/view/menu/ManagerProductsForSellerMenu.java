package view.menu;

public class ManagerProductsForSellerMenu extends Menu {
    public ManagerProductsForSellerMenu(Menu parent) {
        super("Manager Products For Seller Menu", parent);
        submenus.put(1,getViewProductMenu());
        submenus.put(2,getViewBuyersOfProductMenu());
        submenus.put(3,getEditProductMenu());
    }
    @Override
    public void show(){

    }
    private Menu getViewProductMenu(){
        return new Menu("View Product Menu",this) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Please enter your product Id:");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
            }
        };
    }
    private Menu getViewBuyersOfProductMenu(){
        return new Menu("View Buyers Of Product Menu",this) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Please enter your product Id:");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
            }
        };
    }
    private Menu getEditProductMenu(){
        return new Menu("Edit Product Menu",this) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
                System.out.println("Please enter your product Id:");
            }

            @Override
            public void execute() {
                String input = scanner.nextLine();
            }
        };
    }
}
