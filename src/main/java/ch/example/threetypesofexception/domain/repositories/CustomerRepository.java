package ch.example.threetypesofexception.domain.repositories;


import ch.example.threetypesofexception.domain.entities.Customer;
import ch.example.threetypesofexception.domain.entities.CustomerName;

import java.util.List;

public interface CustomerRepository {

    List<Customer> findByName(CustomerName customerName);
}
