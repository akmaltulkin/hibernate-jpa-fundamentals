package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.entities.Employee;
import org.example.persistence.CustomPersistenseUnitInfo;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {


        Map<String,String> props = new HashMap<>();
        props.put("hibernate.show_sql","true"); // show sql in the console
        props.put("hibernate.hbm2ddl.auto","create"); // create the schema automatically based on the entities

        EntityManagerFactory emf = new HibernatePersistenceProvider()
                .createContainerEntityManagerFactory(new CustomPersistenseUnitInfo(), props);// create an entity manager factory using the custom persistence unit info
        EntityManager entityManager = emf.createEntityManager();//  create an entity manager from the entity manager factory


        try {
            entityManager.getTransaction().begin(); // start a transaction

            entityManager.persist(new Employee(1, "Jack Dorsey", "123 Main St")); // insert a new record

            Employee employee = entityManager.find(Employee.class, 1);// retrieve a record

            System.out.println(employee); //Employee(id=1, name=Jack Dorsey, address=123 Main St)

            employee.setName("Jack Black"); // update the record

            System.out.println(employee); //Employee(id=1, name=Jack Black, address=123 Main St)

            entityManager.remove(employee); // delete the record

            Employee employee1 = new Employee(2, "Jane Doe", "456 Main St");

            entityManager.merge(employee1); // insert or update the record





            entityManager.getTransaction().commit(); // commit the transaction

        }finally {
            entityManager.close(); // close the entity manager
        }




    }
}