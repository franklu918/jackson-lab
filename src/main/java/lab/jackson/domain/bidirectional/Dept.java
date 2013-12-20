package lab.jackson.domain.bidirectional;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Author: Frank
 * Date: 13-12-20
 *
 * Dept与User双向关联
 */
public class Dept implements Serializable {
    private Long id;
    private String name;

    @JsonIgnore
    private Set<User> users = new HashSet<User>();

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

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Dept{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
