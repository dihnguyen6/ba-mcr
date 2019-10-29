package customer.repositories;

import customer.models.Customer;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends PagingAndSortingRepository<Customer, ObjectId> {
    @Query("{ 'customerId' : ?0 }")
    Customer findCustomerById(ObjectId customerId);
}
