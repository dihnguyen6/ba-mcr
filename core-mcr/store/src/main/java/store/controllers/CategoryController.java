/*
package store.controllers;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import store.controllers.ResouceAssembler.CategoryResourceAssembler;
import store.controllers.exceptions.ResourceNotFoundException;
import store.models.Category;
import store.services.CategoryService;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/api/categories", produces = {"application/hal+json"})
public class CategoryController {

    private static final Logger LOG = LoggerFactory.getLogger(CategoryController.class);
    private final CategoryService categoryService;
    private final CategoryResourceAssembler categoryResourceAssembler;

    @Autowired
    public CategoryController(CategoryService categoryService,
                              CategoryResourceAssembler categoryResourceAssembler) {
        this.categoryService = categoryService;
        this.categoryResourceAssembler = categoryResourceAssembler;
    }

    @GetMapping(value = {"/", ""})
    public ResponseEntity<Resources<Resource<Category>>> getAllCategories() {
        List<Category> categories = categoryService.findAllCategories();
        if (categories.isEmpty()) {
            throw new ResourceNotFoundException("Categories");
        }
        final List<Resource<Category>> resources = categories
                .stream()
                .map(categoryResourceAssembler::toResource)
                .collect(Collectors.toList());
        final Resources<Resource<Category>> response = new Resources<>(resources);
        LOG.info("Found: - {}", categories);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/{categoryId}")
    public ResponseEntity<Resource<Category>> getCategoryById(@PathVariable ObjectId categoryId) {
        Category category = categoryService.findById(categoryId);
        if (category == null) {
            throw new ResourceNotFoundException("Category", "id", categoryId);
        }
        LOG.info("Found: - {}", category);
        return ResponseEntity.created(linkTo(methodOn(CategoryController.class)
                .getCategoryById(categoryId)).toUri())
                .body(categoryResourceAssembler.toResource(category));
    }

    @PostMapping(value = "/")
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        Category c = categoryService.save(category);
        LOG.info("Created: - {}", c);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{categoryId}")
    public ResponseEntity<Category> deleteCategory(@PathVariable ObjectId categoryId) {
        categoryService.delete(categoryId);
        LOG.info("Deleted:- Category: - {}", categoryId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/{categoryId}")
    public ResponseEntity<Category> updateCategory(@PathVariable ObjectId categoryId,
                                                   @RequestBody Category category) {
        Category updateCategory = categoryService.findById(categoryId);
        updateCategory.setName(category.getName());
        categoryService.save(updateCategory);
        LOG.info("Updated:- {}", updateCategory);
        return new ResponseEntity<>(updateCategory, HttpStatus.OK);
    }
}
*/
