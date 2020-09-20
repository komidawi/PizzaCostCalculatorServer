package com.github.komidawi.pccserver.service;

import com.github.komidawi.pccserver.data.Pizza;
import com.github.komidawi.pccserver.data.PizzaRepository;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PizzaService {

    private final PizzaRepository repository;

    PizzaService(PizzaRepository repository) {
        this.repository = repository;
    }

    public List<Pizza> getAll() {
        return repository.findAll();
    }

    public Pizza getById(Long id) {
        return repository.findById(id).get();
    }

    public Pizza save(Pizza newPizza) {
        return repository.save(newPizza);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public void clear() {
        repository.deleteAll();
    }
}
