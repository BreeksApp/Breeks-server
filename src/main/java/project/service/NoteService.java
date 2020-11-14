package project.service;

import project.entity.Note;

import java.sql.Date;
import java.util.List;

public interface NoteService {
    void addNote(Note note);
    boolean deleteNote(Integer id);
    boolean editNote(Integer id, Note newNote);
    List<Note> listOfNotes();
    Note findNote(Integer id);
    Note findNoteByDateAndPage(Date date, Byte page);
}
