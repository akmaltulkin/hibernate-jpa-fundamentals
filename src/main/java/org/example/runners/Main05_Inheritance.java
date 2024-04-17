package org.example.runners;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.entities.Course;
import org.example.entities.Student;
import org.example.entities.inheritance.Book;
import org.example.entities.inheritance.ElectronicDevice;
import org.example.persistence.CustomPersistenseUnitInfo;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main05_Inheritance {
    public static void main(String[] args) {


        Map<String,String> props = new HashMap<>();
        props.put("hibernate.show_sql","true"); // show sql in the console
        props.put("hibernate.hbm2ddl.auto","create"); // create the schema automatically based on the entities



        EntityManagerFactory emf = new HibernatePersistenceProvider()
                .createContainerEntityManagerFactory(new CustomPersistenseUnitInfo(), props);// create an entity manager factory using the custom persistence unit info
        EntityManager entityManager = emf.createEntityManager();//  create an entity manager from the entity manager factory


        try {
            entityManager.getTransaction().begin(); // start a transaction


            Book book = new Book();
            book.setId(1);
            book.setAuthor("John Doe");

            ElectronicDevice electronicDevice = new ElectronicDevice();
            electronicDevice.setId(2);
            electronicDevice.setVolatage(220);

            entityManager.persist(book);
            entityManager.persist(electronicDevice);











            entityManager.getTransaction().commit(); // commit the transaction

        }finally {
            entityManager.close(); // close the entity manager
        }




    }
}