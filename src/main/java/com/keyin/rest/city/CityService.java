package com.keyin.rest.city;

import com.keyin.rest.airport.Airport;
import com.keyin.rest.airport.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CityService {
    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private AirportRepository airportRepository;

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
        return cityRepository.findByName(name);
    }

    public void deleteCityById(long id) {
        cityRepository.deleteById(id);
    }

    public City updateCity(long id, City updatedCity) {
        Optional<City> cityOptional = cityRepository.findById(id);

        if (cityOptional.isPresent()) {
            City cityToUpdate = cityOptional.get();
            cityToUpdate.setName(updatedCity.getName());
            cityToUpdate.setState(updatedCity.getState());
            cityToUpdate.setPopulation(updatedCity.getPopulation());
            cityToUpdate.setAirports(updatedCity.getAirports());

            // Update the airports
            if (updatedCity.getAirports() != null) {
                for (Airport airport : updatedCity.getAirports()) {
                    airport.setCity(cityToUpdate);
                    airportRepository.save(airport);
                }
                cityToUpdate.setAirports(updatedCity.getAirports());
            }

            return cityRepository.save(cityToUpdate);
        }
        return null;
    }
}