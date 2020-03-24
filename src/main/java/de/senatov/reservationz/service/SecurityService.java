package de.senatov.reservationz.service;



/**
 * @author Iakov Senatov
 * @since 03.2020
 */
public interface SecurityService {

    String findLoggedInUsername();

    void autoLogin(String username, String password);

}
