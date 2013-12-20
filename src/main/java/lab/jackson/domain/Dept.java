package lab.jackson.domain;

import java.io.Serializable;

/**
 * Author: Frank
 * Date: 13-12-20
 */
public class Dept implements Serializable {
    private Long id;
    private String name;

    public Dept() {
    }

    public Dept(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Dept{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
