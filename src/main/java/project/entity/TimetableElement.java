package project.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class TimetableElement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Integer id;

    @Column(nullable = false)
    private String tagColor;

    @Column(nullable = false)
    private String mainText;

    @Column(nullable = false)
    private String timeFrom;

    @Column(nullable = false)
    private String timeTo;

    @Column(nullable = false)
    private Integer tagDay;

    @Column(nullable = false)
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public TimetableElement() {

    }

    public TimetableElement(String tagColor, String mainText, String timeFrom,
                            String timeTo, Integer tagDay, Date date) {
        this.tagColor = tagColor;
        this.mainText = mainText;
        this.timeFrom = timeFrom;
        this.timeTo = timeTo;
        this.tagDay = tagDay;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTagColor() {
        return tagColor;
    }

    public void setTagColor(String tagColor) {
        this.tagColor = tagColor;
    }

    public String getMainText() {
        return mainText;
    }

    public void setMainText(String mainText) {
        this.mainText = mainText;
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

    public Integer getTagDay() {
        return tagDay;
    }

    public void setTagDay(Integer tagDay) {
        this.tagDay = tagDay;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
