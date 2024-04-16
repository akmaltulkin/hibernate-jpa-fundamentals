package org.example.entities;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "courses")
@Data
public class Course {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    private String name;


    @ManyToMany (cascade = CascadeType.PERSIST)
    //Many-to-many relationship between students and courses
    // because many students can take many courses and many courses can be taken by many students
    @JoinTable (
            name = "students_courses", // name of the join table
            joinColumns = @JoinColumn(name = "course_id"), // name of the column in the join table that references the course
            inverseJoinColumns = @JoinColumn(name = "student_id") // name of the column in the join table that references the student
    )
    //This side of the relationship is the owning side because it has the join table
    private List<Student> students;
}
