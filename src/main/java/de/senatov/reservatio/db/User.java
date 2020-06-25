package de.senatov.reservatio.db;



import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;



@Slf4j
@ToString
@Data
@Entity
@Table(name = "sc_user")
public class User implements Serializable {

	private static final long serialVersionUID = -809071111834277692L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "user_name")
	private String userName;
	@Column(unique = true, name = "e_mail")
	@Email
	private String eMail;

}
