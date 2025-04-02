package com.keyin.rest.passenger;


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

    @GetMapping("/passengers/{id}")
    public Passenger getPassengerByID(@PathVariable long id) {
        return passengerService.findPassengerById(id);
    }


    @GetMapping("/passengers/passenger/{lastName}")
    public Passenger getPassengerByLastName(@PathVariable String lastName) {
        return passengerService.findPassengerByLastName(lastName);
    }

    @PostMapping("/passenger")
    public Passenger createPassenger(@RequestBody Passenger newPassenger) {
        return passengerService.createPassenger(newPassenger);
    }

    @PutMapping("/passengers/{id}")
    public Passenger updatePassenger(@PathVariable long id, @RequestBody Passenger updatedPassenger) {
        updatedPassenger.setId(id); // Set the ID from the path variable
        return passengerService.updatePassenger(updatedPassenger);
    }

    @DeleteMapping("/passengers/{id}")
    public void deletePasengerById(@PathVariable long id) {
        passengerService.deletePassengerById(id);
    }
}