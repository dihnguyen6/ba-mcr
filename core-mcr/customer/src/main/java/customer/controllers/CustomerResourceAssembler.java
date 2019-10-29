package customer.controllers;

import customer.models.Customer;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class CustomerResourceAssembler implements ResourceAssembler<Customer, Resource<Customer>> {
    @Override
    public Resource<Customer> toResource(Customer customer) {
        return new Resource<>(customer,
                linkTo(methodOn(CustomerController.class)
                        .getCustomerById(customer.getCustomerId())).withSelfRel(),
                linkTo(methodOn(CustomerController.class)
                        .getAllCustomer()).withRel("customers"));
    }
}
