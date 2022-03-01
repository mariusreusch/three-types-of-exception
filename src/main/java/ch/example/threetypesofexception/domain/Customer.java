package ch.example.threetypesofexception.domain;

public class Customer {

    private final Title title;
    private final CustomerName customerName;

    public Customer(Title title, CustomerName customerName) {
        this.title = title;
        this.customerName = customerName;
    }

    public Title getTitle() {
        return title;
    }

    public CustomerName getCustomerName() {
        return customerName;
    }
}
