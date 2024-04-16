package org.example.entities;

import jakarta.persistence.*;
import lombok.Data;


@Entity (name = "comment")
@Data
public class Comment {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String content;

    //CascadeType.REMOVE is used to propagate the remove operation to the associated entity
    //Cascading can be applied from both sides of the relationship, in this case, we are using it from the Comment side as REMOVE
    @ManyToOne (cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    // fetch is used to specify the fetch type, by default it is eager
    // FetchType.LAZY is used to load the associated entity when it is needed
    // FetchType.EAGER is used to load the associated entity immediately
    @JoinColumn(name = "post")
    private Post post;
}
