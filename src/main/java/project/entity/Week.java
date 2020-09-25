package project.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Week {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Integer id;

    @OneToMany
    private List<ZoneOfBreeks> zonesOfWeek;

    @OneToMany
    private List<Note> notesOfWeek;

    @OneToOne
    private Image imageOfWeek;

    public Week() {

    }

    public Week(Integer id, List<ZoneOfBreeks> zonesOfWeek,
                List<Note> notesOfWeek, Image imageOfWeek) {
        this.id = id;;
        this.zonesOfWeek = zonesOfWeek;
        this.notesOfWeek = notesOfWeek;
        this.imageOfWeek = imageOfWeek;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<ZoneOfBreeks> getZonesOfWeek() {
        return zonesOfWeek;
    }

    public void setZonesOfWeek(List<ZoneOfBreeks> zonesOfWeek) {
        this.zonesOfWeek = zonesOfWeek;
    }

    public List<Note> getNotesOfWeek() {
        return notesOfWeek;
    }

    public void setNotesOfWeek(List<Note> notesOfWeek) {
        this.notesOfWeek = notesOfWeek;
    }

    public Image getImageOfWeek() {
        return imageOfWeek;
    }

    public void setImageOfWeek(Image imageOfWeek) {
        this.imageOfWeek = imageOfWeek;
    }
}
