package model.comment;

import com.google.gson.Gson;
import model.product.Product;
import model.account.Account;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

public class Comment {
    private Account commenter;
    private transient Product product;
    private String commentText;
    private CommentStatus status = CommentStatus.IN_PROGRESS;
    private String title;
    private boolean isCommenterEqualsBuyer;
    public static ArrayList<Comment> allComments = new ArrayList<>();

    public Comment(Account commenter, Product product, String commentText, String title) {
        this.commenter = commenter;
        this.product = product;
        this.commentText = commentText;
        this.title = title;
        this.product.getAllComments().add(this);
        allComments.add(this);
        createAndUpdateJson(this);
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public static void setAllComments(ArrayList<Comment> allComments) {
        Comment.allComments = allComments;
    }

    public void createAndUpdateJson(Comment comment) {
        try {
            Writer writer = new FileWriter("src/main/resources/Comments/" + comment.getTitle() + ".json");
            new Gson().toJson(comment, writer);
            writer.close();
            comment.getProduct().createAndUpdateJson(comment.getProduct());
        } catch (IOException e) {
            System.out.println(e.getMessage() + "!!!!!!!!!!!!!!");
        }
    }

    public Product getProduct() {
        return product;
    }

    public String getCommentText() {
        return commentText;
    }

    public Account getCommenter() {
        return commenter;
    }

    public String getTitle() {
        return title;
    }

    public void setStatus(CommentStatus status) {
        this.status = status;
    }
}
