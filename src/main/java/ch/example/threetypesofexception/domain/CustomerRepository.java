package ch.example.threetypesofexception.domain;


import java.util.List;

public interface CustomerRepository {

    List<Customer> findByName(CustomerName customerName);
}
