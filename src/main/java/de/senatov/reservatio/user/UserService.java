package de.senatov.reservatio.user;



import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;



@Service
@Slf4j
@ToString
public class UserService implements Serializable {

    @Autowired
    private final UserRepository userRepository;



    public UserService(UserRepository userRepository) {

        this.userRepository = userRepository;
    }



    public List<SCUser> getAllUsers() {

        log.debug("getAllUsers()");
        return new ArrayList<>(userRepository.findAll());
    }



    public Optional<SCUser> getUser(Long id) {

        log.debug("getSCUser()");
        return userRepository.findById(id);
    }



    public void addUser(SCUser scUser) {

        log.debug("addUser()");
        userRepository.save(scUser);
    }



    public void updateUser(SCUser scUser) {

        log.debug("updateUser()");
        userRepository.save(scUser);
    }



    public void deleteUser(SCUser scUser) {

        log.debug("deleteUser()");
        userRepository.delete(scUser);
    }

}
