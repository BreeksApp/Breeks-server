package project.entity;

import javax.persistence.*;

@Entity
public class Breek {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Integer id_;

    @Column(nullable = false)
    private String linkToEmoji_;

    @Column(nullable = false)
    private Integer state_;

    public Breek() {

    }

    public Breek(Integer id, String linkToEmoji, Integer state) {
        this.id_ = id;
        this.linkToEmoji_ = linkToEmoji;
        this.state_ = state;
    }

    public Integer getId() {
        return id_;
    }

    public void setId(Integer id) {
        this.id_ = id;
    }

    public String getLinkToEmoji() {
        return linkToEmoji_;
    }

    public void setLinkToEmoji(String linkToEmoji) {
        this.linkToEmoji_ = linkToEmoji;
    }

    public Integer getState() {
        return state_;
    }

    public void setState(Integer state) {
        this.state_ = state;
    }
}
