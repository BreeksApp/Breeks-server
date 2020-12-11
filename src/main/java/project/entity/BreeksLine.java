package project.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class BreeksLine {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Integer id;

    @Column(nullable = false)
    private String description;

    @Column
    private String effects;

    /*
        A number from 0 to 63 representing
        breeks positions in the zone
        2^6 = 64
    */
    @Column(nullable = false)
    private Short conditions;

    @Column(nullable = false)
    private String linkToEmoji;

    /*
        A number from 0 to 4095 representing
        each breek's state in the zone
        4^6 = 4096
    */
    @Column(nullable = false)
    private Short states;

    @Column(nullable = false)
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public BreeksLine() {

    }

    public BreeksLine(String description, String effects, Short conditions,
                      String linkToEmoji, Short states, long timeInMs) {
        this.description = description;
        this.effects = effects;
        this.conditions = conditions;
        this.linkToEmoji = linkToEmoji;
        this.states = states;
        this.date = new Date(timeInMs);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEffects() {
        return effects;
    }

    public void setEffects(String effects) {
        this.effects = effects;
    }

    public Short getConditions() {
        return conditions;
    }

    public void setConditions(Short conditions) {
        this.conditions = conditions;
    }

    public String getLinkToEmoji() {
        return linkToEmoji;
    }

    public void setLinkToEmoji(String linkToEmoji) {
        this.linkToEmoji = linkToEmoji;
    }

    public Short getStates() {
        return states;
    }

    public void setStates(Short states) {
        this.states = states;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(long timeInMs) {
        this.date = new Date(timeInMs);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
