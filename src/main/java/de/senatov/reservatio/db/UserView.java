package de.senatov.reservatio.db;



import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;



@Named("userView")
@Component
@ViewScoped
@Slf4j
@Data
public class UserView implements Serializable {

	private static final long serialVersionUID = 3849109028806396639L;
	private String hint = "Save new system user in DB";
	@Autowired
	private UserService userService;
	private List<User> users = new ArrayList<>();
	private User user;



	@PostConstruct
	public void init() {

		log.debug("init()");
		user = new User();
		if (userService != null) {
			users = userService.getAllUsers();
		}
	}



	public String prepareForUpdate(Long id) {

		log.debug("prepareForUpdate()");
		user = userService.getUser(id)
		                  .get();
		return "new";
	}



	public String savePerson() {

		log.debug("savePerson()");
		user.setId(null);
		userService.addUser(user);
		users = new ArrayList<>();
		users = userService.getAllUsers();
		addMessage("Save person");
		return "save";

	}



	public String newPerson() {

		log.debug("newPerson()");
		user = new User();
		return "new";
	}



	List<Long> getIds() {

		return users.stream()
		            .map(User::getId)
		            .collect(Collectors.toList());

	}



	List<String> getFirstNames() {

		return users.stream()
		            .map(User::getFirstName)
		            .collect(Collectors.toList());

	}



	List<String> getLastNames() {

		return users.stream()
		            .map(User::getLastName)
		            .collect(Collectors.toList());
	}



	List<String> getUserNames() {

		return users.stream()
		            .map(User::getUserName)
		            .collect(Collectors.toList());

	}



	List<String> getEmails() {

		return users.stream()
		            .map(User::getEMail)
		            .collect(Collectors.toList());

	}

	public void addMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}


}
