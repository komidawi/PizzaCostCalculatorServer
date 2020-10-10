package com.github.komidawi.pccserver;

import com.github.komidawi.pccserver.data.Pizza;
import com.github.komidawi.pccserver.service.PizzaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.github.komidawi.pccserver.data.TestPizzaFactory.createPizza;
import static com.github.komidawi.pccserver.rest.PizzaControllerConfig.BY_UUID_PATH;
import static com.github.komidawi.pccserver.rest.PizzaControllerConfig.ROOT_PATH;
import static com.github.komidawi.pccserver.util.JsonUtils.toJsonString;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class RestApiTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private PizzaService pizzaService;

    @BeforeEach
    public void setup() {
        pizzaService.clear();
    }

    @Test
    public void addPizzaEndpoint_addsPizza() throws Exception {
        // given
        Pizza pizza = createPizza();

        // expected
        mvc.perform(MockMvcRequestBuilders
                .post(ROOT_PATH)
                .contentType(APPLICATION_JSON)
                .content(toJsonString(pizza))
                .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(pizza.getName())))
                .andReturn();
    }

    @Test
    public void getPizzaEndpoint_returnsPizza() throws Exception {
        // given
        Pizza pizza = createPizza("test_get_pizza");
        pizzaService.save(pizza);

        // expected
        mvc.perform(MockMvcRequestBuilders
                .get(BY_UUID_PATH + "/" + pizza.getUuid())
                .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.uuid", is(pizza.getUuid().toString())))
                .andExpect(jsonPath("$.name", is(pizza.getName())))
                .andReturn();
    }

    @Test
    public void getAllPizzasEndpoint_returnsAllPizzas() throws Exception {
        // given
        Pizza pizza1 = createPizza();
        Pizza pizza2 = createPizza();
        pizzaService.save(pizza1);
        pizzaService.save(pizza2);

        // expected
        mvc.perform(MockMvcRequestBuilders
                .get(ROOT_PATH))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(2)))
                .andReturn();
    }

    @Test
    public void deletePizzaEndpoint_deletesExistingPizza() throws Exception {
        // given
        Pizza savedPizza = pizzaService.save(createPizza());

        // expected
        mvc.perform(MockMvcRequestBuilders
                .delete(BY_UUID_PATH + "/" + savedPizza.getUuid()))
                .andExpect(status().isOk());

        // and
        assertTrue(pizzaService.getAll().isEmpty());
    }

    @Test
    public void getNonExistingPizza_returns404() throws Exception {
        // expected
        mvc.perform(MockMvcRequestBuilders
                .get(ROOT_PATH + "/0"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void deleteNonExistingPizza_returns404() throws Exception {
        // expected
        mvc.perform(MockMvcRequestBuilders
                .delete(ROOT_PATH + "/0"))
                .andExpect(status().is4xxClientError());
    }

}
