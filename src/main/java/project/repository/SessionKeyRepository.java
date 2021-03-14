// DAO
package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.entity.SessionKey;

public interface SessionKeyRepository extends JpaRepository<SessionKey, String> {

}
