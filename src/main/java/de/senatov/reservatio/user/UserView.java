package de.senatov.reservatio.user;



import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;



@Component
@ViewScoped
@Data
@Slf4j
public class UserView implements Serializable {

	@Autowired
	private UserService userService;
	private List<SCUser> scUsers = new ArrayList<>();
	private SCUser scUser;
	private Boolean BRememberMe = Boolean.TRUE;
	private String userName;
	private String password;



	@PostConstruct
	public void init() {

		log.debug("init()");
		if (userService != null) {
			scUsers = userService.getAllUsers();
		}
		IntStream
				.range(0, scUsers.size())
				.mapToObj(index -> index + ":" + scUsers.get(index))
				.forEach(System.out::println);
		log.info("---------------");
	}



	public String prepareForUpdate(Long id) {

		log.debug("prepareForUpdate()");
		scUser = userService
				.getUser(id)
				.get();
		log.info("new()");
		return "new";
	}



	public String savePerson() {

		log.debug("savePerson()");
		userService.addUser(scUser);
		scUsers = userService.getAllUsers();
		log.info("save");
		return "save";
	}



	public String newPerson() {

		log.debug("newPerson()");
		scUser = new SCUser();
		log.info("new");
		return "new";
	}

}
