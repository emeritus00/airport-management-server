package com.keyin.rest.passenger;

import com.keyin.rest.city.City;
import org.junit.jupiter.api.BeforeEach;
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
        // Create a City object (you will need to set it in both existing and updated passengers)

        City existingCity = new City();
        existingCity.setName("New York");

        City updatedCity = new City();
        updatedCity.setName("Los Angeles");

        // Create the existing passenger (this is the one to be updated)
        Passenger existing = new Passenger();
        existing.setId(1L);  // The ID should match the one you're passing in the test
        existing.setFirstName("Jane");
        existing.setLastName("Doe");
        existing.setPhoneNumber("1234567890");
        existing.setCity(existingCity);  // Assign the City object to existing passenger

        // Create the updated passenger details
        Passenger updated = new Passenger();
        updated.setId(1L);  // Ensure that the ID is set
        updated.setFirstName("Alice");
        updated.setLastName("Smith");
        updated.setPhoneNumber("0987654321");
        updated.setCity(updatedCity);  // Assign the updated City object

        // Mock the repository methods
        when(passengerRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(passengerRepository.save(any(Passenger.class))).thenReturn(existing);

        // Call the service method
        Passenger result = passengerService.updatePassenger(updated);

        // Verify the result
        assertEquals("Alice", result.getFirstName());
        assertEquals("Smith", result.getLastName());
        assertEquals("0987654321", result.getPhoneNumber());
        assertEquals("Los Angeles", result.getCity().getName());  // Ensure the city is correctly updated
        verify(passengerRepository).save(existing);  // Ensure save is called
    }

    @Test
    void testDeletePassenger() {
        passengerService.deletePassengerById(5L);
        verify(passengerRepository).deleteById(5L);
    }
}
