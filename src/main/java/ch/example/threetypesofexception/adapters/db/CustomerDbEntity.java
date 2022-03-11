package ch.example.threetypesofexception.adapters.db;

import ch.example.threetypesofexception.domain.entities.CustomerName;

public record CustomerDbEntity(String title, String customerName) {

    public boolean matchesIgnoreCase(CustomerName customerName) {
        return customerName().toLowerCase()
                .contains(customerName.value().toLowerCase());
    }
}
