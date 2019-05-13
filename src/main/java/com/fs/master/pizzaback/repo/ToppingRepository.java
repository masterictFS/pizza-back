package com.fs.master.pizzaback.repo;

import com.fs.master.pizzaback.model.Topping;
import org.springframework.data.repository.CrudRepository;

public interface ToppingRepository extends CrudRepository<Topping, Long> {
}
