package com.rest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.UUID;
@Entity
@DynamicUpdate
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Id
    @Column(columnDefinition = "binary(16)")
    private UUID id;
    @NotEmpty
    @Size(min = 6,max = 25,message = "Username should have min 6 characters and max 25 characters")
    @Column(nullable = false, unique = true)
    private String username;
    @Size(min = 6,message = "Firtname should have at least 6 characters")
    private String firstname;
    @Size(min = 6,message = "Firtname should have at least 6 characters")
    private String lastname;
    @Email(message = "Invalid email address")
    private String email;
    private String role;
    @Column(nullable = false, unique = true)
    private String ssn;

    public User(String username, String firstname, String lastname, String email, String role, String ssn) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.role = role;
        this.ssn = ssn;
    }
}
