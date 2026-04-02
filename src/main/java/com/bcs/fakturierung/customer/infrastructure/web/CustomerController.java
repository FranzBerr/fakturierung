package com.bcs.fakturierung.customer.infrastructure.web;

import com.bcs.fakturierung.customer.application.CustomerService;
import com.bcs.fakturierung.customer.application.dto.CustomerDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Swagger: http://localhost:8080/swagger-ui/index.html
//OpenAPI JSON/YAML:  http://localhost:8080/v3/api-docs
//OpenAPI JSON/YAML:  http://localhost:8080/v3/api-docs.yaml

//http://localhost:8080/api/customers

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @GetMapping
    public List<CustomerDto> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public CustomerDto getById(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @PostMapping
    public CustomerDto create(@RequestBody CustomerDto dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public CustomerDto update(@PathVariable Long id, @RequestBody CustomerDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
