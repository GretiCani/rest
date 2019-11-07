package com.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.util.UUID;



@Entity
@Data
@NoArgsConstructor
public class Orders extends RepresentationModel {
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
    @Column(columnDefinition = "binary(16)")
    @Id
    private UUID id;
    private String orderDescription;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;

    public Orders(String orderDescription, User user) {
        this.orderDescription = orderDescription;
        this.user = user;
    }
}
