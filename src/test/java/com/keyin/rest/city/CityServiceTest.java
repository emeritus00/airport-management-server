package com.keyin.rest.city;

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
public class CityServiceTest {

    @Mock
    private CityRepository cityRepository;

    @InjectMocks
    private CityService cityService;

    @Test
    void testCreateCity() {
        City city = new City();
        city.setName("New York");

        when(cityRepository.save(city)).thenReturn(city);

        City result = cityService.createCity(city);

        assertEquals("New York", result.getName());
        verify(cityRepository).save(city);
    }

    @Test
    void testFindAllCities() {
        List<City> cities = List.of(new City(), new City());
        when(cityRepository.findAll()).thenReturn(cities);

        List<City> result = cityService.findAllCities();
        assertEquals(2, result.size());
    }

    @Test
    void testUpdateCity() {
        City existing = new City();
        existing.setId(1L);
        existing.setName("Los Angeles");

        City updated = new City();
        updated.setName("Chicago");

        when(cityRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(cityRepository.save(any())).thenReturn(updated);

        City result = cityService.updateCity(1L, updated);
        assertEquals("Chicago", result.getName());
    }

    @Test
    void testDeleteCity() {
        cityService.deleteCityById(5L);
        verify(cityRepository).deleteById(5L);
    }
}
