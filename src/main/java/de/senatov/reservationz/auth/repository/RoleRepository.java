package de.senatov.reservationz.auth.repository;



import de.senatov.reservationz.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;


/**
 * @author Iakov Senatov
 * @since 03.2020
 */
public interface RoleRepository extends JpaRepository<Role, Long>, Serializable {

}
