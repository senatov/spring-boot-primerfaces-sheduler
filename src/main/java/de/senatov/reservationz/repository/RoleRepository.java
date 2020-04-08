package de.senatov.reservationz.repository;



import de.senatov.reservationz.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;



public interface RoleRepository extends JpaRepository<Role, Long>, Serializable {

}
