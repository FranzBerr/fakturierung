package com.bcs.fakturierung.customer.application.dto;

public record CustomerDto(
        Long id,
        String kundennummer,
        String firma,
        String strasse,
        String plz,
        String ort
) {}