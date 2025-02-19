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

    @DeleteMapping("/passenger/{id}")
    public void deletePasengerById(@PathVariable long id) {
        passengerService.deletePassengerById(id);
    }
}