package order.services;

import order.clients.CustomerClient;
import order.clients.StoreClient;
import order.models.Order;
import order.repositories.OrderRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IOrderService implements OrderService{
    private final OrderRepository orderRepository;
    private final CustomerClient customerClient;
    private final StoreClient storeClient;

    @Autowired
    public IOrderService(OrderRepository orderRepository, CustomerClient customerClient, StoreClient storeClient) {
        this.orderRepository = orderRepository;
        this.customerClient = customerClient;
        this.storeClient = storeClient;
    }

    @Override
    public Order findOrderById(ObjectId orderId) {
        return orderRepository.findOrderById(orderId);
    }

    @Override
    public List<Order> findAllOrders() {
        return (List<Order>) orderRepository.findAll();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Order> findOrdersByCustomerId(ObjectId customerId) {
        return (List<Order>) orderRepository.findOrdersByCustomerId(customerId);
    }

    @Override
    public Order save(Order order) {
        if (order.getNumberOfLines() == 0)
            throw new IllegalArgumentException("No order lines");
        if (!customerClient.isValidCustomerId(order.getCustomerId()))
            throw new IllegalArgumentException("Customer does not exist");
        return orderRepository.save(order);
    }

    public double getPrice(ObjectId orderId) {
        return orderRepository.findOrderById(orderId).totalPrice(storeClient);
    }
    @Override
    public void delete(ObjectId orderId) {
        Order o = findOrderById(orderId);
        orderRepository.delete(o);
    }
}
