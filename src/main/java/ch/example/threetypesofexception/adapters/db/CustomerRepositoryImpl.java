package ch.example.threetypesofexception.adapters.db;

import ch.example.threetypesofexception.domain.exceptions.ConsistencyException;
import ch.example.threetypesofexception.domain.exceptions.Problem;
import ch.example.threetypesofexception.domain.exceptions.SystemException;
import ch.example.threetypesofexception.domain.entities.Customer;
import ch.example.threetypesofexception.domain.entities.CustomerName;
import ch.example.threetypesofexception.domain.repositories.CustomerRepository;
import ch.example.threetypesofexception.domain.entities.Title;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
class CustomerRepositoryImpl implements CustomerRepository {

    @Override
    public List<Customer> findByName(CustomerName customerName) {
        List<CustomerDbEntity> customerDbEntities = loadCustomersFromDatabase();

        return customerDbEntities.stream()
                .map(this::mapToDomainObject)
                .toList();
    }

    private Customer mapToDomainObject(CustomerDbEntity customerDbEntity) {
        Title title = Title.from(customerDbEntity.title()).orElseThrow(() -> new ConsistencyException(Problem.UNKNOWN_TITLE_FETCHED_FROM_DATABASE));
        CustomerName customerName = new CustomerName(customerDbEntity.customerName());
        return new Customer(title, customerName);
    }

    private List<CustomerDbEntity> loadCustomersFromDatabase() {
        try {
            // imagine some database access logic here
            return List.of(new CustomerDbEntity("Mister", "Test"));
        } catch (Exception exception) {
            throw new SystemException(Problem.CUSTOMER_DATABASE_IS_NOT_AVAILABLE, exception);
        }
    }
}
