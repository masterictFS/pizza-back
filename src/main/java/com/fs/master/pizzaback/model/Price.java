package com.fs.master.pizzaback.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "prices")
public class Price implements Serializable {
    private static final long serialVersionUID = -2041468278253553042L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "size")
    private PizzaSize size;

    @Column(name = "price")
    private double price;

    protected Price(){}

    public Price(PizzaSize size, double price) {
        this.size = size;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public PizzaSize getSize() {
        return size;
    }

    public void setSize(PizzaSize size) {
        this.size = size;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
