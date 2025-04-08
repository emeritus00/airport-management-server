package com.keyin.rest.passenger;

import com.keyin.rest.aircraft.Aircraft;
import com.keyin.rest.aircraft.AircraftRepository;
import com.keyin.rest.city.City;
import com.keyin.rest.city.CityRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class PassengerIntegrationTest {

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private AircraftRepository aircraftRepository;

    @Test
    public void testPassengerAircraftRelationship() {
        Aircraft aircraft = new Aircraft();
        aircraft.setType("Boeing 747");
        aircraft.setAirlineName("Test Airlines");
        aircraft.setNumberOfPassengers(300);
        aircraft = aircraftRepository.save(aircraft);

        Passenger passenger = new Passenger();
        passenger.setFirstName("John");
        passenger.setLastName("Doe");
        passenger.setAircraft(List.of(aircraft));

        Passenger savedPassenger = passengerRepository.save(passenger);

        assertNotNull(savedPassenger.getAircraft());
        assertEquals(1, savedPassenger.getAircraft().size());
        assertEquals("Test Airlines", savedPassenger.getAircraft().get(0).getAirlineName());
    }

    @Test
    public void testSaveAndFindPassenger() {
        Passenger passenger = new Passenger();
        passenger.setFirstName("Alice");
        passenger.setLastName("Smith");
        passenger.setPhoneNumber("987-654-3210");

        passenger = passengerRepository.save(passenger);

        Optional<Passenger> found = passengerRepository.findById(passenger.getId());
        assertTrue(found.isPresent());
        assertEquals("Alice", found.get().getFirstName());
    }

    @Test
    public void testUpdatePassenger() {
        Passenger passenger = new Passenger();
        passenger.setFirstName("Mark");
        passenger.setLastName("Brown");
        passenger.setPhoneNumber("444-555-6666");

        passenger = passengerRepository.save(passenger);

        passenger.setFirstName("Marcus");
        passenger.setPhoneNumber("111-222-3333");
        Passenger updated = passengerRepository.save(passenger);

        assertEquals("Marcus", updated.getFirstName());
        assertEquals("111-222-3333", updated.getPhoneNumber());
    }

    @Test
    public void testDeletePassenger() {
        Passenger passenger = new Passenger();
        passenger.setFirstName("Lena");
        passenger.setLastName("Hill");

        passenger = passengerRepository.save(passenger);
        Long id = passenger.getId();

        passengerRepository.deleteById(id);

        Optional<Passenger> found = passengerRepository.findById(id);
        assertFalse(found.isPresent());
    }

    @Test
    public void testFindAllPassengers() {
        Passenger p1 = new Passenger();
        p1.setFirstName("Anna");
        p1.setLastName("Lee");

        Passenger p2 = new Passenger();
        p2.setFirstName("Brian");
        p2.setLastName("Fox");

        passengerRepository.save(p1);
        passengerRepository.save(p2);

        Iterable<Passenger> passengersIterable = passengerRepository.findAll();
        List<Passenger> passengers = new ArrayList<>();
        passengersIterable.forEach(passengers::add);
        assertTrue(passengers.size() >= 2);
    }

    @Test
    public void testPassengerWithCity() {
        City city = new City();
        city.setName("Montreal");
        city = cityRepository.save(city);

        Passenger passenger = new Passenger();
        passenger.setFirstName("Leo");
        passenger.setCity(city);

        Passenger saved = passengerRepository.save(passenger);
        assertNotNull(saved.getCity());
        assertEquals("Montreal", saved.getCity().getName());
    }
}