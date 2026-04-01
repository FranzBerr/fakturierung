# 📁 Backend-Struktur (DDD + Hexagonale Architektur)

```bash
backend/
├── pom.xml
└── src/
    ├── main/
    │   ├── java/com/bcs/fakturierung/
    │   │   ├── FakturierungApplication.java
    │   │   │
    │   │   ├── customer/                 # Bounded Context "Customer"
    │   │   │   ├── domain/               # Domain Layer (Kernmodell)
    │   │   │   │   ├── Customer.java
    │   │   │   │   └── CustomerRepository.java   # Domain-Interface (Port)
    │   │   │   │
    │   │   │   ├── application/          # Use Cases / Services
    │   │   │   │   ├── CustomerService.java
    │   │   │   │   └── dto/
    │   │   │   │       └── CustomerDto.java
    │   │   │   │
    │   │   │   ├── infrastructure/       # Adapter (Persistence, Web, Mapping)
    │   │   │   │   ├── persistence/
    │   │   │   │   │   ├── JpaCustomerEntity.java
    │   │   │   │   │   └── JpaCustomerRepository.java
    │   │   │   │   ├── web/
    │   │   │   │   │   └── CustomerController.java
    │   │   │   │   └── mapper/
    │   │   │   │       └── CustomerMapper.java   # MapStruct
    │   │   │   │
    │   │   │   └── config/
    │   │   │       └── CustomerConfig.java       # Beans, evtl. Mapper
    │   │   │
    │   │   └── shared/
    │   │       └── error/
    │   │           └── GlobalExceptionHandler.java
    │   │
    │   └── resources/
    │       ├── application.yml
    │       └── db/
    │           └── migration/
    │               └── V1__create_customer_table.sql
    │
    └── test/
        └── java/com/bcs/fakturierung/
            └── customer/
                └── infrastructure/
                    └── persistence/
                        └── JpaCustomerRepositoryIT.java   # Testcontainers MSSQL

```
Frontend-Struktur (Next.js 14, App Router)
```bash
frontend/
├── package.json
├── next.config.js
├── .env.local                      # NEXT_PUBLIC_API_BASE_URL=http://localhost:8080
├── app/
│   ├── layout.tsx
│   ├── page.tsx                    # Dashboard / Landing
│   └── customers/
│       ├── page.tsx                # Liste
│       ├── new/
│       │   └── page.tsx            # Neuer Kunde
│       └── [id]/
│           ├── page.tsx            # Bearbeiten
│           └── CustomerForm.tsx    # Client Component
└── src/
    ├── services/
    │   └── customerService.ts      # REST-Client (fetch)
    └── types/
        └── customer.ts             # DTO-Typen

```

# 🧱 1. DOMAIN (Kernmodell + Ports)
📁 Struktur
```Bash
customer/
 ├── domain/
 │    ├── Customer.java
 │    └── CustomerRepository.java   ← Domain-Port (Interface)

```
## Customer.java (Domain Entity – rein, ohne JPA)
```Java
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

    public Long getId() { return id; }
    public String getKundennummer() { return kundennummer; }
    public String getFirma() { return firma; }
    public String getStrasse() { return strasse; }
    public String getPlz() { return plz; }
    public String getOrt() { return ort; }

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
```
Gerne, Franz — ich erkläre dir die Klasse Customer Schritt für Schritt, so wie man es in einer sauberen Domain‑Driven‑Design‑Domäne erwarten würde.

## 🧩 Überblick: Was macht diese Klasse?

Die Klasse Customer ist ein einfaches Domain Model (ein sogenanntes Entity) aus dem Paket
com.bcs.fakturierung.customer.domain.

Sie repräsentiert einen Kunden im Fakturierungssystem — mit ID, Kundennummer, Adresse und Firmennamen.

### 🏗️ Aufbau der Klasse
🔹 Felder (Attribute)
```Java
private Long id;
private String kundennummer;
private String firma;
private String strasse;
private String plz;
private String ort;
```
* id → technische, eindeutige Identifikation (Primärschlüssel)
* kundennummer → fachliche Kundennummer
* firma → Firmenname
* strasse, plz, ort → Adresse
### 🔹 Konstruktor
```Java
public Customer(Long id, String kundennummer, String firma, String strasse, String plz, String ort)
```
Der Konstruktor erzwingt, dass ein Customer immer vollständig initialisiert wird.

### 🔹 Getter

Nur Getter, keine Setter:
```Java
public Long getId() { ... }
public String getKundennummer() { ... }
...
```
Das ist typisch für Domain-Modelle:
* Unveränderlichkeit der Identität
* Änderungen laufen über explizite Methoden, nicht über generische Setter

### 🔹 Update-Methode
```Java
public void update(String firma, String strasse, String plz, String ort)
```
Diese Methode erlaubt kontrollierte Änderungen am Kundenobjekt.

Warum ist das gut?
* Es verhindert unkontrollierte Mutationen.
* Es macht Änderungen semantisch sichtbar (DDD‑Prinzip).

### 🔹 equals() und hashCode()
```Java
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
```
Bedeutung:
* Zwei Customer‑Objekte gelten als gleich, wenn ihre id gleich ist.
* Das ist korrekt für Domain Entities:  
    Identität basiert auf der ID, nicht auf den Attributen.

Das ist wichtig für:
* Collections (HashSet, HashMap)
* JPA/Hibernate
* Domain‑Logik

### 🧠 Zusammenfassung

| Bereich | Bedeutung |
| --- | --- |
| **Entity** | Repräsentiert einen Kunden im Domain‑Modell |
| **ID** | Identität des Kunden |
| **Keine Setter** | Kontrollierte Änderungen, klare Domain‑Methoden |
| **update()** | Fachlich sinnvolle Änderung der Kundendaten |
| **equals/hashCode** | Identität basiert ausschließlich auf der ID |

=====================================================

# CustomerRepository.java (Domain-Port)

```Java
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
}
```

## 🧩 Was ist das CustomerRepository?

Das Interface definiert den Vertrag, wie deine Domain‑Schicht auf Kunden zugreifen darf.

Es ist reine Domain‑Logik, kein Framework, keine Datenbankdetails.
Das Repository abstrahiert:
* Datenbank
* JPA
* Filesystem
* REST‑Service
* In‑Memory‑Store
* oder was auch immer darunter liegt

Die Domain weiß nur:
👉 „Ich kann Kunden speichern, laden, löschen.“

## 🧠 Erklärung der Methoden
### 🔹 List<Customer> findAll()  
Gibt alle Kunden zurück.
* Wird z. B. für Listenansichten im UI genutzt.
* Domain‑Services können damit arbeiten.

### 🔹 Optional<Customer> findById(Long id)
Sucht einen Kunden anhand der technischen ID.
* Optional verhindert NullPointer‑Fehler.
* Domain‑konform: „Vielleicht gibt es den Kunden, vielleicht nicht.“

### 🔹 Optional<Customer> findByKundennummer(String kundennummer)
Sucht anhand der fachlichen Kundennummer.

Das ist wichtig, weil:
* Kundennummer ist oft unique
* Fachlich relevanter als die technische ID
* Wird häufig in Fakturierungssystemen genutzt

### 🔹 Customer save(Customer customer)  
Speichert oder aktualisiert einen Kunden.
* Rückgabe ist wichtig, weil Persistenzschichten IDs vergeben können.
* Domain‑Services können damit weiterarbeiten.

### 🔹 void deleteById(Long id)  
Löscht einen Kunden anhand der ID.
* Domain‑Services können damit Löschvorgänge auslösen.
* Die Domain muss nicht wissen, wie gelöscht wird.

### 🔹 boolean existsByKundennummer(String kundennummer) 
Prüft, ob ein Kunde existiert.
Typische Anwendungsfälle:
* Validierung beim Anlegen neuer Kunden
* Verhindern von Duplikaten
* Fachliche Regeln wie „Kundennummer muss eindeutig sein“

### 🏗️ Warum ist das ein Interface?  

Weil du damit saubere Schichten bekommst:

| Schicht | Aufgabe |
| --- | --- |
| **Domain** | Definiert *was* passieren soll |
| **Infrastructure** | Implementiert *wie* es passiert |

Die Domain hängt nur vom Interface ab.  
Die Infrastruktur hängt vom Interface und der Datenbank ab.

Das ist klassisches DDD.
### 🧱 Typische Implementierung (z. B. Spring Data JPA)

In der Infrastruktur weiter unten:
```java
@Repository
public interface JpaCustomerRepository extends CustomerRepository, JpaRepository<CustomerEntity, Long> {

    Optional<CustomerEntity> findByKundennummer(String kundennummer);

    boolean existsByKundennummer(String kundennummer);
}
```

Und du mapst:
* CustomerEntity ↔ Customer (Domain)  
Damit bleibt deine Domain rein, framework‑frei und testbar.

### 🎯 Warum ist das gut?
* Testbarkeit: Du kannst das Repository mocken.
* Flexibilität: Datenbankwechsel ohne Domain‑Änderung.
* DDD‑konform: Domain kennt keine technischen Details.
* Saubere Architektur: Infrastruktur bleibt unten, Domain bleibt oben.

=============================================

# 🧠 2. APPLICATION LAYER (Use Cases + DTOs)
📁 Struktur
```bash
customer/
 ├── application/
 │    ├── CustomerService.java
 │    └── dto/
 │         └── CustomerDto.java
```
### 🧩 Der Code
```Java
package com.bcs.fakturierung.customer.application.dto;

public record CustomerDto(
        Long id,
        String kundennummer,
        String firma,
        String strasse,
        String plz,
        String ort
) {}
```
Gerne, Franz — das ist ein schönes Beispiel für ein sauberes, modernes DTO in einer Application‑Schicht. Ich erkläre dir genau, was dieses CustomerDto macht, warum es ein record ist und wie es sich in eine DDD‑Architektur einfügt.

### 🧠 Was ist ein DTO?

Ein DTO (Data Transfer Object) ist ein Objekt, das ausschließlich dazu dient, Daten zwischen Schichten auszutauschen:
* Controller → Application Layer
* Application Layer → Frontend (REST)
* Application Layer → andere Services

Es enthält keine Domain‑Logik, keine Regeln, keine Methoden außer den automatisch generierten.

### 🚀 Warum ein record?

Java Records (seit Java 16) sind perfekt für DTOs, weil sie:
* immutable sind
* automatisch equals(), hashCode(), toString() generieren
* kompakt und lesbar sind
* keine Setter haben
* semantisch ausdrücken: „Das ist ein reiner Datentransport.“

Ein DTO soll niemals Domain‑Logik enthalten — und ein Record verhindert das elegant.

# 🧱 Felder im DTO
Das DTO enthält exakt die Felder, die du im Frontend brauchst:

| Feld | Bedeutung |
| --- | --- |
| ``id`` | technische ID |
| ``kundennummer`` | fachliche Kundennummer |
| ``firma`` | Firmenname |
| ``strasse`` | Straße |
| ``plz`` | Postleitzahl |
| ``ort`` | Ort |

### 🔄 Wie passt das in deine Architektur?

Du hast eine klassische, saubere DDD‑Schichtung:
```bash
Domain Layer
    ↕ (Mapping)
Application Layer (DTOs)
    ↕ (REST)
API Layer / Controller
    ↕
Frontend (Next.js)
```
Domain → DTO  
Wird z. B. mit MapStruct oder manuell gemappt.  
DTO → Domain  
Beim Erstellen oder Aktualisieren eines Kunden.

### 🧠 Warum kein Domain‑Objekt direkt ins REST‑API?

Weil das gefährlich wäre:
* Domain‑Objekte enthalten Regeln, Invarianten, Methoden
* DTOs sind flach, serialisierbar, stabil
* Domain‑Modelle ändern sich oft → API sollte stabil bleiben
* DTOs schützen die Domain vor „Leaky Abstractions“

## 🛠️ Typischer Mapper (manuell)
```Java
public class CustomerMapper {

    public static CustomerDto toDto(Customer c) {
        return new CustomerDto(
                c.getId(),
                c.getKundennummer(),
                c.getFirma(),
                c.getStrasse(),
                c.getPlz(),
                c.getOrt()
        );
    }

    public static Customer toDomain(CustomerDto dto) {
        return new Customer(
                dto.id(),
                dto.kundennummer(),
                dto.firma(),
                dto.strasse(),
                dto.plz(),
                dto.ort()
        );
    }
}
```

🎯 Zusammenfassung

CustomerDto ist:
* ein Record → perfekt für immutable Daten
* ein reines Transportobjekt
* Teil der Application Layer, nicht der Domain
* ideal für REST‑APIs
* stabil, kompakt, sicher

Es trennt sauber:
* Domain‑Logik (Customer)
* Transport‑Daten (CustomerDto)

Genau so baut man moderne, wartbare Enterprise‑Systeme.