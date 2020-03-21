package de.senatov.reservationz.service;



import de.senatov.reservationz.auth.repository.UserRepository;
import de.senatov.reservationz.model.User;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private Logger LOG;



    public UserServiceImpl(UserRepository userRepository) {

        this.userRepository = userRepository;
    }



    public List<User> getAllUsers() {

        LOG.debug("getAllUsers()");
        List<User> posts = new ArrayList<>();
        userRepository.findAll()
                .forEach(e -> posts.add(e));
        return posts;
    }



    public Optional<User> getUser(Long id) {

        LOG.debug("getUser()");
        return userRepository.findById(id);
    }



    public void addUser(User user) {

        LOG.debug("addUser()");
        userRepository.save(user);
    }



    public void updateUser(User user) {

        LOG.debug("updateUser()");
        userRepository.save(user);
    }



    public void deleteUser(User user) {

        LOG.debug("deleteUser()");
        userRepository.delete(user);
    }

}
