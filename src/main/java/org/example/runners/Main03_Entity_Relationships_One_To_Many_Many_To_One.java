package org.example.runners;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.entities.Comment;
import org.example.entities.Post;
import org.example.persistence.CustomPersistenseUnitInfo;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main03_Entity_Relationships_One_To_Many_Many_To_One {
    public static void main(String[] args) {


        Map<String,String> props = new HashMap<>();
        props.put("hibernate.show_sql","true"); // show sql in the console
        props.put("hibernate.hbm2ddl.auto","create"); // create the schema automatically based on the entities

        EntityManagerFactory emf = new HibernatePersistenceProvider()
                .createContainerEntityManagerFactory(new CustomPersistenseUnitInfo(), props);// create an entity manager factory using the custom persistence unit info
        EntityManager entityManager = emf.createEntityManager();//  create an entity manager from the entity manager factory


        try {
            entityManager.getTransaction().begin(); // start a transaction


            Post post = new Post();
            post.setTitle("My first post");
            post.setContent("This is my first post content");

            Comment comment1 = new Comment();
            comment1.setContent("This is my first comment");


            Comment comment2 = new Comment();
            comment2.setContent("This is my second comment");

            //It is important to set the relationship on both sides
            post.setComments(List.of(comment1, comment2));
            comment1.setPost(post);
            comment2.setPost(post);

            //persist the post entity, cascade will persist the comments
            entityManager.persist(post);








            entityManager.getTransaction().commit(); // commit the transaction

        }finally {
            entityManager.close(); // close the entity manager
        }




    }
}