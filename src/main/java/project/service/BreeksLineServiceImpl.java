package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.entity.BreeksLine;
import project.entity.User;
import project.exception.NotAddedToDatabase;
import project.repository.BreeksLineRepository;

import java.sql.Date;
import java.util.List;

@Service
public class BreeksLineServiceImpl implements BreeksLineService {

    @Autowired
    private BreeksLineRepository breeksLineRepository;

    @Override
    public void addLine(BreeksLine line) throws NotAddedToDatabase {
        BreeksLine lineFromDB = findBreeksLine(line.getDate(), line.getDescription(), line.getUser());
        if (lineFromDB != null) {
            breeksLineRepository.delete(lineFromDB);
        }
        breeksLineRepository.save(line);
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
    public boolean deleteLine(Date date, String description, User user) {
        if (breeksLineRepository.existsByDateAndDescriptionAndUser(date, description, user)) {
            breeksLineRepository.delete(
                    breeksLineRepository.findByDateAndDescriptionAndUser(date, description, user).get()
            );
            return true;
        }
        else return false;
    }

    @Override
    public boolean editLine(Integer id, BreeksLine newLine) throws NotAddedToDatabase {
        if (breeksLineRepository.existsById(id)) {
            newLine.setId(id);
            breeksLineRepository.save(newLine);
            return true;
        }
        else return false;
    }

    @Override
    public boolean editLine(Date date, String description, User user, BreeksLine newLine) {
        if (breeksLineRepository.existsByDateAndDescriptionAndUser(date, description, user)) {
            newLine.setId(
                    breeksLineRepository.findByDateAndDescriptionAndUser(date, description, user).get().getId()
            );
            newLine.setUser(user);
            breeksLineRepository.save(newLine);
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
        return breeksLineRepository.findAll();
    }

    @Override
    public List<BreeksLine> listOfLinesInWeek(Date date, User user) {
        return breeksLineRepository.findAllByDateAndUser(date, user);
    }

    @Override
    public BreeksLine findBreeksLine(Date date, String description, User user) {
        if (breeksLineRepository.existsByDateAndDescriptionAndUser(date, description, user)) {
            return breeksLineRepository.findByDateAndDescriptionAndUser(date, description, user).get();
        }
        return null;
    }
}
