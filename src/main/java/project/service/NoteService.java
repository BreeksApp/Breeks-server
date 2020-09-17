package project.service;

import project.entity.Note;
import java.util.List;

public interface NoteService {
    boolean addNote(Note note);
    boolean deleteNote(Integer id);
    boolean editNote(Integer id, Note newNote);
    List<Note> listOfNotes();
    Note findNote(Integer id);
}
