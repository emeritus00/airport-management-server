package com.keyin.rest.city;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CityController.class)
public class CityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CityService cityService;

    private City city;

    @BeforeEach
    void setUp() {
        city = new City();
        city.setId(1L);
        city.setName("Toronto");
        city.setState("ON");
        city.setPopulation(6000000);
    }

    @Test
    void getAllCities() throws Exception {
        Mockito.when(cityService.findAllCities()).thenReturn(List.of(city));
        mockMvc.perform(get("/cities"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Toronto"));
    }

    @Test
    void getCityById() throws Exception {
        Mockito.when(cityService.findCityById(1L)).thenReturn(city);
        mockMvc.perform(get("/cities/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.state").value("ON"));
    }

    @Test
    void getCityByName() throws Exception {
        Mockito.when(cityService.findCityByName("Toronto")).thenReturn(city);
        mockMvc.perform(get("/cities/name/Toronto"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.population").value(6000000));
    }

    @Test
    void createCity() throws Exception {
        Mockito.when(cityService.createCity(any(City.class))).thenReturn(city);
        mockMvc.perform(post("/city")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(city)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Toronto"));
    }

    @Test
    void updateCity() throws Exception {
        city.setPopulation(6500000);
        Mockito.when(cityService.updateCity(Mockito.eq(1L), any(City.class))).thenReturn(city);
        mockMvc.perform(put("/cities/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(city)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.population").value(6500000));
    }

    @Test
    void deleteCityById() throws Exception {
        mockMvc.perform(delete("/cities/1"))
                .andExpect(status().isOk());
    }
}
