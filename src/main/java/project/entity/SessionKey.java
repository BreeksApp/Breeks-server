package project.entity;

import javax.persistence.*;

@Entity(name = "session_key")
public class SessionKey {
    @Id
    @Column(nullable = false, unique = true)
    private String keyUUID;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public SessionKey() {

    }

    public SessionKey(String keyUUID, User user) {
        this.keyUUID = keyUUID;
        this.user = user;
    }

    public String getKey() {
        return keyUUID;
    }

    public void setKey(String keyUUID) {
        this.keyUUID = keyUUID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
