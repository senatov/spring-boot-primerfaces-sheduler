package de.senatov.reservatio.user;



import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;



/**
 * @author Iakov Senatov
 * @since 03.2020
 */
@Slf4j
@Data
@Entity
@Table(name = "sc_user", schema = "schedule")
public class User implements Serializable {

	private static final long serialVersionUID = -809071111834277692L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "first_name")
	private String firstname;
	@Column(name = "last_name")
	private String lastname;
	@Column(name = "user_name")
	private String username;
	@Column(name = "password")
	private String password;
	@Column(unique = true, name = "e_mail")
	@Email
	private String eMail;

}
