package project.entity;

import javax.persistence.*;

@Entity
public class ElementInDay {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Integer id_;

    @Column(nullable = false)
    private String tagColor_;

    @Column(nullable = false)
    private String mainText_;

    @Column(nullable = false)
    private String timeFrom_;

    @Column(nullable = false)
    private String timeTo_;

    public ElementInDay() {

    }

    public ElementInDay(Integer id, String tagColor, String mainText, String timeFrom, String timeTo) {
        this.id_ = id;
        this.tagColor_ = tagColor;
        this.mainText_ = mainText;
        this.timeFrom_ = timeFrom;
        this.timeTo_ = timeTo;
    }

    public Integer getId() {
        return id_;
    }

    public void setId(Integer id) {
        this.id_ = id;
    }

    public String getTagColor() {
        return tagColor_;
    }

    public void setTagColor(String tagColor) {
        this.tagColor_ = tagColor;
    }

    public String getMainText() {
        return mainText_;
    }

    public void setMainText(String mainText) {
        this.mainText_ = mainText;
    }

    public String getTimeFrom() {
        return timeFrom_;
    }

    public void setTimeFrom(String timeFrom) {
        this.timeFrom_ = timeFrom;
    }

    public String getTimeTo() {
        return timeTo_;
    }

    public void setTimeTo(String timeTo) {
        this.timeTo_ = timeTo;
    }
}
