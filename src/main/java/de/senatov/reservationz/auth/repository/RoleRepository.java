package de.senatov.reservationz.auth.repository;



import de.senatov.reservationz.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;



public interface RoleRepository extends JpaRepository<Role, Long> {

}
