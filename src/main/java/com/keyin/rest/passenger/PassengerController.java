package com.keyin.rest.passenger;

import com.keyin.rest.city.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin
public class PassengerController {
    @Autowired
    private PassengerService passengerService;

    @GetMapping("/passengers")
    public List<Passenger> getAllPassengers() {
        return passengerService.findAllPassengers();
    }

    @GetMapping("/passenger/{id}")
    public Passenger getPassengerByID(@PathVariable long id) {
        return passengerService.findPassengerById(id);
    }

    @PostMapping("/passenger")
    public Passenger createPassenger(@RequestBody Passenger newPassenger) {
        return passengerService.createPassenger(newPassenger);
    }

    @PutMapping("/passenger/{id}")
    public Passenger updatePassenger(@RequestBody Passenger updatedPassenger) {
        return passengerService.updatePassenger(updatedPassenger);
    }

    @GetMapping("/passenger/{firstName}")
    public Passenger getPassengerByFirstName(@PathVariable String firstName) {
        return passengerService.findPassengerByFirstName(firstName);
    }

    @GetMapping("/passenger/{lastName}")
    public Passenger getPassengerByLastName(@PathVariable String lastName) {
        return passengerService.findPassengerByLastName(lastName);
    }

    @DeleteMapping("/passenger/{id}")
    public void deletePasengerById(@PathVariable long id) {
        passengerService.deletePassengerById(id);
    }
}