package de.senatov.reservationz.security;



import lombok.Data;
import lombok.extern.java.Log;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.io.Serializable;



@Named
@RequestScoped
@Data
@Log
public class AuthBean implements Serializable {

    private static final long serialVersionUID = 8769449518313873803L;
    Boolean bRememberMe = Boolean.TRUE;
    private String userName = "admin";
    private String password = "aaa";

}
