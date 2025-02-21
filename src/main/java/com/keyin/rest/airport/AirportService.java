package com.keyin.rest.airport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AirportService {
    @Autowired
    private AirportRepository airportRepository;

    public List<Airport> findAllAirports() {
        return (List<Airport>) airportRepository.findAll();
    }

    public Airport findAirportById(long id) {
        Optional<Airport> airportOptional = airportRepository.findById(id);

        return airportOptional.orElse(null);
    }

    public Airport createAirport(Airport newAirport) {
        return airportRepository.save(newAirport);
    }

    public Airport findAirportByName(String name) {
        return airportRepository.findByName(name);
    }

    public void deleteAirportById(long id) {
        airportRepository.deleteById(id);
    }

    public Airport updateAirport(long id, Airport updatedAirport) {
        Optional<Airport> airportOptional = airportRepository.findById(id);

        if (airportOptional.isPresent()) {
            Airport airportToUpdate = airportOptional.get();
            airportToUpdate.setCity(updatedAirport.getCity());
            airportToUpdate.setCode(updatedAirport.getCode());
            airportToUpdate.setName(updatedAirport.getName());

            return airportRepository.save(airportToUpdate);
        }

        return null;
    }
}
