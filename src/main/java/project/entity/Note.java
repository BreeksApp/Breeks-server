package project.entity;

import javax.persistence.*;

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

    @ManyToOne(fetch = FetchType.LAZY)
    private Week week;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public Note() {

    }

    public Note(Integer id, String text, String effects, Week week, User user) {
        this.id = id;
        this.text = text;
        this.effects = effects;
        this.week = week;
        this.user = user;
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
