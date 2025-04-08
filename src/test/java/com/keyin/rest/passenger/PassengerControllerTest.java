package com.keyin.rest.passenger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keyin.rest.city.City;
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

@WebMvcTest(PassengerController.class)
public class PassengerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PassengerService passengerService;

    private Passenger passenger;

    @BeforeEach
    void setUp() {
        passenger = new Passenger();
        passenger.setId(1L);
        passenger.setFirstName("Wola");
        passenger.setLastName("Gbadamosi");
        passenger.setPhoneNumber("1234567890");

        City city = new City();
        city.setId(1L);
        city.setName("Lagos");
        passenger.setCity(city);
    }

    @Test
    void getAllPassengers() throws Exception {
        Mockito.when(passengerService.findAllPassengers()).thenReturn(List.of(passenger));
        mockMvc.perform(get("/passengers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].lastName").value("Gbadamosi"));
    }

    @Test
    void getPassengerById() throws Exception {
        Mockito.when(passengerService.findPassengerById(1L)).thenReturn(passenger);
        mockMvc.perform(get("/passengers/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Wola"));
    }

    @Test
    void getPassengerByLastName() throws Exception {
        Mockito.when(passengerService.findPassengerByLastName("Gbadamosi")).thenReturn(passenger);
        mockMvc.perform(get("/passengers/passenger/Gbadamosi"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.phoneNumber").value("1234567890"));
    }

    @Test
    void createPassenger() throws Exception {
        Mockito.when(passengerService.createPassenger(any(Passenger.class))).thenReturn(passenger);
        mockMvc.perform(post("/passenger")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(passenger)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.lastName").value("Gbadamosi"));
    }

    @Test
    void updatePassenger() throws Exception {
        passenger.setPhoneNumber("0987654321");
        Mockito.when(passengerService.updatePassenger(any(Passenger.class))).thenReturn(passenger);
        mockMvc.perform(put("/passengers/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(passenger)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.phoneNumber").value("0987654321"));
    }

    @Test
    void deletePassengerById() throws Exception {
        mockMvc.perform(delete("/passengers/1"))
                .andExpect(status().isOk());
    }
}
