package com.example.springbootwebflowjsf.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



@Component
@ViewScoped
public class UserView implements Serializable {


    @Autowired
    private UserService userService;

    private List<User> users = new ArrayList<>();

    private User user;

    @PostConstruct
    public void init() {
        user = new User();
        if(userService !=null) {
            users = userService.getAllUsers();

        }
    }

    public String prepareForUpdate(Long id){
        user = userService.getUser(id).get();
        return "new";
    }


    public String savePerson () {
        userService.addUser(user);
        users = new ArrayList<>();
        users = userService.getAllUsers();
        System.out.println("save");
        return "save";
    }

    public String newPerson () {
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

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public UserService getUserService() {
        return userService;
    }

//    public void save() {
//        FacesContext.getCurrentInstance().addMessage(null,
//                new FacesMessage("Welcome " + firstname + " " + lastname));
//    }


}
