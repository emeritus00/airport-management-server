package com.keyin.rest.city;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CityService {
    @Autowired
    private CityRepository cityRepository;

    public List<City> findAllCities() {
        return (List<City>) cityRepository.findAll();
    }

    public City findCityById(long id) {
        Optional<City> cityOptional = cityRepository.findById(id);

        return cityOptional.orElse(null);
    }

    public City createCity(City newCity) {
        return cityRepository.save(newCity);
    }

    public City findCityByName(String name) {
        return cityRepository.findByCityName(name);
    }

    public void deleteCityById(long id) {
        cityRepository.deleteById(id);
    }

    public City updateCity(City updatedCity) {
        City cityToUpdate = findCityById(updatedCity.getId());

        if (cityToUpdate != null) {
            cityToUpdate.setName(updatedCity.getName());
            cityToUpdate.setAirports(updatedCity.getAirports());
            cityToUpdate.setState(updatedCity.getState());
            cityToUpdate.setPopulation(updatedCity.getPopulation());

            cityRepository.save(cityToUpdate);
        }
        return cityToUpdate;
    }
}