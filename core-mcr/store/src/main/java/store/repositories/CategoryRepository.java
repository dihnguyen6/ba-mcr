/*
package store.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import store.models.Category;

@Repository
public interface CategoryRepository extends MongoRepository<Category, Object> {
    @Query("{ 'categoryId' : ?0 }")
    Category findCategoryById(ObjectId categoryId);
}
*/
