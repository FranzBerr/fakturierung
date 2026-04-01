package com.bcs.fakturierung.customer.infrastructure.persistence;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
//@Getter @Setter

@Table(name = "customer")
public class JpaCustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private Long version;

    private String kundennummer;
    private String firma;
    private String strasse;
    private String plz;
    private String ort;

    // Constructors
    public JpaCustomerEntity() {}

    public JpaCustomerEntity(
            Long id,
            String kundennummer,
            String firma,
            String strasse,
            String plz,
            String ort
    ) {
        this.id = id;
        this.kundennummer = kundennummer;
        this.firma = firma;
        this.strasse = strasse;
        this.plz = plz;
        this.ort = ort;
    }

    // Getter & Setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKundennummer() {
        return kundennummer;
    }

    public void setKundennummer(String kundennummer) {
        this.kundennummer = kundennummer;
    }

    public String getFirma() {
        return firma;
    }

    public void setFirma(String firma) {
        this.firma = firma;
    }

    public String getStrasse() {
        return strasse;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public String getPlz() {
        return plz;
    }

    public void setPlz(String plz) {
        this.plz = plz;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }
}
