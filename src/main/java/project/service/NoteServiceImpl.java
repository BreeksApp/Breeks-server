package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.entity.Note;
import project.repository.NoteRepository;

import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteRepository noteRepository;

    @Override
    public boolean addNote(Note note) {
        return false;
    }

    @Override
    public boolean deleteNote(Integer id) {
        return false;
    }

    @Override
    public boolean editNote(Integer id, Note newNote) {
        return false;
    }

    @Override
    public List<Note> listOfNotes() {
        return null;
    }

    @Override
    public Note findNote(Integer id) {
        return null;
    }
}
