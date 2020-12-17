package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.entity.BreekEmoji;
import project.entity.BreeksLine;
import project.entity.User;
import project.exception.NotAddedToDatabase;
import project.repository.BreekEmojiRepository;
import project.repository.BreeksLineRepository;

import java.sql.Date;
import java.util.List;

@Service
public class BreeksLineServiceImpl implements BreeksLineService {
    @Autowired
    private BreeksLineRepository breeksLineRepository;

    @Autowired
    private BreekEmojiRepository breekEmojiRepository;

    @Override
    public void addLine(BreeksLine line) throws NotAddedToDatabase {
        BreeksLine lineFromDB = findBreeksLine(line.getDate(), line.getDescription(), line.getUser());
        if (lineFromDB != null) {
            breeksLineRepository.delete(lineFromDB);
        }
        for (BreekEmoji emoji : line.getEmojies()) {
            if (!breekEmojiRepository.existsByEmojiNum(emoji.getEmojiNum())) {
                breekEmojiRepository.save(emoji);
            }
            else {
                emoji.setId(breekEmojiRepository.findByEmojiNum(emoji.getEmojiNum()).getId());
            }
        }
        breeksLineRepository.save(line);
    }

    @Override
    public boolean deleteLine(Integer id, int userId) {
        if (breeksLineRepository.existsById(id)) {
            BreeksLine bl = breeksLineRepository.findById(id).get();
            if (bl.getUser().getId() == userId) {
                breeksLineRepository.delete(breeksLineRepository.findById(id).get());
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean editLine(Integer id, BreeksLine newLine, User user) throws NotAddedToDatabase {
        if (breeksLineRepository.existsById(id)) {
            BreeksLine bl = breeksLineRepository.findById(id).get();
            if (newLine.getUser().getId() == user.getId()) {
                newLine.setLineId(id);
                newLine.setUser(user);
                for (BreekEmoji emoji : newLine.getEmojies()) {
                    if (!breekEmojiRepository.existsByEmojiNum(emoji.getEmojiNum())) {
                        breekEmojiRepository.save(emoji);
                    }
                    else {
                        emoji.setId(breekEmojiRepository.findByEmojiNum(emoji.getEmojiNum()).getId());
                    }
                }
                breeksLineRepository.save(newLine);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<BreeksLine> listOfLines() {
        return breeksLineRepository.findAll();
    }

    @Override
    public List<BreeksLine> listOfLinesInWeek(Date date, User user) {
        List<BreeksLine> list = breeksLineRepository.findAllByDateAndUser(date, user);
        return list;
    }

    @Override
    public BreeksLine findBreeksLine(Date date, String description, User user) {
        if (breeksLineRepository.existsByDateAndDescriptionAndUser(date, description, user)) {
            return breeksLineRepository.findByDateAndDescriptionAndUser(date, description, user).get();
        }
        return null;
    }
}
