package project.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class TimetableElement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Integer elementId;

    @Column(nullable = false)
    private short tagColorNum;

    @Column(nullable = false)
    private String mainText;

    @Column(length = 65535, columnDefinition = "text")
    private String effects;

    @Column(nullable = false)
    private String timeFrom;

    @Column(nullable = false)
    private String timeTo;

    @Column(nullable = false)
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public TimetableElement() {

    }

    public TimetableElement(short tagColorNum, String mainText, String effects,
                            String timeFrom, String timeTo, Date date) {
        this.tagColorNum = tagColorNum;
        this.mainText = mainText;
        this.effects = effects;
        this.timeFrom = timeFrom;
        this.timeTo = timeTo;
        this.date = date;
    }

    public Integer getElementId() {
        return elementId;
    }

    public void setElementId(Integer id) {
        this.elementId = id;
    }

    public short getTagColorNum() {
        return tagColorNum;
    }

    public void setTagColorNum(short tagColorNum) {
        this.tagColorNum = tagColorNum;
    }

    public String getMainText() {
        return mainText;
    }

    public void setMainText(String mainText) {
        this.mainText = mainText;
    }

    public String getEffects() {
        return effects;
    }

    public void setEffects(String effects) {
        this.effects = effects;
    }

    public String getTimeFrom() {
        return timeFrom;
    }

    public void setTimeFrom(String timeFrom) {
        this.timeFrom = timeFrom;
    }

    public String getTimeTo() {
        return timeTo;
    }

    public void setTimeTo(String timeTo) {
        this.timeTo = timeTo;
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
