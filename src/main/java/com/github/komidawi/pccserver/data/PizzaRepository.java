package com.github.komidawi.pccserver.data;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PizzaRepository extends JpaRepository<Pizza, Long> {

    Long deletePizzaById(Long id);

    Long deletePizzaByUuid(UUID uuid);

    Optional<Pizza> findByUuid(UUID uuid);

}
