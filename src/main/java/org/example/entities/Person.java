package org.example.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "person")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToOne(cascade = CascadeType.PERSIST) // cascade is used to propagate the persist operation to the associated entity
    @JoinColumn (name = "passport") // foreign key column name in the database, by default it will be passport_id
    private Passport passport;



}
