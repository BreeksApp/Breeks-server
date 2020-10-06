package project.entity;

import javax.persistence.*;

@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Integer id;

    @Column(nullable = false)
    private String linkToImage;

    @OneToOne(fetch = FetchType.LAZY)
    private Week week;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public Image() {

    }

    public Image(Integer id, String linkToImage, Week week, User user) {
        this.id = id;
        this.linkToImage = linkToImage;
        this.week = week;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLinkToImage() {
        return linkToImage;
    }

    public void setLinkToImage(String linkToImage) {
        this.linkToImage = linkToImage;
    }

    public Week getWeek() {
        return week;
    }

    public void setWeek(Week week) {
        this.week = week;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
