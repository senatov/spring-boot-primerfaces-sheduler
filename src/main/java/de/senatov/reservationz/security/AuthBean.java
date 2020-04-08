package de.senatov.reservationz.security;



import lombok.Data;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;



@Named
@RequestScoped
@Data
public class AuthBean {

    Boolean bRememberMe;
    private String userName;
    private String password;

}
