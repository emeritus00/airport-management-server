package com.keyin.rest.city;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class CityIntegrationTest {

    @Autowired
    private CityRepository cityRepository;

    @Test
    public void testSaveAndFindCity() {
        City city = new City();
        city.setName("St. John's");

        city = cityRepository.save(city);

        assertNotNull(city.getId());
        assertEquals("St. John's", city.getName());
    }

    @Test
    public void testUpdateCity() {
        City city = new City();
        city.setName("Old Name");
        city = cityRepository.save(city);

        city.setName("New Name");
        City updatedCity = cityRepository.save(city);

        assertEquals("New Name", updatedCity.getName());
    }

    @Test
    public void testDeleteCity() {
        City city = new City();
        city.setName("ToDelete");
        city = cityRepository.save(city);

        cityRepository.delete(city);
        Optional<City> deletedCity = cityRepository.findById(city.getId());

        assertTrue(deletedCity.isEmpty());
    }

    @Test
    public void testFindAllCities() {
        City city1 = new City();
        city1.setName("City One");
        cityRepository.save(city1);

        City city2 = new City();
        city2.setName("City Two");
        cityRepository.save(city2);

        List<City> cities = (List<City>) cityRepository.findAll();
        assertTrue(cities.size() >= 2);
    }
}