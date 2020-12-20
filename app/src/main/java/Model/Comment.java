package Model;

import java.io.Serializable;
import java.util.List;

public class Comment implements Serializable {
    private List<Comments> comments;

    public List<Comments> getComments() {
        return comments;
    }

    public void setComments(List<Comments> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "comments=" + comments +
                '}';
    }
}
