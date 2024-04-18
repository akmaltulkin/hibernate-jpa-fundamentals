package org.example.runners;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import org.example.entities.Product;
import org.example.persistence.CustomPersistenseUnitInfo;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main06_JPQL_Basics {
    public static void main(String[] args) {


        Map<String,String> props = new HashMap<>();
        props.put("hibernate.show_sql","true"); // show sql in the console
        props.put("hibernate.hbm2ddl.auto","none"); // create the schema automatically based on the entities



        EntityManagerFactory emf = new HibernatePersistenceProvider()
                .createContainerEntityManagerFactory(new CustomPersistenseUnitInfo(), props);// create an entity manager factory using the custom persistence unit info
        EntityManager entityManager = emf.createEntityManager();//  create an entity manager from the entity manager factory


        try {
            entityManager.getTransaction().begin(); // start a transaction


            //JPQL allows Select, Update, Delete operations on entities, Insert operations are not supported directly, but can be done using the persist method

            // JPQL is similar to SQL, but it operates on entities, not tables
            // Syntax: SELECT [DISTINCT] FROM [entity_name] [alias] WHERE [condition]
            // entity_name is the name of the entity, alias is the name of the entity in the query, condition is the condition to filter the results

            //Comparison between SQL and JPQL
            // SQL: Select * from Product -> fetch all products from Product table
            // JPQL: SELECT p FROM Product p -> fetch all product attributes from Product entity in the current persistence context


//            String jpql = "SELECT p FROM Product p"; // JPQL query to select all products
//            String jpql = "SELECT p FROM Product p WHERE p.price>500"; // JPQL query to select all products with price greater than 500

            String jpql = "SELECT p FROM Product p WHERE p.price > :price AND p.name LIKE :name "; // JPQL query that uses named parameters
            // Parameters are used to pass values to the query, they are prefixed with a colon(:) and are set using the setParameter method of the query object
            // Example: SELECT p FROM Product p WHERE p.price > :price AND p.name LIKE :name

            //TypedQuery is used to create a query that returns a single result
            TypedQuery<Product> query = entityManager.createQuery(jpql, Product.class);

            // set the parameters for the parametrized query
            query.setParameter("price", 500); // set the price parameter to
            query.setParameter("name", "A%"); // set the name parameter to match all products that start with the letter 'A'

           // Sample Result:
//            Product(id=4, name=Aerodynamic Wool Plate, price=694.18)
//            Product(id=6, name=Awesome Paper Chair, price=732.80)
//            Product(id=8, name=Aerodynamic Silk Bottle, price=979.85)
//            Product(id=25, name=Aerodynamic Bronze Shoes, price=920.29)
//            Product(id=31, name=Awesome Cotton Keyboard, price=701.11)
//            Product(id=32, name=Aerodynamic Copper Lamp, price=957.68)
//            Product(id=97, name=Awesome Linen Clock, price=506.66)


            List<Product> resultList = query.getResultList();

           resultList.forEach(System.out::println);

            entityManager.getTransaction().commit(); // commit the transaction


        }finally {
            entityManager.close(); // close the entity manager
        }




    }
}