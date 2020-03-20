package de.senatov.reservationz.model;



import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;



@Entity
@Table(name = "role")
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;



    public String getName() {

        return name;
    }



    public void setName(String name) {

        this.name = name;
    }



    public Long getId() {

        return id;
    }



    public void setId(Long id) {

        this.id = id;
    }



    public Set<User> getUsers() {

        return users;
    }



    public void setUsers(Set<User> users) {

        this.users = users;
    }

}
