package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.entity.ElementInDay;
import project.repository.ElementInDayRepository;

import java.util.List;

@Service
public class ElementInDayServiceImpl implements ElementInDayService {

    @Autowired
    private ElementInDayRepository elementInDayRepository;

    @Override
    public boolean addElement(ElementInDay element) {
        return false;
    }

    @Override
    public boolean deleteElement(Integer id) {
        return false;
    }

    @Override
    public boolean editElement(Integer id, ElementInDay newElement) {
        return false;
    }

    @Override
    public boolean moveElement() {
        return false;
    }

    @Override
    public List<ElementInDay> listOfElements() {
        return null;
    }
}
