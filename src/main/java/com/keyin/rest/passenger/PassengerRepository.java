package com.keyin.rest.passenger;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassengerRepository extends CrudRepository<Passenger, Long> {
    public Passenger findByPassengerName(String name);
}
