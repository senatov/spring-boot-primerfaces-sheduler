package de.senatov.reservationz.service;



import de.senatov.reservationz.model.User;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;



public interface UserService extends Serializable {

    List<User> getAllUsers();

    Optional<User> getUser(Long id);

    void addUser(User user);

    void updateUser(User user);

    void deleteUser(User user);

}
