package project.service;

import project.entity.BreeksLine;
import java.util.List;

public interface BreeksLineService {
    void addLine(BreeksLine zone);
    boolean deleteLine(Integer id);
    boolean editLine(Integer id, BreeksLine newZone);
    boolean moveBreek(); // ?
    List<BreeksLine> listOfLines();
}
