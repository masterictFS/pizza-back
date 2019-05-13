package com.fs.master.pizzaback.repo;

import com.fs.master.pizzaback.model.Pizza;
import org.springframework.data.repository.CrudRepository;

public interface PizzaRepository extends CrudRepository<Pizza, Long> {
}
