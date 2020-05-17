package model.comment;

import model.account.Account;
import model.product.Product;

public class Comment {
    private Account commenter;
    private Product product;
    private String commentText;
    private CommentStatus status;
    private String title;
    private boolean isCommenterEqualsBuyer;

    public Comment(Account commenter, Product product, String commentText, String title) {
        this.commenter = commenter;
        this.product = product;
        this.commentText = commentText;
        this.title = title;
    }

    public void setStatus(CommentStatus status) {
        this.status = status;
    }
}
