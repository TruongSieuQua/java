package com.foodorderingsystem.orderservice.domain.applicationservice.ports.output.repository;

import com.foodorderingsystem.orderservice.domain.core.entity.Customer;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository {
    Optional<Customer> findCustomer(UUID customerId);
}
