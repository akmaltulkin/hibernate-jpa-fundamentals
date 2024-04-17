package org.example.runners;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.entities.Course;
import org.example.entities.Student;
import org.example.persistence.CustomPersistenseUnitInfo;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main04_Entity_Relationships_Many_To_Many {
    public static void main(String[] args) {


        Map<String,String> props = new HashMap<>();
        props.put("hibernate.show_sql","true"); // show sql in the console



        EntityManagerFactory emf = new HibernatePersistenceProvider()
                .createContainerEntityManagerFactory(new CustomPersistenseUnitInfo(), props);// create an entity manager factory using the custom persistence unit info
        EntityManager entityManager = emf.createEntityManager();//  create an entity manager from the entity manager factory


        try {
            entityManager.getTransaction().begin(); // start a transaction


            Student student = new Student();
            student.setName("John Doe");

            Student student2 = new Student();
            student2.setName("Jane Doe");


            Course course = new Course();
            course.setName("Math");

            Course course2 = new Course();
            course2.setName("Science");


            //It is important to set the relationship on both sides
           course.setStudents(List.of(student, student2));
           course2.setStudents(List.of(student2));

              student.setCourses(List.of(course));
              student2.setCourses(List.of(course, course2));


          // persist the course entity, cascade will persist the students
           entityManager.persist(course);
           entityManager.persist(course2);







            entityManager.getTransaction().commit(); // commit the transaction

        }finally {
            entityManager.close(); // close the entity manager
        }




    }
}