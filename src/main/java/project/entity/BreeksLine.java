package project.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class BreeksLine {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Integer lineId;

    @Column(nullable = false)
    private String description;

    /*
        A number from 0 to 127 representing
        breeks positions in the zone
        2^7 = 128
    */
    @Column(nullable = false)
    private Byte conditions;

    /*
        A number from 0 to 2186 representing
        each breek's state in the zone
        3^7 = 2187
    */
    @Column(nullable = false)
    private Short states;

    @Column(nullable = false)
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public BreeksLine() {

    }

    public BreeksLine(String description, Byte conditions, Short states, long timeInMs) {
        this.description = description;
        this.conditions = conditions;
        this.states = states;
        this.date = new Date(timeInMs);
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

    public Byte getConditions() {
        return conditions;
    }

    public void setConditions(Byte conditions) {
        this.conditions = conditions;
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
