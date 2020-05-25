package de.senatov.reservatio.user;



import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.apache.commons.lang3.StringUtils.join;



@Component
@ViewScoped
@Data
@Slf4j
public class UserView implements Serializable {

    @Autowired
    private UserService userService;
    private List<SCUser> scUsers = new ArrayList<>();
    private SCUser scUser;
    private Boolean BRememberMe = Boolean.TRUE;
    private String userName;
    private String password;



    @PostConstruct
    public void init() {

        log.debug("init()");
        if (userService != null) {
            scUsers = userService.getAllUsers();
        }
        IntStream.range(0, scUsers.size()).mapToObj(index -> {
            return join(index, ":", scUsers.get(index));
        }).forEach(System.out::println);
        log.info("---------------");
    }



    public String prepareForUpdate(Long id) {

        scUser = userService.getUser(id).get();
        return "new";
    }



    public String savePerson() {

        userService.addUser(scUser);
        scUsers = userService.getAllUsers();
        return "save";
    }



    public String newPerson() {

        log.debug("newPerson()");
        scUser = new SCUser();
        log.info("new");
        return "new";
    }

}
