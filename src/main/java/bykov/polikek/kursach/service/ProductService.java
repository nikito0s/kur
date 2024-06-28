package bykov.polikek.kursach.service;


import bykov.polikek.kursach.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    void addProduct(Product product);

    List<Product> getAllProducts();

    Optional<Product> getProductById(Long id);

    Optional<Product> putProductById(Long id, Product updatedProduct);

    void deleteProductById(Long id);

}
