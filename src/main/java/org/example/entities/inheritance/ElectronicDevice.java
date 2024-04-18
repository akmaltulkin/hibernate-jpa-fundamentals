package org.example.entities.inheritance;

import jakarta.persistence.Entity;

@Entity
public class ElectronicDevice extends Products {
    private int voltage;

    public ElectronicDevice() {
    }

    public ElectronicDevice(String name, int manufacturer) {
        super(name);
        this.voltage = manufacturer;
    }

    public int getVolatage() {
        return voltage;
    }

    public void setVolatage(int volatage) {
        this.voltage = volatage;
    }
}
