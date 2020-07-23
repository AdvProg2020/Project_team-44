package server.model.comment;

import com.google.gson.Gson;
import server.model.product.Product;
import server.model.account.Account;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Random;

public class Comment {
    private Account commenter;
    private transient Product product;
    private String commentText;
    private CommentStatus status = CommentStatus.IN_PROGRESS;
    private String title;
    private boolean isCommenterEqualsBuyer;
    public static ArrayList<Comment> allComments = new ArrayList<>();
    private String commentId;

    public Comment(Account commenter, Product product, String commentText, String title) {
        this.commenter = commenter;
        this.product = product;
        this.commentText = commentText;
        this.title = title;
        this.product.getAllComments().add(this);
        this.commentId = produceCommentId();
        allComments.add(this);
        createAndUpdateJson(this);
    }

    public String produceCommentId() {
        String logId = "Comment_";
        Random random = new Random();
        int min = 10;
        int max = 100000000;
        int range = max - min;
        int rand = random.nextInt(range) + min;
        logId += rand;
        return logId;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getCommentId() {
        return commentId;
    }

    public static void setAllComments(ArrayList<Comment> allComments) {
        Comment.allComments = allComments;
    }

    public void createAndUpdateJson(Comment comment) {
        try {
            Writer writer = new FileWriter("src/server.main/resources/Comments/" + this.getCommentId() + ".json");
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

    @Override
    public String toString() {
        return "Comment{" +
                "commenter=" + commenter +
                ", product=" + product +
                ", commentText='" + commentText + '\'' +
                ", status=" + status +
                ", title='" + title + '\'' +
                ", isCommenterEqualsBuyer=" + isCommenterEqualsBuyer +
                '}';
    }
}
