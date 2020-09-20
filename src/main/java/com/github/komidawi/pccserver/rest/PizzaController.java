package com.github.komidawi.pccserver.rest;

import com.github.komidawi.pccserver.data.Pizza;
import com.github.komidawi.pccserver.service.PizzaService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pizza")
public class PizzaController {

    private final PizzaService pizzaService;

    PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @GetMapping("/{id}")
    Pizza getPizza(@PathVariable Long id) {
        return pizzaService.getById(id);
    }

    @PostMapping
    Pizza addPizza(@RequestBody Pizza newPizza) {
        return pizzaService.save(newPizza);
    }

    @DeleteMapping("/{id}")
    void deletePizza(@PathVariable Long id) {
        pizzaService.delete(id);
    }
}
