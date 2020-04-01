package de.senatov.reservationz.model;



import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.Set;



/**
 * @author Iakov Senatov
 * @since 03.2020
 */
@Data
@Entity
@Table(name = "user", schema = "scheduler")
public class User implements Serializable {

    private static final long serialVersionUID = -809071111834277692L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(unique = true, name = "user_name")
    private String username;
    @Column(unique = true, name = "e_mail")
    @Email
    private String eMail;
    @Column(name = "password")
    private String password;
    @ManyToMany
    private Set<Role> roles;
}
