package org.example.runners;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import org.example.dto.EnrolledStudent;
import org.example.persistence.CustomPersistenseUnitInfo;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main09_JPQL_Joins {
    public static void main(String[] args) {


        Map<String,String> props = new HashMap<>();
        props.put("hibernate.show_sql","true"); // show sql in the console
        props.put("hibernate.hbm2ddl.auto","none"); // create the schema automatically based on the entities



        EntityManagerFactory emf = new HibernatePersistenceProvider()
                .createContainerEntityManagerFactory(new CustomPersistenseUnitInfo(), props);// create an entity manager factory using the custom persistence unit info
        EntityManager entityManager = emf.createEntityManager();//  create an entity manager from the entity manager factory


        try {
            entityManager.getTransaction().begin(); // start a transaction




//            String jpql = "SELECT s, e FROM Student s JOIN s.enrollments e ";

//            TypedQuery<Object[]> query = entityManager.createQuery(jpql, Object[].class);
//            List<Object[]> resultList = query.getResultList();
//            resultList.forEach(objects -> {
//                System.out.println(objects[0] + " " + objects[1]);
//            });


            //Alternatively, we can use a constructor expression to create a DTO (Data Transfer Object)
            // DTO is a class that is used to store the result of a query
            // It can be used instead of an array of objects in this case because we are selecting multiple entities
            //Syntax: SELECT NEW package_name.DTO_name(parameters)
            String jpql = "SELECT NEW org.example.dto.EnrolledStudent(s, e) FROM Student s JOIN s.enrollments e ";

            TypedQuery<EnrolledStudent> query = entityManager.createQuery(jpql, EnrolledStudent.class);
            query.getResultList().forEach(System.out::println);





            entityManager.getTransaction().commit(); // commit the transaction


        }finally {
            entityManager.close(); // close the entity manager
        }




    }
}