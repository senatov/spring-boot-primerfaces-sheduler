package de.senatov.reservatio.db;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.common.aliasing.qual.Unique;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import java.io.Serializable;


@Slf4j
@ToString
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(schema = "schedule_db", name = "SC_USER")
public class UserEntity implements Serializable {

    private static final long serialVersionUID = -809071111834277692L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
