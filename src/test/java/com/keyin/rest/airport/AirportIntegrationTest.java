package com.keyin.rest.airport;

import com.keyin.rest.city.City;
import com.keyin.rest.city.CityRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class AirportIntegrationTest {

    @Autowired
    private AirportRepository airportRepository;

    @Autowired
    private CityRepository cityRepository;

    @Test
    public void testSaveAndFindAirport() {
        City city = new City();
        city.setName("Toronto");
        city = cityRepository.save(city);

        Airport airport = new Airport();
        airport.setName("Pearson International");
        airport.setCity(city);

        airport = airportRepository.save(airport);

        assertNotNull(airport.getId());
        assertEquals("Pearson International", airport.getName());
        assertEquals("Toronto", airport.getCity().getName());
    }

    @Test
    public void testUpdateAirport() {
        City city = new City();
        city.setName("Montreal");
        city = cityRepository.save(city);

        Airport airport = new Airport();
        airport.setName("Old Name");
        airport.setCity(city);
        airport = airportRepository.save(airport);

        airport.setName("New Name");
        Airport updatedAirport = airportRepository.save(airport);

        assertEquals("New Name", updatedAirport.getName());
    }

    @Test
    public void testDeleteAirport() {
        City city = new City();
        city.setName("Calgary");
        city = cityRepository.save(city);

        Airport airport = new Airport();
        airport.setName("Calgary Airport");
        airport.setCity(city);
        airport = airportRepository.save(airport);

        airportRepository.deleteById(airport.getId());
        assertFalse(airportRepository.findById(airport.getId()).isPresent());
    }

    @Test
    public void testFindAllAirports() {
        City city = new City();
        city.setName("Ottawa");
        city = cityRepository.save(city);

        Airport a1 = new Airport();
        a1.setName("Airport One");
        a1.setCity(city);

        Airport a2 = new Airport();
        a2.setName("Airport Two");
        a2.setCity(city);

        airportRepository.save(a1);
        airportRepository.save(a2);

        List<Airport> airports = (List<Airport>) airportRepository.findAll();
        assertTrue(airports.size() >= 2);
    }
}
