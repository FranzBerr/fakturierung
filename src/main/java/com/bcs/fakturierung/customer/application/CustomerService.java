package com.bcs.fakturierung.customer.application;

import com.bcs.fakturierung.customer.application.dto.CustomerDto;
import com.bcs.fakturierung.customer.domain.Customer;
import com.bcs.fakturierung.customer.domain.CustomerRepository;
import com.bcs.fakturierung.customer.infrastructure.mapper.CustomerMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CustomerService {

    private final CustomerRepository repo;
    private final CustomerMapper mapper;

    public CustomerService(CustomerRepository repo, CustomerMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    public List<CustomerDto> findAll() {
        return repo.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }

    public CustomerDto findById(Long id) {
        Customer c = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found: " + id));
        return mapper.toDto(c);
    }

    public CustomerDto create(CustomerDto dto) {
        if (repo.existsByKundennummer(dto.kundennummer())) {
            throw new RuntimeException("Kundennummer exists: " + dto.kundennummer());
        }
        Customer c = mapper.toDomain(dto);
        return mapper.toDto(repo.save(c));
    }

    public CustomerDto update(Long id, CustomerDto dto) {
        Customer existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found: " + id));

        existing.update(dto.firma(), dto.strasse(), dto.plz(), dto.ort());

        return mapper.toDto(repo.save(existing));
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
