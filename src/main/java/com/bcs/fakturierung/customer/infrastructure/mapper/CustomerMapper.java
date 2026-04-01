package com.bcs.fakturierung.customer.infrastructure.mapper;

import com.bcs.fakturierung.customer.application.dto.CustomerDto;
import com.bcs.fakturierung.customer.domain.Customer;
import com.bcs.fakturierung.customer.infrastructure.persistence.JpaCustomerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    // Domain <-> DTO
    CustomerDto toDto(Customer domain);

    Customer toDomain(CustomerDto dto);

    // Domain <-> JPA
    JpaCustomerEntity toEntity(Customer domain);

    Customer toDomain(JpaCustomerEntity entity);
}
