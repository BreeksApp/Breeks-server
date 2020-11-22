// DAO
package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.entity.BreeksLine;
import project.entity.User;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface BreeksLineRepository extends JpaRepository<BreeksLine, Integer> {
    Optional<BreeksLine> findByDateAndDescriptionAndUser(Date date,String description, User user);
    List<BreeksLine> findAllByDateAndUser(Date date, User user);
    boolean existsByDateAndDescriptionAndUser(Date date,String description, User user);
}
