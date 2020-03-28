package de.senatov.reservationz.service;



import de.senatov.reservationz.model.User;



/**
 * @author Iakov Senatov
 * @since 03.2020
 */
public interface UserAuthenticationProviderService {

    /**
     * Process user authentication
     *
     * @param user
     * @return
     */
    boolean processUserAuthentication(User user);

}