/*
package store.controllers.ResouceAssembler;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;
import store.controllers.CategoryController;
import store.models.Category;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class CategoryResourceAssembler implements ResourceAssembler<Category, Resource<Category>> {

    @Override
    public Resource<Category> toResource(Category category) {
        return new Resource<>(category,
                linkTo(methodOn(CategoryController.class).getCategoryById(category.getCategoryId())).withSelfRel(),
                linkTo(methodOn(CategoryController.class).getAllCategories()).withRel("categories"));
    }
}
*/
