package customer.services;

import customer.models.Customer;
import customer.repositories.CustomerRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ICustomerService implements CustomerServices {

    private final CustomerRepository customerRepositories;

    @Autowired
    public ICustomerService(CustomerRepository customerRepositories) {
        this.customerRepositories = customerRepositories;
    }

    @Override
    public Customer findCustomerById(ObjectId customerId) {
        return customerRepositories.findCustomerById(customerId);
    }

    @Override
    public List<Customer> findAllCustomers() {
        return (List<Customer>) customerRepositories.findAll();
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepositories.save(customer);
    }

    @Override
    public void delete(ObjectId customerId) {
        Customer c = findCustomerById(customerId);
        customerRepositories.delete(c);
    }

    @Override
    public Page<Customer> findAllCustomerWithPages(int page, int size) {
        return customerRepositories.findAll(PageRequest.of(page, size));
    }
}
