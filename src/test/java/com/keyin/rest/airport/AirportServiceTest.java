package com.keyin.rest.airport;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AirportServiceTest {

    @Mock
    private AirportRepository airportRepository;

    @InjectMocks
    private AirportService airportService;

    @Test
    void testCreateAirport() {
        Airport airport = new Airport();
        airport.setName("JFK");

        when(airportRepository.save(airport)).thenReturn(airport);

        Airport result = airportService.createAirport(airport);

        assertEquals("JFK", result.getName());
        verify(airportRepository).save(airport);
    }

    @Test
    void testFindAllAirports() {
        List<Airport> airports = List.of(new Airport(), new Airport());
        when(airportRepository.findAll()).thenReturn(airports);

        List<Airport> result = airportService.findAllAirports();
        assertEquals(2, result.size());
    }

    @Test
    void testUpdateAirport() {
        Airport existing = new Airport();
        existing.setId(1L);
        existing.setName("LAX");

        Airport updated = new Airport();
        updated.setName("SFO");

        when(airportRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(airportRepository.save(any())).thenReturn(updated);

        Airport result = airportService.updateAirport(1L, updated);
        assertEquals("SFO", result.getName());
    }

    @Test
    void testDeleteAirport() {
        airportService.deleteAirportById(5L);
        verify(airportRepository).deleteById(5L);
    }
}
