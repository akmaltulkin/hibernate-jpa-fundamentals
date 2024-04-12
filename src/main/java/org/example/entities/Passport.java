package org.example.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity (name = "passport")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Passport {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        private String number;

        @OneToOne(mappedBy = "passport") // mappedBy is used to specify the field in the other entity that maps this entity
        private Person person;



}
