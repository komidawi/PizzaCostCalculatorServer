package com.github.komidawi.pccserver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.komidawi.pccserver.data.Pizza;
import com.github.komidawi.pccserver.service.PizzaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class RestApiTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private PizzaService pizzaService;

    @Test
    public void addPizzaEndpoint_addsPizza() throws Exception {
        // given
        Pizza pizza = new Pizza("test_pizza_name");

        // expected
        mvc.perform(MockMvcRequestBuilders
                .post("/pizza")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJsonString(pizza))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(pizza.getName())));
    }

    @Test
    public void getPizzaEndpoint_returnsExistingPizza() throws Exception {
        // given
        Pizza pizza = new Pizza("test_pizza_name");
        pizzaService.save(pizza);

        // expected
        mvc.perform(MockMvcRequestBuilders
                .get("/pizza/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(pizza.getName())));
    }

    private static String toJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
