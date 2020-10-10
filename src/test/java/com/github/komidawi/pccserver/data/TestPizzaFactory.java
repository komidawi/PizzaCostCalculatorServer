package com.github.komidawi.pccserver.data;

public class TestPizzaFactory {

    public static Pizza createPizza() {
        return new Pizza();
    }

    public static Pizza createPizza(String name) {
        Pizza pizza = createPizza();
        pizza.setName(name);
        return pizza;
    }

}