package com.bcs.fakturierung.customer.domain;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository {

    List<Customer> findAll();

    Optional<Customer> findById(Long id);

    Optional<Customer> findByKundennummer(String kundennummer);

    Customer save(Customer customer);

    void deleteById(Long id);

    boolean existsByKundennummer(String kundennummer);

    int count();
}
