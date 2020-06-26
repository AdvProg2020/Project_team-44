package model.comment;

import model.product.Product;
import model.account.Account;

public class Comment {
    private Account commenter;
    private transient Product product;
    private String commentText;
    private CommentStatus status = CommentStatus.IN_PROGRESS;
    private String title;
    private boolean isCommenterEqualsBuyer;

    public Comment(Account commenter, Product product, String commentText, String title) {
        this.commenter = commenter;
        this.product = product;
        this.commentText = commentText;
        this.title = title;
        this.product.getAllComments().add(this);
    }

    public String getCommentText() {
        return commentText;
    }

    public Account getCommenter() {
        return commenter;
    }

    public void setStatus(CommentStatus status) {
        this.status = status;
    }
}
