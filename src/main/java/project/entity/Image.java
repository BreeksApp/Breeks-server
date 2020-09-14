package project.entity;

import javax.persistence.*;

@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Integer id_;

    @Column(nullable = false)
    private String linkToImage_;

    public Image() {

    }

    public Image(Integer id, String linkToImage) {
        this.id_ = id;
        this.linkToImage_ = linkToImage;
    }

    public Integer getId() {
        return id_;
    }

    public void setId(Integer id) {
        this.id_ = id;
    }

    public String getLinkToImage() {
        return linkToImage_;
    }

    public void setLinkToImage(String linkToImage) {
        this.linkToImage_ = linkToImage;
    }
}
