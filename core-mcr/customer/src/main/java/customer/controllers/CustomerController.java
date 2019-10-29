package customer.controllers;

import customer.controllers.exceptions.ResourceNotFoundException;
import customer.models.Customer;
import customer.services.CustomerServices;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/api/customer", produces = "application/hal+json")
public class CustomerController {

    private static final Logger LOG = LoggerFactory.getLogger(CustomerController.class);
    private final CustomerServices customerServices;
    private final CustomerResourceAssembler customerResourceAssembler;

    @Autowired
    public CustomerController(CustomerServices customerServices, CustomerResourceAssembler customerResourceAssembler) {
        this.customerServices = customerServices;
        this.customerResourceAssembler = customerResourceAssembler;
    }

    @GetMapping(value = {"/", ""})
    public ResponseEntity<Resources<Resource<Customer>>> getAllCustomer() {
        List<Customer> customers = customerServices.findAllCustomers();
        if (customers.isEmpty()) {
            throw new ResourceNotFoundException("Customer");
        }
        final List<Resource<Customer>> resources = customers
                .stream()
                .map(customerResourceAssembler::toResource)
                .collect(Collectors.toList());
        final Resources<Resource<Customer>> response = new Resources<>(resources);
        LOG.info("Found: - {}", customers);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/{customerId}")
    public ResponseEntity<Resource<Customer>> getCustomerById(@PathVariable ObjectId customerId) {
        Customer customer = customerServices.findCustomerById(customerId);
        if (customer == null) {
            throw new ResourceNotFoundException("Customer", "id", customerId);
        }
        LOG.info("Found: - {}", customer);
        return ResponseEntity.created(linkTo(methodOn(CustomerController.class)
                .getCustomerById(customerId)).toUri())
                .body(customerResourceAssembler.toResource(customer));
    }

    @GetMapping(value = "/pages")
    public ResponseEntity<Page<Customer>> findAllCustomersWithPage (@RequestParam int page, @RequestParam int size) {
        Page<Customer> customers = customerServices.findAllCustomerWithPages(page, size);
        if (customers.isEmpty()) {
            throw new ResourceNotFoundException("Customer");
        }
        final PageResourceSupport<Customer> response =
                new PageResourceSupport<>(customers, "page", "size");
        LOG.info("Found: - [{}, Page: - {}, Size: - {}].", customers, page, size);
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        Customer c = customerServices.save(customer);
        LOG.info("Created: - {}", c);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{customerId}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable ObjectId customerId) {
        customerServices.delete(customerId);
        LOG.info("Deleted: - Product: - {}", customerId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
