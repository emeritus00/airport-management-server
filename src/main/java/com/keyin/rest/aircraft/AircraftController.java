package com.keyin.rest.aircraft;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class AircraftController {
    @Autowired
    private AircraftService aircraftService;

    @GetMapping("/aircrafts")
    public List<Aircraft> getAllAircrafts() {
        return aircraftService.findAllAircrafts();
    }

    @GetMapping("aircrafts/aircraft/{id}")
    public Aircraft getAircraftByID(@PathVariable long id) {
        return aircraftService.findAircraftById(id);
    }

    @PostMapping("/aircraft")
    public Aircraft createAircraft(@RequestBody Aircraft newAircraft) {
        return aircraftService.createAircraft(newAircraft);
    }

    @PutMapping("aircrafts/{id}")
    public Aircraft updateAircraft(@PathVariable long id, @RequestBody Aircraft updatedAircraft) {
        return aircraftService.updateAircraft(id, updatedAircraft);
    }

    @GetMapping("/aircrafts/name/{airlineName}")
    public Aircraft getAircraftByName(@PathVariable String airlineName) {
        return aircraftService.findAircraftByAirlineName(airlineName);
    }

    @DeleteMapping("/aircrafts/{id}")
    public void deleteAircraftById(@PathVariable long id) {
        aircraftService.deleteAircraftById(id);
    }
}

