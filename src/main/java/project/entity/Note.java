package project.entity;

import javax.persistence.*;

@Entity
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Integer id_;

    @Column
    private String text_;

    @Column
    private String effects_;

    public Note() {

    }

    public Note(Integer id, String text, String effects) {
        this.id_ = id;
        this.text_ = text;
        this.effects_ = effects;
    }

    public Integer getId() {
        return id_;
    }

    public void setId(Integer id) {
        this.id_ = id;
    }

    public String getText() {
        return text_;
    }

    public void setText(String text) {
        this.text_ = text;
    }

    public String getEffects() {
        return effects_;
    }

    public void setEffects(String effects) {
        this.effects_ = effects;
    }
}
