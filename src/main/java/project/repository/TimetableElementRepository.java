// DAO
package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.entity.TimetableElement;
import project.entity.User;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface TimetableElementRepository extends JpaRepository<TimetableElement, Integer> {
    Optional<TimetableElement> findByIdAndUser(Integer id, User user);
    List<TimetableElement> findAllByDateAndUser(Date date, User user);
    boolean existsByIdAndUser(Integer id, User user);
}
