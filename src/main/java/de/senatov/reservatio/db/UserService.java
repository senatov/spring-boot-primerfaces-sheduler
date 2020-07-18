package de.senatov.reservatio.db;



import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



@Service
@Slf4j
@ToString
public class UserService implements Serializable {

	private static final long serialVersionUID = 2480837124037856177L;

	private final UserRepository userRepository;



	@Autowired
	public UserService(UserRepository userRepository) {

		this.userRepository = userRepository;
	}



	public List<UserEntity> getAllUsers() {

		log.debug("getAllUsers()");
		List<UserEntity> userEntities = new ArrayList<>(4);
		userRepository.findAll()
		              .forEach(userEntities::add);
		return userEntities;
	}



	public Optional<UserEntity> getUser(Long id) {

		log.debug("getUserEntity()");
		return userRepository.findById(id);
	}



	public void addUser(UserEntity userEntity) {

		log.debug("addUser()");
		userRepository.save(userEntity);
	}



	public void updateUser(UserEntity userEntity) {

		log.debug("updateUser()");
		userRepository.save(userEntity);
	}



	public void deleteUser(UserEntity userEntity) {

		log.debug("deleteUser()");
		userRepository.delete(userEntity);
	}

}
