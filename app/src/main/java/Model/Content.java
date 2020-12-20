package Model;

import java.io.Serializable;
import java.util.List;

public class Content implements Serializable {

    private String text;
    private List<Content> content;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Content> getContent() {
        return content;
    }

    public void setContent(List<Content> content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Content{" +
                "text='" + text + '\'' +
                ", content=" + content +
                '}';
    }
}
