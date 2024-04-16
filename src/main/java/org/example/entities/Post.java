package org.example.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity (name = "post")
@Data
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String content;

    //ONE TO MANY because one post can have many comments
    //Cascading can be used from both sides of the relationship, in this case, we are using it from the Post side as PERIST
    @OneToMany(mappedBy = "post", cascade = CascadeType.PERSIST) // mappedBy is used to specify the field in the other entity that maps this entity
    private List<Comment> comments;
    // The collection types that can be used for @OneToMany relationship are List, Set, Map, SortedSet, SortedMap, and Collection

}
