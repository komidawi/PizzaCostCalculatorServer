package com.github.komidawi.pccserver.data;

import java.util.UUID;

public class TestPizzaFactory {

    public static Pizza createPizza() {
        return new Pizza(UUID.randomUUID().toString());
    }

}