package de.senatov.reservatio.db;



import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



@Component
@ViewScoped
@Slf4j
@Data
public class UserView implements Serializable {

	private static final long serialVersionUID = 3849109028806396639L;
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
		userService.addUser(user);
		users = new ArrayList<>();
		users = userService.getAllUsers();
		System.out.println("save");
		return "save";
	}



	public String newPerson() {

		log.debug("newPerson()");
		user = new User();
		System.out.println("new");
		return "new";
	}

}
