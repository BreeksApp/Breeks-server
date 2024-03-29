package project.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
public class BreeksLine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Integer lineId;

    @Lob @Type(type = "org.hibernate.type.TextType") // 131000
    private String description;

    @Lob @Type(type = "org.hibernate.type.TextType") // 131000
    private String effects;

    /*
        A number from 0 to 63 representing
        breeks positions in the zone
        2^6 = 64
    */
    @Column(nullable = false)
    private Short conditions;

    /*
        A number from 0 to 4095 representing
        each breek's state in the zone
        4^6 = 4096
    */
    @Column(nullable = false)
    private Short states;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "emoji_id")
    private List<BreekEmoji> emojies;

    @Column(nullable = false)
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public BreeksLine() {

    }

    public BreeksLine(String description, String effects, Short conditions,
                      Short states, List<BreekEmoji> emojies, Date date) {
        this.description = description;
        this.effects = effects;
        this.conditions = conditions;
        this.states = states;
        this.emojies = emojies;
        this.date = date;
    }

    public Integer getLineId() {
        return lineId;
    }

    public void setLineId(Integer id) {
        this.lineId = id;
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

    public Short getStates() {
        return states;
    }

    public void setStates(Short states) {
        this.states = states;
    }

    public List<BreekEmoji> getEmojies() {
        return emojies;
    }

    public void setEmojies(List<BreekEmoji> emojies) {
        this.emojies = emojies;
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
