package project.service;

import project.entity.ZoneOfBreeks;
import java.util.List;

public interface ZoneOfBreeksService {
    void addZone(ZoneOfBreeks zone);
    boolean deleteZone(Integer id);
    boolean editZone(Integer id, ZoneOfBreeks newZone);
    boolean moveBreek(); // ?
    List<ZoneOfBreeks> listOfZones();
}
