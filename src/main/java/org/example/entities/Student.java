package org.example.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table (name = "students")
@Data
public class Student {

    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment
    private int id;
    private String name;


    @ManyToMany (mappedBy = "students") // mappedBy is mandatory in the non-owning side of the relationship
    private List<Course> courses;



}
