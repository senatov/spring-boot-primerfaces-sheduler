package de.senatov.reservationz.model;



import de.senatov.reservationz.service.UserService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Iakov Senatov
 * @since 03.2020
 */
@Component
@ViewScoped
public class UserView implements Serializable {

    @Autowired
    private Logger LOG;
    @Autowired
    private UserService userService;
    private List<User> users = new ArrayList<>();
    private User user;



    @PostConstruct
    public void init() {

        LOG.debug("init()");
        user = new User();
        if (userService != null) {
            users = userService.getAllUsers();
        }
    }



    public String prepareForUpdate(Long id) {

        LOG.debug("prepareForUpdate()");
        user = userService.getUser(id).get();
        return "new";
    }



    public String savePerson() {

        LOG.debug("savePerson()");
        userService.addUser(user);
        users = new ArrayList<>();
        users = userService.getAllUsers();
        System.out.println("save");
        return "save";
    }



    public String newPerson() {

        LOG.debug("newPerson()");
        user = new User();
        System.out.println("new");
        return "new";
    }



    public List<User> getUsers() {

        return users;
    }



    public void setUsers(List<User> users) {

        this.users = users;
    }



    public User getUser() {

        return user;
    }



    public void setUser(User user) {

        this.user = user;
    }



    public UserService getUserService() {

        return userService;
    }



    public void setUserService(UserService userService) {

        this.userService = userService;
    }

}
