package bykov.polikek.kursach.controller;

import bykov.polikek.kursach.model.Product;
import bykov.polikek.kursach.model.ProductWithCount;
import bykov.polikek.kursach.repository.ProductRepository;
import bykov.polikek.kursach.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ProductControllerTest {

    @Mock
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

//    @Test
//    void addProduct_ReturnsOk() {
//        Product product = new Product();
//        ResponseEntity<Void> response = productController.addTovar(product);
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        verify(productService, times(1)).addProduct(product);
//    }

    @Test
    void getAllProducts_ReturnsOk() {
        List<Product> products = new ArrayList<>();
        when(productService.getAllProducts()).thenReturn(products);

        ResponseEntity<List<Product>> response = productController.getAllTovars();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(products, response.getBody());
    }

    @Test
    void getProductById_ReturnsOk() {
        Long id = 1L;
        Product product = new Product();
        when(productService.getProductById(id)).thenReturn(Optional.of(product));

        ResponseEntity<Product> response = productController.getTovarById(id);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(product, response.getBody());
    }

    @Test
    void getProductById_ReturnsNotFound() {
        Long id = 1L;
        when(productService.getProductById(id)).thenReturn(Optional.empty());

        ResponseEntity<Product> response = productController.getTovarById(id);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void updateProductById_ReturnsOk() {
        Long id = 1L;
        Product updatedProduct = new Product();
        when(productService.putProductById(id, updatedProduct)).thenReturn(Optional.of(updatedProduct));

        ResponseEntity<Product> response = productController.updateTovarById(id, updatedProduct);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedProduct, response.getBody());
    }

    @Test
    void updateProductById_ReturnsNotFound() {
        Long id = 1L;
        Product updatedProduct = new Product();
        when(productService.putProductById(id, updatedProduct)).thenReturn(Optional.empty());

        ResponseEntity<Product> response = productController.updateTovarById(id, updatedProduct);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void deleteProductById_ReturnsOk() {
        Long id = 1L;
        ResponseEntity<Void> response = productController.deleteTovarById(id);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(productService, times(1)).deleteProductById(id);
    }

    @Test
    void getTop5Products_ReturnsOk() {
        List<ProductWithCount> top5Products = new ArrayList<>();
        when(productRepository.findTop5Products()).thenReturn(top5Products);

        ResponseEntity<List<ProductWithCount>> response = productController.getTop5Products();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(top5Products, response.getBody());
    }
}
