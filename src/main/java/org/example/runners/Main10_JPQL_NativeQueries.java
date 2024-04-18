package org.example.runners;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import org.example.entities.Product;
import org.example.persistence.CustomPersistenseUnitInfo;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;
import java.util.Map;

public class Main10_JPQL_NativeQueries {
    public static void main(String[] args) {


        Map<String,String> props = new HashMap<>();
        props.put("hibernate.show_sql","true"); // show sql in the console
        props.put("hibernate.hbm2ddl.auto","none"); // create the schema automatically based on the entities



        EntityManagerFactory emf = new HibernatePersistenceProvider()
                .createContainerEntityManagerFactory(new CustomPersistenseUnitInfo(), props);// create an entity manager factory using the custom persistence unit info
        EntityManager entityManager = emf.createEntityManager();//  create an entity manager from the entity manager factory


        try {
            entityManager.getTransaction().begin(); // start a transaction




            String sql = "SELECT * FROM Product";

            // create a native query that uses the sql query and maps the result to the Product entity
            Query nativeQuery = entityManager.createNativeQuery(sql, Product.class);
            //Using native queries is not recommended because they are not portable across different databases
            //They are useful when we have to use database-specific features that are not supported by JPQL
            //They are also useful when we have to use a query that is too complex to be written in JPQL


            nativeQuery.getResultList().forEach(System.out::println);

            entityManager.getTransaction().commit(); // commit the transaction


        }finally {
            entityManager.close(); // close the entity manager
        }




    }
}