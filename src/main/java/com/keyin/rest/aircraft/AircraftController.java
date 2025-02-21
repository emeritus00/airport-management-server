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

    @GetMapping("aircrafts/{id}")
    public Aircraft getAircraftByID(@PathVariable long id) {
        return aircraftService.findAircraftById(id);
    }

    @GetMapping("/aircrafts/aircraft/{airlineName}")
    public Aircraft getAircraftByName(@PathVariable String airlineName) {
        return aircraftService.findAircraftByAirlineName(airlineName);
    }

    @PostMapping("/aircraft")
    public Aircraft createAircraft(@RequestBody Aircraft newAircraft) {
        return aircraftService.createAircraft(newAircraft);
    }

    @PutMapping("aircrafts/{id}")
    public Aircraft updateAircraft(@PathVariable long id, @RequestBody Aircraft updatedAircraft) {
        return aircraftService.updateAircraft(id, updatedAircraft);
    }

    @DeleteMapping("/aircrafts/{id}")
    public void deleteAircraftById(@PathVariable long id) {
        aircraftService.deleteAircraftById(id);
    }
}

