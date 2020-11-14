package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.entity.BreeksLine;
import project.exception.NotAddedToDatabase;
import project.repository.BreeksLineRepository;

import java.util.List;

@Service
public class BreeksLineServiceImpl implements BreeksLineService {

    @Autowired
    private BreeksLineRepository breeksLineRepository;

    @Override
    public void addLine(BreeksLine zone) throws NotAddedToDatabase {
        breeksLineRepository.save(zone);
    }

    @Override
    public boolean deleteLine(Integer id) {
        if (breeksLineRepository.existsById(id)) {
            breeksLineRepository.delete(breeksLineRepository.findById(id).get());
            return true;
        }
        else return false;
    }

    @Override
    public boolean editLine(Integer id, BreeksLine newZone) throws NotAddedToDatabase {
        if (breeksLineRepository.existsById(id)) {
            newZone.setId(id);
            breeksLineRepository.save(newZone);
            return true;
        }
        else return false;
    }

    @Override
    public boolean moveBreek() {
        return false;
    }

    @Override
    public List<BreeksLine> listOfLines() {
        return (List<BreeksLine>) breeksLineRepository.findAll();
    }
}
