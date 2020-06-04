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
import java.util.stream.IntStream;

import static org.apache.commons.lang3.StringUtils.join;



@Component
@ViewScoped
@Data
@Slf4j
public class UserView implements Serializable {

	private static final long serialVersionUID = -4172286508468519350L;
	@Autowired
	private UserService userService;
	private List<User> Users = new ArrayList<>();
	private User User;
	private Boolean BRememberMe = Boolean.TRUE;
	private String userName;
	private String password;



	@PostConstruct
	public void init() {

		log.debug("init()");
		if (userService != null) {
			Users = userService.getAllUsers();
		}
		IntStream
				.range(0, Users.size())
				.mapToObj(o -> {
					return join(o, ":", Users.get(o));
				})
				.forEach(System.out::println);
	}



	public String prepareForUpdate(Long id) {

		User = userService
				.getUser(id)
				.get();
		return "new";
	}



	public String savePerson() {

		userService.addUser(User);
		Users = userService.getAllUsers();
		return "save";
	}



	public String newPerson() {

		User = new User();
		log.info("new");
		return "new";
	}

}
