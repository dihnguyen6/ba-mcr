package store.services;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import store.models.Product;

import java.util.List;

public interface ProductService {
    Product findById(ObjectId productId);
    List<Product> findAllProducts();
    //List<Product> findAllProductsByCategory(ObjectId categoryId);
    Product save(Product product);
    void delete(ObjectId productId);

    Page<Product> findAllProductsWithPages(int page, int size);
    //Page<Product> findAllProductsByCategoryWithPages(ObjectId categoryId, int page, int size);
}
