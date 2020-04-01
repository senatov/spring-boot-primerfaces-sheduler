package de.senatov.reservationz.model;



import lombok.Data;

import javax.persistence.*;
import java.util.Set;



/**
 * @author Iakov Senatov
 * @since 03.2020
 */
@Data
@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

}
