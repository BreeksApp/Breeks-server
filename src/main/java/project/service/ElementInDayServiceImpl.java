package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.entity.ElementInDay;
import project.exception.NotAddedToDatabase;
import project.repository.ElementInDayRepository;

import java.util.List;

@Service
public class ElementInDayServiceImpl implements ElementInDayService {

    @Autowired
    private ElementInDayRepository elementInDayRepository;

    @Override
    public void addElement(ElementInDay element) throws NotAddedToDatabase {
        elementInDayRepository.save(element);
    }

    @Override
    public boolean deleteElement(Integer id) {
        if (elementInDayRepository.existsById(id)) {
            elementInDayRepository.delete(elementInDayRepository.findById(id).get());
            return true;
        }
        else return false;
    }

    @Override
    public boolean editElement(Integer id, ElementInDay newElement) throws NotAddedToDatabase {
        if (elementInDayRepository.existsById(id)) {
            newElement.setId(id);
            elementInDayRepository.save(newElement);
            return true;
        }
        else return false;
    }

    @Override
    public boolean moveElement() {
        return false;
    }

    @Override
    public List<ElementInDay> listOfElements() {
        return (List<ElementInDay>)elementInDayRepository.findAll();
    }
}
