package org.example.runners;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.example.entities.Product;
import org.example.persistence.CustomPersistenseUnitInfo;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;
import java.util.Map;

public class Main11_JPQL_CriteriaQueries {
    public static void main(String[] args) {


        Map<String,String> props = new HashMap<>();
        props.put("hibernate.show_sql","true"); // show sql in the console
        props.put("hibernate.hbm2ddl.auto","none"); // create the schema automatically based on the entities



        EntityManagerFactory emf = new HibernatePersistenceProvider()
                .createContainerEntityManagerFactory(new CustomPersistenseUnitInfo(), props);// create an entity manager factory using the custom persistence unit info
        EntityManager entityManager = emf.createEntityManager();//  create an entity manager from the entity manager factory


        try {
            entityManager.getTransaction().begin(); // start a transaction



            //Criteria Queries are used to create queries using the Criteria API
            //The Criteria API is a type-safe way to create queries
            //It is used to create queries programmatically, without using JPQL
            //It is useful when we have to create queries dynamically, based on the user input
            //It is also useful when we have to create queries that are too complex to be written in JPQL

            CriteriaBuilder criteriaBuilder = emf.getCriteriaBuilder(); // get the criteria builder from the entity manager factory
            CriteriaQuery<Product> cq = criteriaBuilder.createQuery(Product.class); // create a criteria query that returns a Product object

            Root<Product> productRoot = cq.from(Product.class);// get the root of the query, which is the Product entity

            cq.select(productRoot);// SELECT * FROM Product
//            cq.select(productRoot.get("name"));// SELECT name FROM Product
//            cq.multiselect(productRoot.get("name"), productRoot.get("price"));// SELECT name, price FROM Product
//            cq.select(productRoot).where(criteriaBuilder.equal(productRoot.get("name"), "Sleek Marble Car"));// SELECT * FROM Product WHERE name = 'Sleek Marble Car'

            TypedQuery<Product> query = entityManager.createQuery(cq); // create a typed query from the criteria query

            query.getResultList().forEach(System.out::println); // print the result of the query





            entityManager.getTransaction().commit(); // commit the transaction


        }finally {
            entityManager.close(); // close the entity manager
        }




    }
}