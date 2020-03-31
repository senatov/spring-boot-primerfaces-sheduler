package de.senatov.reservationz.model;



import javax.persistence.*;
import java.util.Set;



/**
 * @author Iakov Senatov
 * @since 03.2020
 */
@Entity
@Table(name = "role")
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
