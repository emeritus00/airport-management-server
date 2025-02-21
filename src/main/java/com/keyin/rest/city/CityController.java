package com.keyin.rest.city;

import com.keyin.rest.airport.Airport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
public class CityController {
    @Autowired
    private CityService cityService;

    @GetMapping("/city")
    public List<City> getAllCities() {
        return cityService.findAllCities();
    }

    @GetMapping("/city/{id}")
    public City getCityByID(@PathVariable long id) {
        return cityService.findCityById(id);
    }

    @GetMapping("/city/name/{name}")
    public City getCityByName(@PathVariable String name) {
        return cityService.findCityByName(name);
    }

    @PostMapping("/city")
    public City createCity(@RequestBody City newCity) {
        return cityService.createCity(newCity);
    }

    @PutMapping("/city/{id}")
    public City updateCity(@PathVariable long id, @RequestBody City updatedCity) {
        return cityService.updateCity(id, updatedCity);
    }

    @DeleteMapping("/city/{id}")
    public void deleteCityById(@PathVariable long id) {
        cityService.deleteCityById(id);
    }
}