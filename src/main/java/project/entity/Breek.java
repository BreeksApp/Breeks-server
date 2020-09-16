package project.entity;

import javax.persistence.*;

@Entity
public class Breek {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Integer id;

    @Column(nullable = false)
    private String linkToEmoji;

    @Column(nullable = false)
    private Integer state;

    public Breek() {

    }

    public Breek(Integer id, String linkToEmoji, Integer state) {
        this.id = id;
        this.linkToEmoji = linkToEmoji;
        this.state = state;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLinkToEmoji() {
        return linkToEmoji;
    }

    public void setLinkToEmoji(String linkToEmoji) {
        this.linkToEmoji = linkToEmoji;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
