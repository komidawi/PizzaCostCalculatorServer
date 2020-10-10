package com.github.komidawi.pccserver.rest;

import com.github.komidawi.pccserver.data.Pizza;
import com.github.komidawi.pccserver.service.PizzaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static com.github.komidawi.pccserver.rest.PizzaControllerConfig.*;

@RestController
public class PizzaController {

    private final PizzaService pizzaService;

    PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @GetMapping(BY_ID_PATH + "/{id}")
    Pizza getPizzaById(@PathVariable Long id) {
        return pizzaService.getById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping(BY_UUID_PATH + "/{uuid}")
    Pizza getPizzaByUuid(@PathVariable String uuid) {
        return pizzaService.getByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping(ROOT_PATH)
    List<Pizza> getAllPizzas() {
        return pizzaService.getAll();
    }

    @PostMapping(ROOT_PATH)
    Pizza addPizza(@RequestBody Pizza newPizza) {
        return pizzaService.save(newPizza);
    }

    @DeleteMapping(BY_ID_PATH + "/{id}")
    void deleteById(@PathVariable Long id) {
        Long deletedCount = pizzaService.deleteById(id);

        if (deletedCount.equals(0L)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(BY_UUID_PATH + "/{uuid}")
    void deleteByUuid(@PathVariable String uuid) {
        Long deletedCount = pizzaService.deleteByUuid(uuid);

        if (deletedCount.equals(0L)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
