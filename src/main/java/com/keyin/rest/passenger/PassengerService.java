package com.keyin.rest.passenger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PassengerService {
    @Autowired
    private PassengerRepository passengerRepository;

    public List<Passenger> findAllPassengers() {
        return (List<Passenger>) passengerRepository.findAll();
    }

    public Passenger findPassengerById(long id) {
        Optional<Passenger> passengerOptional = passengerRepository.findById(id);

        return passengerOptional.orElse(null);
    }

    public Passenger createPassenger(Passenger newPassenger) {
        return passengerRepository.save(newPassenger);
    }

    public Passenger findPassengerByName(String name) {
        return passengerRepository.findByPassengerName(name);
    }

    public Passenger updatePassenger(Passenger updatedPassenger) {
        Passenger passengerToUpdate = findPassengerById(updatedPassenger.getId());

        if (passengerToUpdate != null) {
            passengerToUpdate.setFirstName(updatedPassenger.getFirstName());
            passengerToUpdate.setLastName(updatedPassenger.getLastName());
            passengerToUpdate.setPhoneNumber(updatedPassenger.getPhoneNumber());
            passengerToUpdate.setCity(updatedPassenger.getCity());
            passengerToUpdate.setAircraft(updatedPassenger.getAircraft());


            passengerRepository.save(passengerToUpdate);
        }
        return passengerToUpdate;
    }
}

