// DAO
package project.repository;

import org.springframework.data.repository.CrudRepository;
import project.entity.DayOfWeek;

public interface DayOfWeekRepository extends CrudRepository<DayOfWeek, Integer> {

}
