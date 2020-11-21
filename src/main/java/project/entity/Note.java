package project.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Integer id;

    @Column
    private String text;

    @Column
    private String effects;

    @Column(nullable = false)
    private Byte page;

    @Column(nullable = false)
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public Note() {

    }

    public Note(String text, String effects, Byte page, Date date) {
        this.text = text;
        this.effects = effects;
        this.page = page;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getEffects() {
        return effects;
    }

    public void setEffects(String effects) {
        this.effects = effects;
    }

    public Byte getPage() {
        return page;
    }

    public void setPage(Byte page) {
        this.page = page;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
