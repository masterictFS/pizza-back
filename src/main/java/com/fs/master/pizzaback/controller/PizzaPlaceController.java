package com.fs.master.pizzaback.controller;

import com.fs.master.pizzaback.model.PizzaPlace;
import com.fs.master.pizzaback.repo.PizzaPlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PizzaPlaceController {
    @Autowired
    PizzaPlaceRepository pizzaPlaceRepository;

    @RequestMapping(value = "/pizza_places", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity getPizzaPlaces() {
        Iterable<PizzaPlace> pizzaPlaces = pizzaPlaceRepository.findAll();
        return new ResponseEntity<>(pizzaPlaces, HttpStatus.OK);
    }

    @RequestMapping(value = "/pizza_places/tag/{pizza_place_tag}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity getPizzaPlacesFromTag(@PathVariable("pizza_place_tag") String pizzaPlaceTag) {
        List<PizzaPlace> pizzaPlaces = pizzaPlaceRepository.findByStringTag(pizzaPlaceTag);
        return new ResponseEntity<>(pizzaPlaces, HttpStatus.OK);
    }
}
