package com.keyin.rest.airport;

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

@WebMvcTest(AirportController.class)
public class AirportControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AirportService airportService;

    private Airport airport;

    @BeforeEach
    void setUp() {
        airport = new Airport();
        airport.setId(1L);
        airport.setName("Pearson");
        airport.setCode("YYZ");
    }

    @Test
    void getAllAirports() throws Exception {
        Mockito.when(airportService.findAllAirports()).thenReturn(List.of(airport));
        mockMvc.perform(get("/airports"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Pearson"));
    }

    @Test
    void getAirportByID() throws Exception {
        Mockito.when(airportService.findAirportById(1L)).thenReturn(airport);
        mockMvc.perform(get("/airports/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("YYZ"));
    }

    @Test
    void getAirportByName() throws Exception {
        Mockito.when(airportService.findAirportByName("Pearson")).thenReturn(airport);
        mockMvc.perform(get("/airports/airport/Pearson"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void createAirport() throws Exception {
        Mockito.when(airportService.createAirport(any(Airport.class))).thenReturn(airport);
        mockMvc.perform(post("/airport")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(airport)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("YYZ"));
    }

    @Test
    void updateAirport() throws Exception {
        airport.setCode("YUL");
        Mockito.when(airportService.updateAirport(Mockito.eq(1L), any(Airport.class))).thenReturn(airport);
        mockMvc.perform(put("/airports/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(airport)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("YUL"));
    }

    @Test
    void deleteAirportById() throws Exception {
        mockMvc.perform(delete("/airports/1"))
                .andExpect(status().isOk());
    }
}
