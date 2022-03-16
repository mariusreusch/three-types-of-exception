package ch.example.threetypesofexception.adapters.db;

import ch.example.threetypesofexception.domain.entities.Customer;
import ch.example.threetypesofexception.domain.entities.CustomerName;
import ch.example.threetypesofexception.domain.entities.Title;
import ch.example.threetypesofexception.domain.exceptions.Problem;
import ch.example.threetypesofexception.domain.exceptions.SystemException;
import ch.example.threetypesofexception.domain.repositories.CustomerRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static ch.example.threetypesofexception.adapters.db.SafeMapFromDbToDomain.safeMapFromDatabaseToDomain;

@Repository
class CustomerRepositoryImpl implements CustomerRepository {

    @Override
    public List<Customer> findByName(CustomerName customerName) {
        List<CustomerDbEntity> customerDbEntities = loadCustomersFromDatabase(customerName);

        return customerDbEntities.stream()
                .map(c -> safeMapFromDatabaseToDomain(this::mapToDomainObject, c))
                .toList();
    }

    private Customer mapToDomainObject(CustomerDbEntity customerDbEntity) {
        Title title = Title.from(customerDbEntity.title());
        CustomerName customerName = new CustomerName(customerDbEntity.customerName());
        return new Customer(title, customerName);
    }

    private List<CustomerDbEntity> loadCustomersFromDatabase(CustomerName customerName) {
        try {
            // imagine some database access logic here
            return Stream.of(
                            new CustomerDbEntity("Mister", "Test"),
                            new CustomerDbEntity("Mr", "Mustermann"),
                            new CustomerDbEntity("Mrs", "Musterfrau")
                    )
                    .filter(c -> c.matchesIgnoreCase(customerName))
                    .collect(Collectors.toList());
        } catch (Exception exception) {
            throw new SystemException(Problem.CUSTOMER_DATABASE_IS_NOT_AVAILABLE, exception);
        }
    }
}
