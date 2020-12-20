package Model;

import java.io.Serializable;
import java.util.Date;

public class Comments implements Serializable {
    private Author author;
    private Body body;
    private Date created;
    private Date updated;

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    @Override
    public String toString() {
        return "Comments{" +
                "author=" + author +
                ", body=" + body +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }
}
