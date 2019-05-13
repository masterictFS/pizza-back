package com.fs.master.pizzaback.context;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class PizzaWithToppings implements Serializable {
    private static final long serialVersionUID = -2245310097297477562L;

    @JsonProperty("name")
    private String name;

    @JsonProperty("toppings_ids")
    private long[] toppingsIds;

    protected PizzaWithToppings(){}

    public PizzaWithToppings(String name, long[] toppingsIds) {
        this.name = name;
        this.toppingsIds = toppingsIds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long[] getToppingsIds() {
        return toppingsIds;
    }

    public void setToppingsIds(long[] toppingsIds) {
        this.toppingsIds = toppingsIds;
    }
}
