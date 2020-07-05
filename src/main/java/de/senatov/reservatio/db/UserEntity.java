package de.senatov.reservatio.db;



import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.common.aliasing.qual.Unique;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;



@Slf4j
@ToString
@Data
@Entity
@Table(name = "SC_USER" ,  schema = "SCHEDULE_DB")
@SecondaryTable(name = "SC_SCHEDULE",  schema = "SCHEDULE_DB", pkJoinColumns = @PrimaryKeyJoinColumn(name = "user_id"))
public class UserEntity implements Serializable {

	private static final long serialVersionUID = -809071111834277692L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id", updatable = false, nullable = false)
	private Long id;
	@Column(name = "user_name")
	@Unique
	private String userName;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	@Column(unique = true, name = "e_mail")
	@Email
	private String eMail;
}
