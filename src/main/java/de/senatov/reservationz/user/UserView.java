package de.senatov.reservationz.user;



import lombok.Data;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;



@Component
@ViewScoped
@Data
public class UserView implements Serializable {
    
    @Autowired
    private Logger LOG;
    @Autowired
    private UserService userService;
    private List<SCUser> scUsers = new ArrayList<>();
    private SCUser scUser;
    private Boolean BRememberMe = Boolean.TRUE;
    private String userName;
    private String password;
    
    
    
    @PostConstruct
    public void init() {
        
        LOG.debug("init()");
        if (userService != null) {
            scUsers = userService.getAllUsers();
        }
        IntStream.range(0, scUsers.size())
                .mapToObj(index -> index + ":" + scUsers.get(index))
                .forEach(System.out::println);
        LOG.info("---------------");
    }
    
    
    
    public String prepareForUpdate(Long id) {
        
        LOG.debug("prepareForUpdate()");
        scUser= userService.getUser(id).get();
        LOG.info("new()");
        return "new";
    }
    
    
    
    public String savePerson() {
        
        LOG.debug("savePerson()");
        userService.addUser(scUser);
        scUsers = userService.getAllUsers();
        LOG.info("save");
        return "save";
    }
    
    
    
    public String newPerson() {
        
        LOG.debug("newPerson()");
        scUser= new SCUser();
        LOG.info("new");
        return "new";
    }
    
}
