package com.fs.master.pizzaback.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "toppings")
public class Topping implements Serializable {

    private static final long serialVersionUID = 6599998751699820010L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    protected Topping() {}

    public Topping(String name) {
        this.name = name;
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
}
