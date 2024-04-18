package org.example.entities.inheritance;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // default strategy where all the fields of the subclasses are stored in the same table, not the best strategy for performance because it can lead to null values
@Inheritance(strategy = InheritanceType.JOINED) // joined strategy where each subclass has its own table, this is the most normalized strategy and the most commonly used
public abstract class Products {

    @Id
    private int id;
    private String name;

    public Products() {
    }

    public Products(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
