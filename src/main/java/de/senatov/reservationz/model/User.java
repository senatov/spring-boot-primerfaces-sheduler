package de.senatov.reservationz.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import static org.apache.commons.lang3.StringUtils.EMPTY;



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



    public User() {

        firstName = EMPTY;
        lastName = EMPTY;
        username = EMPTY;
        eMail = EMPTY;
        roles = new HashSet<>();
    }



    public Long getId() {

        return id;
    }



    public void setId(Long id) {

        this.id = id;
    }



    public String getUserName() {

        return username;
    }



    public void setUserName(String pUserName) {

        username = pUserName;
    }



    public String getFirstName() {

        return firstName;
    }



    public void setFirstName(String pFirstName) {

        firstName = pFirstName;
    }



    public String getLastName() {

        return lastName;
    }



    public void setLastName(String pLastName) {

        lastName = pLastName;
    }



    public String geteMail() {

        return eMail;
    }



    public void seteMail(String pEMail) {

        eMail = pEMail;
    }



    public String getPassword() {

        return password;
    }



    public void setPassword(String pPassword) {

        password = pPassword;
    }



    public Set<Role> getRoles() {

        return roles;
    }



    public void setRoles(Set<Role> pRoles) {

        roles = pRoles;
    }

}
