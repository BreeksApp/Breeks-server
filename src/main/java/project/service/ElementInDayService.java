package project.service;

import project.entity.ElementInDay;
import java.util.List;

public interface ElementInDayService {
    void addElement(ElementInDay element);
    boolean deleteElement(Integer id);
    boolean editElement(Integer id, ElementInDay newElement);
    boolean moveElement(); // ?
    List<ElementInDay> listOfElements();
}
