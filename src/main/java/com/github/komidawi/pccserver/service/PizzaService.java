package com.github.komidawi.pccserver.service;

import com.github.komidawi.pccserver.data.Pizza;
import com.github.komidawi.pccserver.data.PizzaRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class PizzaService {

    private final PizzaRepository repository;

    PizzaService(PizzaRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public List<Pizza> getAll() {
        return repository.findAll();
    }

    @Transactional
    public Optional<Pizza> getById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Pizza save(Pizza newPizza) {
        return repository.save(newPizza);
    }

    @Transactional
    public Long deleteById(Long id) {
        return repository.deletePizzaById(id);
    }

    @Transactional
    public void clear() {
        repository.deleteAll();
    }
}
