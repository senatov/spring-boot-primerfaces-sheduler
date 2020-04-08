package de.senatov.reservationz.repository;



import de.senatov.reservationz.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;



public interface UserRepository extends JpaRepository<User, Long>, Serializable {

    User findByUsername(String username);

}
