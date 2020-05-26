package de.senatov.reservatio.user;



import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;



public interface UserRepository extends JpaRepository<User, Long>, Serializable {

	User findByUsername(String username);

}
