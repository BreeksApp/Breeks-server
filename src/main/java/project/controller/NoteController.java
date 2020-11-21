package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.controller.utils.UserDetermination;
import project.entity.Note;
import project.entity.User;
import project.exception.NotAddedToDatabase;
import project.security.jwt.JwtTokenProvider;
import project.service.CustomUserDetailsService;
import project.service.NoteService;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/note")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/addNote")
    public ResponseEntity<?> addNote(@RequestBody Note note) {
        try {
            noteService.addNote(note);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (NotAddedToDatabase exception) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }

    @DeleteMapping("/deleteNote/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable("id") int id) {
        boolean deleted = noteService.deleteNote(id);
        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @PutMapping("/editNote/{id}")
    public ResponseEntity<?> editNote(@PathVariable("id") int id, @RequestBody Note note) {
        boolean updated = noteService.editNote(id, note);
        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @GetMapping("/listOfNotes")
    public ResponseEntity<List<Note>> getAllNotes() {
        List<Note> list = noteService.listOfNotes();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/getNote/{id}")
    public ResponseEntity<Note> getNote(@PathVariable("id") int id) {
        Note note = noteService.findNote(id);
        if (note != null) return new ResponseEntity<>(note, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getNoteByDateAndPage/{timeInMs}/{page}")
    public ResponseEntity<Note> getNote(@RequestHeader("Authorization") String bearerToken,
                                        @PathVariable("timeInMs") long timeInMs,
                                        @PathVariable("page") byte page) {
        User user = UserDetermination.determineUser(bearerToken, jwtTokenProvider, userDetailsService);
        if (user != null) {
            Note note = noteService.findByDateAndPageAndUser(new Date(timeInMs), page, user);
            if (note != null) return new ResponseEntity<>(note, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
