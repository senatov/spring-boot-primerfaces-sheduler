package de.senatov.reservatio.service;



import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import de.senatov.reservatio.db.UserEntity;
import de.senatov.reservatio.db.UserRepository;



@Service
@Slf4j
public class UserService implements Serializable {

	@Serial
	private static final long serialVersionUID = 2480837124037856177L;

	private final UserRepository userRepository;


	@Autowired
	public UserService(UserRepository userRepository) {

		this.userRepository = userRepository;
	}


	public List<UserEntity> getAllUsers() throws Exception {

        log.debug("getAllUsers()");
		List<UserEntity> userEntities = new ArrayList<>(4);
		userEntities.addAll(userRepository.findAll());
		return userEntities;
	}


	public Optional<UserEntity> getUser(Long id) throws Exception {

        log.debug("getUserEntity()");
		return userRepository.findById(id);
	}


	public void addUser(UserEntity userEntity) throws Exception {

        log.debug("addUser()");
		userRepository.save(userEntity);
	}


	public void updateUser(UserEntity userEntity) throws Exception {

        log.debug("updateUser()");
		userRepository.save(userEntity);
	}


	public void deleteUser(UserEntity userEntity) throws Exception {

        log.debug("deleteUser()");
		userRepository.delete(userEntity);
	}

}
