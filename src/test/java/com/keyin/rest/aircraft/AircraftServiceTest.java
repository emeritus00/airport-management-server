package com.keyin.rest.aircraft;

import org.junit.jupiter.api.BeforeEach;
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
public class AircraftServiceTest {

    @Mock
    private AircraftRepository aircraftRepository;

    @InjectMocks
    private AircraftService aircraftService;

    @BeforeEach
    void setUp() {
        // @InjectMocks will automatically inject the mocked repository into the service
    }

    @Test
    void testCreateAircraft() {
        Aircraft aircraft = new Aircraft();
        aircraft.setAirlineName("Delta");

        when(aircraftRepository.save(aircraft)).thenReturn(aircraft);

        Aircraft result = aircraftService.createAircraft(aircraft);

        assertEquals("Delta", result.getAirlineName());
        verify(aircraftRepository).save(aircraft);
    }

    @Test
    void testFindAllAircrafts() {
        List<Aircraft> aircrafts = List.of(new Aircraft(), new Aircraft());
        when(aircraftRepository.findAll()).thenReturn(aircrafts);

        List<Aircraft> result = aircraftService.findAllAircrafts();
        assertEquals(2, result.size());
    }

    @Test
    void testUpdateAircraft() {
        Aircraft existing = new Aircraft();
        existing.setId(1L);
        existing.setType("Boeing");

        Aircraft updated = new Aircraft();
        updated.setType("Airbus");

        when(aircraftRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(aircraftRepository.save(any())).thenReturn(updated);

        Aircraft result = aircraftService.updateAircraft(1L, updated);
        assertEquals("Airbus", result.getType());
    }

    @Test
    void testDeleteAircraft() {
        aircraftService.deleteAircraftById(5L);
        verify(aircraftRepository).deleteById(5L);
    }
}
