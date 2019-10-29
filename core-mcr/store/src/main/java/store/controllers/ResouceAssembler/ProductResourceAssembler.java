package store.controllers.ResouceAssembler;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;
import store.controllers.ProductController;
import store.models.Product;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class ProductResourceAssembler
        implements ResourceAssembler<Product, Resource<Product>> {
    @Override
    public Resource<Product> toResource(Product product) {
        return new Resource<>(product,
                linkTo(methodOn(ProductController.class).
                        getProductById(product.getProductId())).withSelfRel(),
                linkTo(methodOn(ProductController.class).
                        getAllProducts()).withRel("products"));
    }
}
