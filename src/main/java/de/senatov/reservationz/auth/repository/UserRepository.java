package de.senatov.reservationz.auth.repository;



import de.senatov.reservationz.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;


/**
 * @author Iakov Senatov
 * @since 03.2020
 */
public interface UserRepository extends JpaRepository<User, Long>, Serializable {

    User findByUsername(String username);

}
