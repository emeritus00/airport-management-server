package com.keyin.rest.aircraft;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AircraftController.class)
class AircraftControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AircraftService aircraftService;

    @Test
    void testGetAllAircrafts() throws Exception {
        Aircraft aircraft = new Aircraft();
        aircraft.setId(1L);
        aircraft.setAirlineName("Delta");
        aircraft.setType("Boeing 737");

        Mockito.when(aircraftService.findAllAircrafts()).thenReturn(List.of(aircraft));

        mockMvc.perform(get("/aircrafts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].airlineName").value("Delta"));
    }

    @Test
    void testGetAircraftById() throws Exception {
        Aircraft aircraft = new Aircraft();
        aircraft.setId(1L);
        aircraft.setAirlineName("Air Canada");

        Mockito.when(aircraftService.findAircraftById(1L)).thenReturn(aircraft);

        mockMvc.perform(get("/aircrafts/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.airlineName").value("Air Canada"));
    }
}
