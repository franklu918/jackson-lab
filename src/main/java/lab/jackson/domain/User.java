package lab.jackson.domain;

import java.io.Serializable;

/**
 * Author: Frank
 * Date: 13-12-20
 */
public class User implements Serializable {

    private Long id;

    private String name;

    private String password;

    private Dept dept;

    public User() {
    }

    public User(Long id, String name, String password, Dept dept) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.dept = dept;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", dept=" + dept +
                '}';
    }
}
