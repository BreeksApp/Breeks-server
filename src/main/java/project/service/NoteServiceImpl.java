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
        noteRepository.save(note);
    }

    @Override
    public boolean deleteNote(Integer id) {
        if (noteRepository.existsById(id)) {
            noteRepository.delete(noteRepository.findById(id).get());
            return true;
        }
        else return false;
    }

    @Override
    public boolean editNote(Integer id, Note newNote) throws NotAddedToDatabase {
        if (noteRepository.existsById(id)) {
            newNote.setId(id);
            noteRepository.save(newNote);
            return true;
        }
        else return false;
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
