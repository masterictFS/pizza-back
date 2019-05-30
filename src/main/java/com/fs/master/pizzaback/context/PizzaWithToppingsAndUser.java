package com.fs.master.pizzaback.context;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class PizzaWithToppingsAndUser implements Serializable {
    private static final long serialVersionUID = -2245310097297477562L;

    @JsonProperty("name")
    private String name;

    @JsonProperty("toppings_ids")
    private long[] toppingsIds;

    @JsonProperty("user_id")
    private long userId;

    protected PizzaWithToppingsAndUser(){}

    public PizzaWithToppingsAndUser(String name, long[] toppingsIds, long userId) {
        this.name = name;
        this.toppingsIds = toppingsIds;
        this.userId = userId;
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

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
