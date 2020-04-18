package de.senatov.reservationz.user;



import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



@Service
public class UserService implements Serializable {

    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private Logger log;



    public UserService(UserRepository userRepository) {

        this.userRepository = userRepository;
    }



    public List<SCUser> getAllUsers() {

        log.debug("getAllUsers()");
        List<SCUser> posts = new ArrayList<>();
        userRepository.findAll()
                .forEach(e -> posts.add(e));
        return posts;
    }



    public Optional<SCUser> getUser(Long id) {

        log.debug("getSCUser()");
        return userRepository.findById(id);
    }



    public void addUser(SCUser SCUser) {

        log.debug("addUser()");
        userRepository.save(SCUser);
    }



    public void updateUser(SCUser SCUser) {

        log.debug("updateUser()");
        userRepository.save(SCUser);
    }



    public void deleteUser(SCUser SCUser) {

        log.debug("deleteUser()");
        userRepository.delete(SCUser);
    }

}
