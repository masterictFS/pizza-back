package com.fs.master.pizzaback.controller;

import com.fs.master.pizzaback.model.Topping;
import com.fs.master.pizzaback.repo.ToppingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ToppingController {

    @Autowired
    ToppingRepository repository;

    @RequestMapping(value = "/toppings", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ResponseEntity postTopping(@RequestBody Topping topping) {
        String name = topping.getName();
        if (name != null && !name.isEmpty()) {
            repository.save(topping);
            return new ResponseEntity<>(topping, HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/toppings", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity getToppings() {
        Iterable<Topping> toppings = repository.findAll();
        return new ResponseEntity<>(toppings, HttpStatus.OK);
    }

    @RequestMapping(value = "/toppings/{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity findToppingById(@PathVariable("id") long id) {
        Topping topping = repository.findById(id).orElse(null);
        return (topping != null)
                ? new ResponseEntity<>(topping, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
