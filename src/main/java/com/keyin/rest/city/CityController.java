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

    @GetMapping("/cities")
    public List<City> getAllCities() {
        return cityService.findAllCities();
    }

    @GetMapping("/cities/{id}")
    public City getCityByID(@PathVariable long id) {
        return cityService.findCityById(id);
    }

    @GetMapping("/cities/name/{name}")
    public City getCityByName(@PathVariable String name) {
        return cityService.findCityByName(name);
    }

    @PostMapping("/city")
    public City createCity(@RequestBody City newCity) {
        return cityService.createCity(newCity);
    }

    @PutMapping("/cities/{id}")
    public City updateCity(@PathVariable long id, @RequestBody City updatedCity) {
        return cityService.updateCity(id, updatedCity);
    }

    @DeleteMapping("/cities/{id}")
    public void deleteCityById(@PathVariable long id) {
        cityService.deleteCityById(id);
    }
}