package de.senatov.drill.jsf_springboot.controller;



import javax.enterprise.context.SessionScoped;
import javax.faces.FacesException;
import javax.faces.application.ViewExpiredException;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;



@Named
@SessionScoped
public class ExceptionHandlerView implements Serializable {

    public void throwNullPointerException() {

        throw new NullPointerException("A NullPointerException!");
    }



    public void throwWrappedIllegalStateException() {

        Throwable t = new IllegalStateException("A wrapped IllegalStateException!");
        throw new FacesException(t);
    }



    public void throwViewExpiredException() {

        throw new ViewExpiredException("A ViewExpiredException!", FacesContext.getCurrentInstance().getViewRoot().getViewId());
    }
}