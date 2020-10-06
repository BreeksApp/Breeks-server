package project.entity;

import javax.persistence.*;

@Entity
public class ZoneOfBreeks {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Integer id;

    @Column(nullable = false)
    private String description;

    /*
        A number from 0 to 127 representing
        breeks positions in the zone
        2^7 = 128
    */
    @Column(nullable = false)
    private Byte conditions;

    @Column(nullable = false)
    private String linkToEmoji;

    /*
        A number from 0 to 2186 representing
        each breek's state in the zone
        3^7 = 2187
    */
    @Column(nullable = false)
    private Short states;

    @ManyToOne(fetch = FetchType.LAZY)
    private Week week;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public ZoneOfBreeks() {

    }

    public ZoneOfBreeks(Integer id, String description, Byte conditions,
                        String linkToEmoji, Short states, Week week, User user) {
        this.id = id;
        this.description = description;
        this.conditions = conditions;
        this.linkToEmoji = linkToEmoji;
        this.states = states;
        this.week = week;
        this.user = user;
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

    public Byte getConditions() {
        return conditions;
    }

    public void setConditions(Byte conditions) {
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
