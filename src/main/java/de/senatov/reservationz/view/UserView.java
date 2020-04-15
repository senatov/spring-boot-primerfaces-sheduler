package de.senatov.reservationz.view;



import de.senatov.reservationz.model.User;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



@Component
@ViewScoped
public class UserView implements Serializable {
    
    @Autowired
    private Logger log;
    @Autowired
    private UserService userService;
    private List<User> users = new ArrayList<>();
    private User user;
    private String userName;
    private String password;
    
    
    
    @PostConstruct
    public void init() {
        
        log.debug("init()");
        user = new User();
        if (userService != null) {
            users = userService.getAllUsers();
        }
    }
    
    
    
    public String getUserName() {
        
        return userName;
    }
    
    
    
    public void setUserName(String userName) {
        
        this.userName = userName;
    }
    
    
    
    public String getPassword() {
        
        return password;
    }
    
    
    
    public void setPassword(String password) {
        
        this.password = password;
    }
    
    
    
    public String prepareForUpdate(Long id) {
        
        log.debug("prepareForUpdate()");
        user = userService.getUser(id)
                .get();
        return "new";
    }
    
    
    
    public String savePerson() {
        
        log.debug("savePerson()");
        userService.addUser(user);
        users = new ArrayList<>();
        users = userService.getAllUsers();
        System.out.println("save");
        return "save";
    }
    
    
    
    public String newPerson() {
        
        log.debug("newPerson()");
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
