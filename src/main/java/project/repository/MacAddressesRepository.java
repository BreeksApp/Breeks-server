// DAO
package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.entity.MacAddresses;

public interface MacAddressesRepository extends JpaRepository<MacAddresses, String> {

}
