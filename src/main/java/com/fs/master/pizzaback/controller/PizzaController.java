package com.fs.master.pizzaback.controller;

import com.fs.master.pizzaback.context.PizzaWithToppingsAndUser;
import com.fs.master.pizzaback.model.*;
import com.fs.master.pizzaback.repo.PizzaPlaceRepository;
import com.fs.master.pizzaback.repo.PizzaRepository;
import com.fs.master.pizzaback.repo.PriceRepository;
import com.fs.master.pizzaback.repo.ToppingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PizzaController {

    @Autowired
    PizzaPlaceRepository pizzaPlaceRepository;
    @Autowired
    PizzaRepository pizzaRepository;
    @Autowired
    ToppingRepository toppingRepository;
    @Autowired
    PriceRepository priceRepository;

    @RequestMapping(value = "/{pizza_place_tag}/pizzas", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ResponseEntity postPizza(
            @PathVariable("pizza_place_tag") String pizzaPlaceTag,
            @RequestBody PizzaWithToppingsAndUser pizzaWithToppingsAndUser) {

        List<PizzaPlace> pizzaPlaceList = pizzaPlaceRepository.findByStringTag(pizzaPlaceTag);
        if (pizzaPlaceList.size() > 0) {

            PizzaPlace pizzaPlace = pizzaPlaceList.get(0);
            String name = pizzaWithToppingsAndUser.getName();
            long[] toppingsIds = pizzaWithToppingsAndUser.getToppingsIds();
            long userId = pizzaWithToppingsAndUser.getUserId();

            if (name != null && !name.isEmpty() && toppingsIds != null && toppingsIds.length != 0) {
                Pizza pizza = new Pizza(name, pizzaPlace);

                for (long id : toppingsIds) {
                    toppingRepository.findById(id).ifPresent(topping -> pizza.getToppings().add(topping));
                }

                System.out.println("got here");

                pizza.setUserId(userId);
                pizzaRepository.save(pizza);

                return new ResponseEntity<>(pizza, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/{pizza_place_tag}/pizzas", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity getPizzas(@PathVariable("pizza_place_tag") String pizzaPlaceTag) {
        List<PizzaPlace> pizzaPlaceList = pizzaPlaceRepository.findByStringTag(pizzaPlaceTag);
        if (pizzaPlaceList.size() > 0) {
            PizzaPlace pizzaPlace = pizzaPlaceList.get(0);

            // default pizzas have userId = -1
            Iterable<Pizza> pizzas = pizzaRepository.findByPlaceAndUserId(pizzaPlace.getId(), -1);
            return new ResponseEntity<>(pizzas, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/{pizza_place_tag}/user_pizzas/{user_id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity getUserPizzas(
            @PathVariable("pizza_place_tag") String pizzaPlaceTag,
            @PathVariable("user_id") long user_id) {
        List<PizzaPlace> pizzaPlaceList = pizzaPlaceRepository.findByStringTag(pizzaPlaceTag);
        if (pizzaPlaceList.size() > 0) {
            PizzaPlace pizzaPlace = pizzaPlaceList.get(0);
            Iterable<Pizza> pizzas = pizzaRepository.findByPlaceAndUserId(pizzaPlace.getId(), user_id);
            return new ResponseEntity<>(pizzas, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/pizzas/{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity findPizzaById(@PathVariable("id") long id) {
        Pizza pizza = pizzaRepository.findById(id).orElse(null);
        return (pizza != null)
                ? new ResponseEntity<>(pizza, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // TODO should this be a PUT?
    @RequestMapping(value = "/pizzas/{id}/price", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ResponseEntity addPriceToPizzaById(@PathVariable("id") long id, @RequestBody Price price) {
        Pizza pizza = pizzaRepository.findById(id).orElse(null);
        if (pizza != null) {
            PizzaSize size = price.getSize();
            double priceVal = price.getPrice();

            if (size != null && priceVal > 0) {
                Price pizzaPrice = new Price(size, priceVal);
                boolean priceAdded = pizza.addPrice(pizzaPrice);
                if (priceAdded) {
                    pizzaRepository.save(pizza);
                    return new ResponseEntity<>(pizza, HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // TODO should this be a PUT?
    @RequestMapping(value = "/pizzas/{id}/topping", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ResponseEntity addToppingToPizzaById(@PathVariable("id") long id, @RequestBody Topping toppingIn) {
        Pizza pizza = pizzaRepository.findById(id).orElse(null);
        Topping topping = toppingRepository.findById(toppingIn.getId()).orElse(null);
        if (pizza != null && topping != null) {
            boolean toppingAdded = pizza.addTopping(topping);
            if (toppingAdded) {
                pizzaRepository.save(pizza);
                return new ResponseEntity<>(pizza, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/pizzas/{id}/topping", method = RequestMethod.DELETE, produces = "application/json")
    @ResponseBody
    public ResponseEntity removeToppingFromPizzaById(@PathVariable("id") long id, @RequestBody Topping toppingIn) {
        Pizza pizza = pizzaRepository.findById(id).orElse(null);
        Topping topping = toppingRepository.findById(toppingIn.getId()).orElse(null);
        if (pizza != null && topping != null) {
            boolean toppingRemoved = pizza.removeTopping(topping);
            if (toppingRemoved) {
                pizzaRepository.save(pizza);
                return new ResponseEntity<>(pizza, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/pizzas/{id}", method = RequestMethod.DELETE, produces = "application/json")
    @ResponseBody
    public ResponseEntity deletePizzaById(@PathVariable("id") long id) {
        pizzaRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
