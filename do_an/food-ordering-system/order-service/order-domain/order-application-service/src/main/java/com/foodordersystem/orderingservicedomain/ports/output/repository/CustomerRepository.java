package com.foodordersystem.orderingservicedomain.ports.output.repository;

import com.foodordersystem.orderdomaincore.entity.Customer;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository {
    Optional<Customer> findCustomer(UUID customerId);
}
