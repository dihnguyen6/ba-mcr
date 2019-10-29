/*
package store.services.impl;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import store.models.Category;
import store.repositories.CategoryRepository;
import store.services.CategoryService;

import java.util.List;

@Service
public class ICategoryService implements CategoryService {

    private final CategoryRepository categoryRepositories;

    @Autowired
    public ICategoryService(CategoryRepository categoryRepositories) {
        this.categoryRepositories = categoryRepositories;
    }

    @Override
    public Category findById(ObjectId categoryId) {
        return categoryRepositories.findCategoryById(categoryId);
    }

    @Override
    public List<Category> findAllCategories() {
        return categoryRepositories.findAll();
    }

    @Override
    public Category save(Category category) {
        return categoryRepositories.save(category);
    }

    @Override
    public void delete(ObjectId categoryId) {
        Category category = categoryRepositories.findCategoryById(categoryId);
        categoryRepositories.delete(category);
    }

}
*/
