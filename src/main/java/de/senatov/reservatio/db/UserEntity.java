package de.senatov.reservatio.db;



import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serial;
import java.io.Serializable;



@Slf4j
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sc_user")
@Getter
@Setter
@ToString
public class UserEntity implements Serializable {

	@Serial
	private static final long serialVersionUID = -809071111834277692L;
	@Column(nullable = false)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "user_name")
	private String userName;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	@Column(unique = true, name = "e_mail")
	@Email
	private String eMail;

}
