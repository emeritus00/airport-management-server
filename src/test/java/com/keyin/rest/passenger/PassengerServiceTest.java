package com.keyin.rest.passenger;

import com.keyin.rest.city.City;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PassengerServiceTest {

    @Mock
    private PassengerRepository passengerRepository;

    @InjectMocks
    private PassengerService passengerService;

    @Test
    void testCreatePassenger() {
        Passenger passenger = new Passenger();
        passenger.setFirstName("John");
        passenger.setLastName("Doe");

        when(passengerRepository.save(passenger)).thenReturn(passenger);

        Passenger result = passengerService.createPassenger(passenger);

        assertEquals("John", result.getFirstName());
        verify(passengerRepository).save(passenger);
    }

    @Test
    void testFindAllPassengers() {
        List<Passenger> passengers = List.of(new Passenger(), new Passenger());
        when(passengerRepository.findAll()).thenReturn(passengers);

        List<Passenger> result = passengerService.findAllPassengers();
        assertEquals(2, result.size());
    }

    @Test
    void testUpdatePassenger() {

        City existingCity = new City();
        existingCity.setName("New York");

        City updatedCity = new City();
        updatedCity.setName("Los Angeles");

        Passenger existing = new Passenger();
        existing.setId(1L);
        existing.setFirstName("Jane");
        existing.setLastName("Doe");
        existing.setPhoneNumber("1234567890");
        existing.setCity(existingCity);

        Passenger updated = new Passenger();
        updated.setId(1L);
        updated.setFirstName("Alice");
        updated.setLastName("Smith");
        updated.setPhoneNumber("0987654321");
        updated.setCity(updatedCity);

        when(passengerRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(passengerRepository.save(any(Passenger.class))).thenReturn(existing);

        Passenger result = passengerService.updatePassenger(updated);

        assertEquals("Alice", result.getFirstName());
        assertEquals("Smith", result.getLastName());
        assertEquals("0987654321", result.getPhoneNumber());
        assertEquals("Los Angeles", result.getCity().getName());
        verify(passengerRepository).save(existing);
    }

    @Test
    void testDeletePassenger() {
        passengerService.deletePassengerById(5L);
        verify(passengerRepository).deleteById(5L);
    }
}
