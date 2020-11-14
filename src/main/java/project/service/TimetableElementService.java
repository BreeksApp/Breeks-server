package project.service;

import project.entity.TimetableElement;
import java.util.List;

public interface TimetableElementService {
    void addElement(TimetableElement element);
    boolean deleteElement(Integer id);
    boolean editElement(Integer id, TimetableElement newElement);
    boolean moveElement(); // ?
    List<TimetableElement> listOfElements();
}
