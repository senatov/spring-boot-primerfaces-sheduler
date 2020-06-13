package de.senatov.reservatio.db;



import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



@Service
@Slf4j
public class UserService implements Serializable {

	@Autowired
	private final UserRepository userRepository;



	public UserService(UserRepository userRepository) {

		this.userRepository = userRepository;
	}



	public List<User> getAllUsers() {

		log.debug("getAllUsers()");
		List<User> posts = new ArrayList<>();
		userRepository
				.findAll()
				.forEach(posts::add);
		return posts;
	}



	public Optional<User> getUser(Long id) {

		log.debug("getUser()");
		return userRepository.findById(id);
	}



	public void addUser(User user) {

		log.debug("addUser()");
		userRepository.save(user);
	}



	public void updateUser(User user) {

		log.debug("updateUser()");
		userRepository.save(user);
	}



	public void deleteUser(User user) {

		log.debug("deleteUser()");
		userRepository.delete(user);
	}

}
