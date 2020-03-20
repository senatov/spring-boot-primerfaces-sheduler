package de.senatov.reservationz.model;



import lombok.Data;

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
@Data
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(unique = true, name = "user_name")
    private String userName;
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
        userName = EMPTY;
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

        return userName;
    }



    public void setUserName(String userName) {

        this.userName = userName;
    }



    public String getFirstName() {

        return firstName;
    }



    public void setFirstName(String firstName) {

        this.firstName = firstName;
    }



    public String getLastName() {

        return lastName;
    }



    public void setLastName(String lastName) {

        this.lastName = lastName;
    }



    public String geteMail() {

        return eMail;
    }



    public void seteMail(String eMail) {

        this.eMail = eMail;
    }



    public String getPassword() {

        return password;
    }



    public void setPassword(String password) {

        this.password = password;
    }



    public Set<Role> getRoles() {

        return roles;
    }



    public void setRoles(Set<Role> roles) {

        this.roles = roles;
    }

}
