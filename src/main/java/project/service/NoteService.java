package project.service;

import project.entity.Note;
import project.entity.User;

import java.sql.Date;
import java.util.List;

public interface NoteService {
    void addNote(Note note);
    boolean deleteNote(Integer id, int userId);
    boolean editNote(Date date, Byte page, User user, Note newNote);
    List<Note> listOfNotes();
    Note findNote(Integer id);
    Note findByDateAndPageAndUser(Date date, Byte page, User user);
}
