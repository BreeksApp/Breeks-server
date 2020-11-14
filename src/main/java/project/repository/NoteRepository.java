// DAO
package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.entity.Note;

import java.sql.Date;
import java.util.Optional;

public interface NoteRepository extends JpaRepository<Note, Integer> {
    Optional<Note> findByDateAndPage(Date date, Byte page);
}
