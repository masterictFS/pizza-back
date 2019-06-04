package com.fs.master.pizzaback.repo;

import com.fs.master.pizzaback.model.PizzaPlace;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PizzaPlaceRepository extends CrudRepository<PizzaPlace, Long> {

    @Query("select p from PizzaPlace p where string_tag = :string_tag")
    List<PizzaPlace> findByStringTag(@Param("string_tag") String stringTag);
}
