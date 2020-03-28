package de.senatov.reservationz.service;



import de.senatov.reservationz.model.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;



/**
 * Provides processing service to set user authentication session
 *
 * @author senat
 * @since 2020.02
 */
public class UserAuthenticationProviderServiceImpl implements UserAuthenticationProviderService {

    private AuthenticationManager authenticationManager;



    public AuthenticationManager getAuthenticationManager() {

        return this.authenticationManager;
    }



    public void setAuthenticationManager(AuthenticationManager authenticationManager) {

        this.authenticationManager = authenticationManager;
    }



    @Override
    public boolean processUserAuthentication(User user) {

        try {
            Authentication request = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
            Authentication result = this.authenticationManager.authenticate(request);
            SecurityContextHolder.getContext().setAuthentication(result);
            return true;
        }
        catch (AuthenticationException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "Sorry!"));
            return false;
        }
    }

}
