package de.senatov.reservatio.view;


import de.senatov.reservatio.db.UserEntity;
import de.senatov.reservatio.db.UserService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.primefaces.context.PrimeFacesContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Named("userView")
@Component
@ViewScoped
@Data
@Slf4j
public class UserView implements Serializable {

	@Serial
	private static final long serialVersionUID = 3849109028806396639L;
	private String hint = "Save new system userEntity in DB";
	@Autowired
	private UserService userService;
	private List<UserEntity> userEntities = new ArrayList<>(8);
	private UserEntity userEntity;


	@PostConstruct
	public void init() {

		log.debug("init()");
		userEntity = new UserEntity();
		if (userService != null) {
			userEntities = userService.getAllUsers();
		}
	}


	public String prepareForUpdate(Long id) {

		log.debug("prepareForUpdate()");
		userEntity = userService.getUser(id)
				.get();
		return "new";
	}


	public String savePerson() {

		log.debug("savePerson()");
		userService.addUser(userEntity);
		userEntities = new ArrayList<>(8);
		userEntities = userService.getAllUsers();
		addMessage("Save person");
		return "save";

	}


	public String newPerson() {

		log.debug("newPerson()");
		userEntity = new UserEntity();
		return "new";
	}


	List<Long> getIds() {

		return userEntities.stream()
				.map(UserEntity::getId)
				.collect(Collectors.toList());

	}


	List<String> getFirstNames() {

		return userEntities.stream()
				.map(UserEntity::getFirstName)
				.collect(Collectors.toList());

	}


	List<String> getLastNames() {

		return userEntities.stream()
				.map(UserEntity::getLastName)
				.collect(Collectors.toList());
	}


	List<String> getUserNames() {

		return userEntities.stream()
				.map(UserEntity::getUserName)
				.collect(Collectors.toList());

	}


	List<String> getEmails() {

		return userEntities.stream()
				.map(UserEntity::getEMail)
				.collect(Collectors.toList());

	}


	private void addMessage(String summary) {

		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
		PrimeFacesContext.getCurrentInstance()
				.addMessage(null, message);
	}

}
