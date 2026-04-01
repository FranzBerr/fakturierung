package com.bcs.fakturierung.seed;

import com.bcs.fakturierung.customer.domain.Customer;
import com.bcs.fakturierung.customer.infrastructure.persistence.JpaCustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
//@Profile("dev") // Seeder läuft nur in der Entwicklungsumgebung
@RequiredArgsConstructor
public class CustomerSeeder implements CommandLineRunner {

    private final JpaCustomerRepository customerRepository;

    @Override
    public void run(String... args) {

        long count = customerRepository.count();
        if (count > 0) {
            System.out.println("✔ Customers already exist — skipping seed");
            return;
        }

        Customer c1 = new Customer(null, "K0001", "Alpha GmbH", "Hauptstr. 1", "80331", "München");
        Customer c2 = new Customer(null, "K0002", "Beta GmbH", "Ringstr. 1", "70173", "Stuttgart");
        Customer c3 = new Customer(null, "K0003", "Gamma Solutions", "Marktplatz 5", "50667", "Köln");

        customerRepository.save(c1);
        customerRepository.save(c2);
        customerRepository.save(c3);

        System.out.println("✔ Customer seed completed");
    }
    // 100 Datensätze erzeugen
//        List<Customer> customers = new ArrayList<>();
//
//        for (int i = 1; i <= 100; i++) {
//            customers.add(new Customer(
//                    null,
//                    "Firstname" + i,
//                    "Lastname" + i,
//                    "customer" + i + "@example.com"
//            ));
//        }
//
//        customerRepository.saveAll(customers);
//
//        System.out.println("✔ Seeded 100 customers");
//    }
}
