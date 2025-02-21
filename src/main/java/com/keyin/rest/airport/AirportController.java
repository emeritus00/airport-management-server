package com.keyin.rest.airport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class AirportController {
    @Autowired
    private AirportService airportService;

    @GetMapping("/airports")
    public List<Airport> getAllAirports() {
        return airportService.findAllAirports();
    }

    @GetMapping("/airport/{id}")
    public Airport getAirportByID(@PathVariable long id) {
        return airportService.findAirportById(id);
    }

    @GetMapping("/airport/{name}")
    public Airport getAirportByName(@PathVariable String name) {
        return airportService.findAirportByName(name);
    }

    @PostMapping("/airport")
    public Airport createAirport(@RequestBody Airport newAirport) {
        return airportService.createAirport(newAirport);
    }

    @PutMapping("/airport/{id}")
    public Airport updateAirport(@RequestBody Airport updatedAirport) {
        return airportService.updateAirport(updatedAirport);
    }

    @DeleteMapping("/airport/{id}")
    public void deleteDivisionById(@PathVariable long id) {
        airportService.deleteAirportById(id);
    }
}
