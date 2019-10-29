package store.services.impl;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import store.models.Product;
import store.repositories.ProductRepository;
import store.services.ProductService;

import java.util.List;

@Service
class IProductService implements ProductService {

    private final ProductRepository productRepositories;

    @Autowired
    IProductService(ProductRepository productRepositories) {
        this.productRepositories = productRepositories;
    }

    @Override
    public Product findById(ObjectId productId) {
        return productRepositories.findByProductId(productId);
    }

    @Override
    public List<Product> findAllProducts() {
        return (List<Product>) productRepositories.findAll();
    }

   /* @Override
    public List<Product> findAllProductsByCategory(ObjectId categoryId) {
        return productRepositories.findProductsByCategoryId(categoryId);
    }*/

    @Override
    public Product save(Product product) {
        return productRepositories.save(product);
    }

    @Override
    public void delete(ObjectId productId) {
        Product p = findById(productId);
        productRepositories.delete(p);
    }

    @Override
    public Page<Product> findAllProductsWithPages(int page, int size) {
        return productRepositories.findAll(PageRequest.of(page, size));
    }

    /*@Override
    public Page<Product> findAllProductsByCategoryWithPages(ObjectId categoryId, int page, int size) {
        return new PageImpl<>(productRepositories.findProductsByCategoryId(categoryId));
    }*/

}
