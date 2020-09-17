package com.github.komidawi.pccserver.rest;

import com.github.komidawi.pccserver.data.Pizza;
import com.github.komidawi.pccserver.data.PizzaRepository;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/pizza")
public class PizzaController {

    private final PizzaRepository repository;

    PizzaController(PizzaRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{id}")
    Pizza getPizza(@PathVariable Long id) {
        return repository.findById(id).get();
    }

    @PostMapping
    Pizza addPizza(@RequestBody Pizza newPizza) {
        return repository.save(newPizza);
    }
}
