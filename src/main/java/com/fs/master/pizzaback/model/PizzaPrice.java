package com.fs.master.pizzaback.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "pizza_prices", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"size", "pizza_id"})
})
public class PizzaPrice implements Serializable {
    private static final long serialVersionUID = -2041468278253553042L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "pizza_id")
    private Pizza pizza;

    @Enumerated(EnumType.STRING)
    @Column(name = "size")
    private PizzaSize size;

    @Column(name = "price")
    private double price;

    protected PizzaPrice(){}

    public PizzaPrice(Pizza pizza, PizzaSize size, double price) {
        this.pizza = pizza;
        this.size = size;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
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
