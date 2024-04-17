package org.example.runners;

import com.github.javafaker.Faker;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.entities.Passport;
import org.example.entities.Person;
import org.example.persistence.CustomPersistenseUnitInfo;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;
import java.util.Map;

public class Main02_Entity_Relationships_One_To_One {
    public static void main(String[] args) {


        Map<String,String> props = new HashMap<>();
        props.put("hibernate.show_sql","true"); // show sql in the console
        props.put("hibernate.hbm2ddl.auto","create"); // create the schema automatically based on the entities

        EntityManagerFactory emf = new HibernatePersistenceProvider()
                .createContainerEntityManagerFactory(new CustomPersistenseUnitInfo(), props);// create an entity manager factory using the custom persistence unit info
        EntityManager entityManager = emf.createEntityManager();//  create an entity manager from the entity manager factory


        try {
            entityManager.getTransaction().begin(); // start a transaction


            for (int i = 0; i < 100; i++) {

                Person person = new Person();
                person.setName(new Faker().name().fullName());
                Passport passport = new Passport();
                passport.setNumber(new Faker().idNumber().valid());
                person.setPassport(passport);
                entityManager.persist(person); // no need to persist the passport entity, it will be persisted automatically due to the cascade property in the Person entity


            }





            entityManager.getTransaction().commit(); // commit the transaction

        }finally {
            entityManager.close(); // close the entity manager
        }




    }
}