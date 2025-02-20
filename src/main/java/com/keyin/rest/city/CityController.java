package com.keyin.rest.city;

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

    @GetMapping("/city/{id}")
    public City getCityByID(@PathVariable long id) {
        return cityService.findCityById(id);
    }

    @PostMapping("/city")
    public City createCity(@RequestBody City newCity) {
        return cityService.createCity(newCity);
    }

    @PutMapping("/city/{id}")
    public City updateCity(@RequestBody City updatedCity) {
        return cityService.updateCity(updatedCity);
    }

    @DeleteMapping("/city/{id}")
    public void deleteCityById(@PathVariable long id) {
        cityService.deleteCityById(id);
    }
}