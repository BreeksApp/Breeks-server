// DAO
package project.repository;

import org.springframework.data.repository.CrudRepository;
import project.entity.TimetableElement;

public interface TimetableElementRepository extends CrudRepository<TimetableElement, Integer> {

}
