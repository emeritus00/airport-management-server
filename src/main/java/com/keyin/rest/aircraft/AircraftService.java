package com.keyin.rest.aircraft;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AircraftService {
    @Autowired
    private AircraftRepository aircraftRepository;

    public List<Aircraft> findAllAircrafts() {
        return (List<Aircraft>) aircraftRepository.findAll();
    }

    public Aircraft findAircraftById(long id) {
        Optional<Aircraft> aircraftOptional = aircraftRepository.findById(id);
        return aircraftOptional.orElse(null);
    }

    public Aircraft createAircraft(Aircraft newAircraft) {
        return aircraftRepository.save(newAircraft);
    }

    public Aircraft findAircraftByAirlineName(String airlineName) {
        return aircraftRepository.findByAirlineName(airlineName);
    }

    public void deleteAircraftById(long id) {
        aircraftRepository.deleteById(id);
    }

    public Aircraft updateAircraft(long id, Aircraft updatedAircraft) {
        Optional<Aircraft> aircraftOptional = aircraftRepository.findById(id);

        if (aircraftOptional.isPresent()) {
            Aircraft aircraftToUpdate = aircraftOptional.get();
            aircraftToUpdate.setAirlineName(updatedAircraft.getAirlineName());
            aircraftToUpdate.setType(updatedAircraft.getType());
            aircraftToUpdate.setNumberOfPassengers(updatedAircraft.getNumberOfPassengers());

            return aircraftRepository.save(aircraftToUpdate);
        }

        return null;
    }


}
