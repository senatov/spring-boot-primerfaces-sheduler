package de.senatov.reservationz.service;



import de.senatov.reservationz.model.User;

import java.io.Serializable;


/**
 * @author Iakov Senatov
 * @since 03.2020
 */
public interface UserService extends Serializable {

    void save(User user);

    User findByUsername(String username);

}
