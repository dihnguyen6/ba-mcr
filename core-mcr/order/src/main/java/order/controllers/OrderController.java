package order.controllers;

import order.clients.Customer;
import order.clients.CustomerClient;
import order.clients.Product;
import order.clients.StoreClient;
import order.models.Order;
import order.services.OrderService;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping(value = "/api/order", produces = "application/hal+json")
public class OrderController {
    private static final Logger LOG = LoggerFactory.getLogger(OrderController.class);

    private final OrderService orderService;
    private final CustomerClient customerClient;
    private final StoreClient storeClient;

    @Autowired
    public OrderController(OrderService orderService, CustomerClient customerClient, StoreClient storeClient) {
        this.orderService = orderService;
        this.customerClient = customerClient;
        this.storeClient = storeClient;
    }

    @GetMapping(value = {"/", ""})
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(orderService.findAllOrders());
    }

    @GetMapping(value = "/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable ObjectId orderId) {
        return ResponseEntity.ok(orderService.findOrderById(orderId));
    }

    @GetMapping(value = "/customer")
    public ResponseEntity<List<Order>> getOrdersByCustomer(@RequestParam ObjectId customerId) {
        return ResponseEntity.ok(orderService.findOrdersByCustomerId(customerId));
    }

    @PostMapping(value = "/")
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        orderService.save(order);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/products")
    public ResponseEntity<Collection<Product>> allProducts() {
        return ResponseEntity.ok(storeClient.findAll());
    }

    @GetMapping(value = "/customers")
    public  ResponseEntity<Collection<Customer>> allCustomer() {
        return ResponseEntity.ok(customerClient.findAll());
    }
}
