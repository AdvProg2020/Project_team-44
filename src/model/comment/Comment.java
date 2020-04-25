package model.comment;

import model.product.Product;
import model.account.Account;

public class Comment {
    private Account commenter;
    private Product product;
    private String commentText;
    private CommentStatus status;
    private boolean isCommenterEqualsBuyer;

    public Comment(Account commenter, Product product, String commentText) {
        this.commenter = commenter;
        this.product = product;
        this.commentText = commentText;
    }
}
