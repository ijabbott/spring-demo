package com.pillar.demo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends MongoRepository<Customer, String> {
    public Optional<Customer> findByFirstName(String firstName);
    public List<Customer> findByLastName(String lastName);
}
