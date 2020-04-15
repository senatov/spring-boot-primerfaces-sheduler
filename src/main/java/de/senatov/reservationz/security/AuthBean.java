package de.senatov.reservationz.security;



import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.io.Serializable;



@Named
@RequestScoped
@Data
@Slf4j
public class AuthBean implements Serializable {

    private static final long serialVersionUID = 8769449518313873803L;
    Boolean bRememberMe = Boolean.TRUE;
    private String userName = "admin";
    private String password = "aaa";

}
