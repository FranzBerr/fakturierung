package com.bcs.fakturierung.customer.domain;

import java.util.Objects;

public class Customer {
    private Long id;
    private String kundennummer;
    private String firma;
    private String strasse;
    private String plz;
    private String ort;

    public Customer(Long id, String kundennummer, String firma, String strasse, String plz, String ort) {
        this.id = id;
        this.kundennummer = kundennummer;
        this.firma = firma;
        this.strasse = strasse;
        this.plz = plz;
        this.ort = ort;
    }

    // Getter
    public Long getId() { return id; }
    public String getKundennummer() { return kundennummer; }
    public String getFirma() { return firma; }
    public String getStrasse() { return strasse; }
    public String getPlz() { return plz; }
    public String getOrt() { return ort; }

    // Update
    public void update(String firma, String strasse, String plz, String ort) {
        this.firma = firma;
        this.strasse = strasse;
        this.plz = plz;
        this.ort = ort;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer c = (Customer) o;
        return Objects.equals(id, c.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
