// DAO
package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.entity.Note;
import project.entity.User;

import java.sql.Date;
import java.util.Optional;

public interface NoteRepository extends JpaRepository<Note, Integer> {
    Optional<Note> findByDateAndPageAndUser(Date date, Byte page, User user);
    boolean existsByDateAndPageAndUser(Date date, Byte page, User user);
}
