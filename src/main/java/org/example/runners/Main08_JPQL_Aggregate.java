package org.example.runners;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.example.entities.Product;
import org.example.persistence.CustomPersistenseUnitInfo;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.hibernate.transform.Transformers;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Main08_JPQL_Aggregate {
    public static void main(String[] args) {


        Map<String,String> props = new HashMap<>();
        props.put("hibernate.show_sql","true"); // show sql in the console
        props.put("hibernate.hbm2ddl.auto","none"); // create the schema automatically based on the entities



        EntityManagerFactory emf = new HibernatePersistenceProvider()
                .createContainerEntityManagerFactory(new CustomPersistenseUnitInfo(), props);// create an entity manager factory using the custom persistence unit info
        EntityManager entityManager = emf.createEntityManager();//  create an entity manager from the entity manager factory


        try {
            entityManager.getTransaction().begin(); // start a transaction


//            String jpql = "SELECT p.name as name, p.price as price FROM Product p";
            String jpql = "SELECT p.name as name, AVG(p.price) as AVGprice FROM Product p GROUP BY p.name";

            // we can convert the result of the query to a list of lists
            TypedQuery<List> query = entityManager.createQuery(jpql, List.class);
            List<List> resultList = query.getResultList();

            for (List list : resultList) {
                System.out.println(list.get(0) + " " + list.get(1));
            }

            // JPQL does not allow TypedQuery to return a list of maps, so we have to use the Query interface
//            Query query = entityManager.createQuery(jpql);
//            query.unwrap(org.hibernate.query.Query.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
//            List<Map<String, Object>> result = query.getResultList();
//            for (Map<String, Object> map : result) {
//                System.out.println(map);
//            }

            entityManager.getTransaction().commit(); // commit the transaction


        }finally {
            entityManager.close(); // close the entity manager
        }




    }
}