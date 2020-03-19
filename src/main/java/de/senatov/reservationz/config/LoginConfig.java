package de.senatov.reservationz.config;



import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import java.io.Serializable;



@EnableWebSecurity
//@Data
public class LoginConfig extends WebSecurityConfigurerAdapter implements Serializable {

    private static final long serialVersionUID = -4277828832138180646L;
    private String userName;
    private String password;



    public String getPassword() {

        return password;
    }



    public String getUserName() {

        return userName;
    }

}