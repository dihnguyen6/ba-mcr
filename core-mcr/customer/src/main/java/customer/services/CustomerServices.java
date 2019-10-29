package customer.services;

import customer.models.Customer;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CustomerServices {
    Customer findCustomerById(ObjectId customerId);
    List<Customer> findAllCustomers();
    Customer save(Customer customer);
    void delete(ObjectId customerId);

    Page<Customer> findAllCustomerWithPages(int page, int size);
}
