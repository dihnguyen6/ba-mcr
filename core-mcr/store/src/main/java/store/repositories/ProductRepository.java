package store.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import store.models.Product;

import java.util.List;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, ObjectId> {
    @Query("{ 'productId' : ?0 }")
    Product findByProductId(ObjectId productId);

    @Query("{ 'categoryId' : ?0 }")
    List<Product> findProductsByCategoryId(ObjectId categoryId);
}
