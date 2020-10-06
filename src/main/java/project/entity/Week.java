package project.entity;

import javax.persistence.*;

@Entity
public class Week {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public Week() {

    }

    public Week(Integer id, User user) {
        this.id = id;;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
