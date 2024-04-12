package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.entities.Product;
import org.example.persistence.CustomPersistenseUnitInfo;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {


        EntityManagerFactory emf = new HibernatePersistenceProvider()
                .createContainerEntityManagerFactory(new CustomPersistenseUnitInfo(), new HashMap());
        EntityManager entityManager = emf.createEntityManager();

        entityManager.getTransaction().begin();

        Product product = new Product();
        product.setId(2);
        product.setName("Milk");

        entityManager.persist(product);


        entityManager.getTransaction().commit();

        entityManager.close();

    }
}