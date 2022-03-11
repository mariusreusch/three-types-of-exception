package ch.example.threetypesofexception.application;

import ch.example.threetypesofexception.domain.entities.Customer;
import ch.example.threetypesofexception.domain.entities.CustomerName;
import ch.example.threetypesofexception.domain.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindCustomersByNameUseCase {

    private final CustomerRepository customerRepository;

    public List<Customer> invoke(CustomerName customerName) {
        return customerRepository.findByName(customerName);
    }
}
