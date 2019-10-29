package order.repositories;

import order.models.Order;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends PagingAndSortingRepository<Order, Resource<Order>> {

    @Query("{ 'orderId' : ?0 }")
    Order findOrderById(ObjectId orderId);

    @Query("{ 'customerId' : ?0 }")
    Order findOrdersByCustomerId(ObjectId customerId);

}
