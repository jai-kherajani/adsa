package Model;

import java.io.Serializable;

public class Issuetype  implements Serializable {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Issuetype{" +
                "name='" + name + '\'' +
                '}';
    }
}
