package com.fs.master.pizzaback.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="pizza_places")
public class PizzaPlace implements Serializable {

    private static final long serialVersionUID = 3655683951573700935L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name="string_tag", length = 8)
    private String stringTag;

    @Column(name="latitude")
    private double latitude;

    @Column(name="longitude")
    private double longitude;

    /*@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "fk_pizza_place", nullable = false)
    private Set<Pizza> pizzas;*/

    protected PizzaPlace(){}

    public PizzaPlace(String name, String stringTag, double latitude, double longitude) {
        this.name = name;
        this.stringTag = stringTag;
        this.latitude = latitude;
        this.longitude = longitude;
        //this.pizzas = new HashSet<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStringTag() {
        return stringTag;
    }

    public void setStringTag(String stringTag) {
        this.stringTag = stringTag;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

   /* public Set<Pizza> getPizzas() {
        return pizzas;
    }

    public void setPizzas(Set<Pizza> pizzas) {
        this.pizzas = pizzas;
    }

    public boolean addPizza(Pizza pizza) {
        return this.pizzas.add(pizza);
    }*/

}
