package com.bcs.fakturierung.customer.infrastructure.persistence;

import com.bcs.fakturierung.customer.domain.Customer;
import com.bcs.fakturierung.customer.domain.CustomerRepository;
import com.bcs.fakturierung.customer.infrastructure.mapper.CustomerMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JpaCustomerRepository implements CustomerRepository {

    private final SpringDataCustomerRepository jpa;
    private final CustomerMapper mapper;

    public JpaCustomerRepository(SpringDataCustomerRepository jpa, CustomerMapper mapper) {
        this.jpa = jpa;
        this.mapper = mapper;
    }

    @Override
    public List<Customer> findAll() {
        return jpa.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return jpa.findById(id).map(mapper::toDomain);
    }

    @Override
    public Optional<Customer> findByKundennummer(String kundennummer) {
        return jpa.findByKundennummer(kundennummer).map(mapper::toDomain);
    }

    @Override
    public Customer save(Customer customer) {
        JpaCustomerEntity entity = mapper.toEntity(customer);
        return mapper.toDomain(jpa.save(entity));
    }

    @Override
    public void deleteById(Long id) {
        jpa.deleteById(id);
    }

    @Override
    public boolean existsByKundennummer(String kundennummer) {
        return jpa.existsByKundennummer(kundennummer);
    }

    @Override
    public int count() {
        return (int) jpa.count();
    }
}

