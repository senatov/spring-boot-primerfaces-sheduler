package de.senatov.reservationz.auth.repository;



import de.senatov.reservationz.model.User;
import org.springframework.data.jpa.repository.JpaRepository;



public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

}
