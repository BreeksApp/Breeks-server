package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.entity.Note;
import project.entity.User;
import project.exception.NotAddedToDatabase;
import project.repository.NoteRepository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteRepository noteRepository;

    @Override
    public void addNote(Note note) throws NotAddedToDatabase {
        Note noteFromDB = findByDateAndPageAndUser(note.getDate(), note.getPage(), note.getUser());
        if (noteFromDB != null) {
            noteRepository.delete(noteFromDB);
        }
        noteRepository.save(note);
    }

    @Override
    public boolean deleteNote(Integer id, int userId) {
        if (noteRepository.existsById(id)) {
            Note note = noteRepository.findById(id).get();
            if (note.getUser().getId() == userId) {
                noteRepository.delete(noteRepository.findById(id).get());
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean editNote(Date date, Byte page, User user, Note newNote) {
        if (noteRepository.existsByDateAndPageAndUser(date, page, user)) {
            Note note = noteRepository.findByDateAndPageAndUser(date, page, user).get();
            if (note.getUser().getId() == user.getId()) {
                newNote.setId(note.getId());
                newNote.setUser(user);
                noteRepository.save(newNote);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Note> listOfNotes() {
        return noteRepository.findAll();
    }

    @Override
    public Note findNote(Integer id) {
        if (noteRepository.existsById(id)) {
            return noteRepository.findById(id).get();
        }
        return null;
    }

    @Override
    public Note findByDateAndPageAndUser(Date date, Byte page, User user) {
        Optional<Note> note = noteRepository.findByDateAndPageAndUser(date, page, user);
        if (note.isEmpty()) return null;
        else return note.get();
    }
}
