package com.bcs.fakturierung.customer.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataCustomerRepository extends JpaRepository<JpaCustomerEntity, Long> {

    Optional<JpaCustomerEntity> findByKundennummer(String kundennummer);

    boolean existsByKundennummer(String kundennummer);
}
