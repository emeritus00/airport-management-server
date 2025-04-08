package com.keyin.rest.aircraft;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class AircraftIntegrationTest {

    @Autowired
    private AircraftRepository aircraftRepository;

    @Test
    public void testSaveAndFindAircraft() {
        Aircraft aircraft = new Aircraft();
        aircraft.setAirlineName("Air Canada");
        aircraft.setType("Boeing 737");

        aircraft = aircraftRepository.save(aircraft);

        Aircraft found = aircraftRepository.findByAirlineName("Air Canada");

        assertNotNull(found);
        assertNotNull(found.getId());
        assertEquals("Air Canada", found.getAirlineName());
        assertEquals("Boeing 737", found.getType());
    }

    @Test
    public void testUpdateAircraft() {
        Aircraft aircraft = new Aircraft();
        aircraft.setAirlineName("WestJet");
        aircraft.setType("Boeing 737");

        aircraft = aircraftRepository.save(aircraft);
        aircraft.setType("Airbus A320");

        Aircraft updated = aircraftRepository.save(aircraft);

        assertEquals("Airbus A320", updated.getType());
    }

    @Test
    public void testDeleteAircraft() {
        Aircraft aircraft = new Aircraft();
        aircraft.setAirlineName("Flair Airlines");
        aircraft.setType("737 MAX");

        aircraft = aircraftRepository.save(aircraft);
        Long id = aircraft.getId();

        aircraftRepository.deleteById(id);

        assertFalse(aircraftRepository.findById(id).isPresent());
    }

    @Test
    public void testFindAllAircraft() {
        Aircraft a1 = new Aircraft();
        a1.setAirlineName("Sunwing");
        a1.setType("Boeing 737");

        Aircraft a2 = new Aircraft();
        a2.setAirlineName("Porter");
        a2.setType("Embraer E195");

        aircraftRepository.save(a1);
        aircraftRepository.save(a2);

        Iterable<Aircraft> aircraftList = aircraftRepository.findAll();
        assertTrue(aircraftList.iterator().hasNext());
    }
}
