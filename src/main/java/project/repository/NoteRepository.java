// DAO
package project.repository;

import org.springframework.data.repository.CrudRepository;
import project.entity.Note;

public interface NoteRepository extends CrudRepository<Note, Integer> {

}
