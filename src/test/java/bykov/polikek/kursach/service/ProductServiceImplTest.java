package baranow.polikek.kursach.service;

import baranow.polikek.kursach.exceptions.ExceptionHandler;
import baranow.polikek.kursach.model.Employee;
import baranow.polikek.kursach.model.Product;
import baranow.polikek.kursach.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ExceptionHandler exceptionHandler;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addProduct() {
        Product product = new Product();
        product.setIdProduct(1L);
        product.setProductName("Test Product");
        product.setDiscount(10L);
        product.setPrice(100L);
        product.setCountAvailable(50L);

        when(productRepository.save(product)).thenReturn(product);

        productService.addProduct(product);

        verify(productRepository, times(1)).save(product);
    }


    @Test
    void getAllProducts() {
        Product product1 = new Product();
        product1.setIdProduct(1L);
        product1.setProductName("Product 1");

        Product product2 = new Product();
        product2.setIdProduct(2L);
        product2.setProductName("Product 2");

        List<Product> products = Arrays.asList(product1, product2);

        when(productRepository.findAll()).thenReturn(products);

        List<Product> result = productService.getAllProducts();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void getProductById() {
        Product product = new Product();
        product.setIdProduct(1L);
        product.setProductName("Test Product");

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        Optional<Product> result = productService.getProductById(1L);

        assertTrue(result.isPresent());
        assertEquals("Test Product", result.get().getProductName());
        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    void putProductById() {
        Product existingProduct = new Product();
        existingProduct.setIdProduct(1L);
        existingProduct.setProductName("Existing Product");
        existingProduct.setDiscount(5L);
        existingProduct.setPrice(100L);
        existingProduct.setCountAvailable(50L);

        Product updatedProduct = new Product();
        updatedProduct.setProductName("Updated Product");
        updatedProduct.setDiscount(10L);
        updatedProduct.setPrice(120L);
        updatedProduct.setCountAvailable(60L);

        when(productRepository.findById(1L)).thenReturn(Optional.of(existingProduct));
        when(productRepository.save(existingProduct)).thenReturn(existingProduct);

        Optional<Product> result = productService.putProductById(1L, updatedProduct);

        assertTrue(result.isPresent());
        assertEquals("Updated Product", result.get().getProductName());
        assertEquals(10L, result.get().getDiscount());
        assertEquals(120L, result.get().getPrice());
        assertEquals(60L, result.get().getCountAvailable());
        verify(productRepository, times(1)).findById(1L);
        verify(productRepository, times(1)).save(existingProduct);
    }

    @Test
    void deleteProductById() {
        doNothing().when(productRepository).deleteById(1L);

        productService.deleteProductById(1L);

        verify(productRepository, times(1)).deleteById(1L);
    }
}
