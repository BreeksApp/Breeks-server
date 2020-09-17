package project.service;

import project.entity.Breek;
import java.util.List;

public interface BreekService {
    void addBreek(Breek breek);
    boolean deleteBreek(Integer id);
    boolean editBreek(Integer id, Breek newBreek);
    boolean moveBreek(); // ?
    List<Breek> listOfBreeks();
}
