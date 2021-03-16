package project.entity;

import javax.persistence.*;

@Entity(name = "session_key")
public class SessionKey {
    @Id
    @Column(nullable = false, unique = true)
    private String key;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public SessionKey() {

    }

    public SessionKey(String key, User user) {
        this.key = key;
        this.user = user;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
