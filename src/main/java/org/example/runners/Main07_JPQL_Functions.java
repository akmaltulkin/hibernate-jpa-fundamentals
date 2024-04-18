package org.example.runners;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import org.example.persistence.CustomPersistenseUnitInfo;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Main07_JPQL_Functions {
    public static void main(String[] args) {


        Map<String,String> props = new HashMap<>();
        props.put("hibernate.show_sql","true"); // show sql in the console
        props.put("hibernate.hbm2ddl.auto","none"); // create the schema automatically based on the entities



        EntityManagerFactory emf = new HibernatePersistenceProvider()
                .createContainerEntityManagerFactory(new CustomPersistenseUnitInfo(), props);// create an entity manager factory using the custom persistence unit info
        EntityManager entityManager = emf.createEntityManager();//  create an entity manager from the entity manager factory


        try {
            entityManager.getTransaction().begin(); // start a transaction


            // FUNCTIONS IN JPQL are the same as in SQL, they are used to perform operations on the data
//            String jpql = "SELECT AVG(p.price) FROM Product p"; // JPQL query that uses function AVG
//            String jpql = "SELECT COUNT(p.price) FROM Product p"; // JPQL query that uses function COUNT
//            String jpql = "SELECT SUM(p.price) FROM Product p"; // JPQL query that uses function SUM
//            String jpql = "SELECT MIN(p.price) FROM Product p"; // JPQL query that uses function MIN
            String jpql = "SELECT MAX(p.price) FROM Product p"; // JPQL query that uses function MAX
            TypedQuery<BigDecimal> query = entityManager.createQuery(jpql, BigDecimal.class); //type of the object returned by the query is BigDecimal
            BigDecimal singleResult = query.getSingleResult(); //get the single result of the query,
            // if the returned value is not a single value, a NoResultException or a NonUniqueResultException will be thrown
            System.out.println(singleResult);

            entityManager.getTransaction().commit(); // commit the transaction


        }finally {
            entityManager.close(); // close the entity manager
        }




    }
}