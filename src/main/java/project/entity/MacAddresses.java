package project.entity;

import javax.persistence.*;

@Entity
public class MacAddresses {
    @Id
    @Column(nullable = false, unique = true)
    private String address;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public MacAddresses() {

    }

    public MacAddresses(String address, User user) {
        this.address = address;
        this.user = user;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
