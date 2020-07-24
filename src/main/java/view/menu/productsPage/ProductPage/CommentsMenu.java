package view.menu.productsPage.ProductPage;

import controller.ProductPageController;
import view.menu.Menu;

import java.util.HashMap;

public class CommentsMenu extends Menu {
    public CommentsMenu(Menu parent) {
        super("Comments Menu", parent);
        HashMap<Integer, Menu> submenus = new HashMap<>();
        submenus.put(1, getAddCommentMenu());
        this.setSubmenus(submenus);
    }

    @Override
    public void menuWork() {

    }

    private Menu getAddCommentMenu() {
        return new Menu("Add Comment Menu", this) {
            @Override
            public void show() {
                System.out.println(this.getName() + ":");
            }

            @Override
            public void execute() {
                System.out.println("Please enter your title");
                String title = scanner.nextLine();
                if (title.equalsIgnoreCase("back"))
                    this.backInExecute();
                System.out.println("Please enter your content");
                String content = scanner.nextLine();
                if (content.equalsIgnoreCase("back"))
                    this.backInExecute();
                ProductPageController.processAddComment(title, content);
                System.out.println("Your comment add");
                this.backInExecute();
            }
        };
    }
}
