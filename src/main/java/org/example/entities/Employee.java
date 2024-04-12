package org.example.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // this class is an entity that represents a table in the database
@Table (name = "employee") // table name in the database,
                          // if not specified, the table name will be the same as the class name,
                          // cases are not important
@Data
@AllArgsConstructor
@NoArgsConstructor //default constructor is a must for entities
//Hibernate (which implements JPA) requires a no-argument constructor for your entity classes.
// This is because Hibernate uses proxies for lazy loading of data, and these proxies require a no-argument constructor to instantiate.
public class Employee {

    @Id // primary key, it is a must to have a primary key in an entity
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment
    private Integer id;

    @Column(name = "name"  ) // column name in the database, if not specified, the column name will be the same as the field name, usually skipped
    private String name;


    private String address;


}
