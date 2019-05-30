package com.fs.master.pizzaback.repo;

import com.fs.master.pizzaback.model.Pizza;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PizzaRepository extends CrudRepository<Pizza, Long> {

    @Query("select p from Pizza p where user_id = :user_id")
    List<Pizza> findByUserId(@Param("user_id") long userId);
}
