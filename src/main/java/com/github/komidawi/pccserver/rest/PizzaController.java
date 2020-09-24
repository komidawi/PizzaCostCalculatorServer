package com.github.komidawi.pccserver.rest;

import com.github.komidawi.pccserver.data.Pizza;
import com.github.komidawi.pccserver.service.PizzaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/pizza")
public class PizzaController {

    private final PizzaService pizzaService;

    PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @GetMapping("/{id}")
    Pizza getPizza(@PathVariable Long id) {
        return pizzaService.getById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    List<Pizza> getAllPizzas() {
        return pizzaService.getAll();
    }

    @PostMapping
    Pizza addPizza(@RequestBody Pizza newPizza) {
        return pizzaService.save(newPizza);
    }

    @DeleteMapping("/{id}")
    void deletePizza(@PathVariable Long id) {
        Long deletedCount = pizzaService.deleteById(id);

        if (deletedCount.equals(0L)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
