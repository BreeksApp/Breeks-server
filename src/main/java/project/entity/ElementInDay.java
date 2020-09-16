package project.entity;

import javax.persistence.*;

@Entity
public class ElementInDay {

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

    public ElementInDay() {

    }

    public ElementInDay(Integer id, String tagColor, String mainText, String timeFrom, String timeTo) {
        this.id = id;
        this.tagColor = tagColor;
        this.mainText = mainText;
        this.timeFrom = timeFrom;
        this.timeTo = timeTo;
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
}
