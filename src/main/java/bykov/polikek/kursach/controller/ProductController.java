package bykov.polikek.kursach.controller;

import baranow.polikek.kursach.model.Product;
import baranow.polikek.kursach.model.ProductWithCount;
import baranow.polikek.kursach.repository.ProductRepository;
import baranow.polikek.kursach.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;
    private final ProductRepository productRepository;

    @PostMapping
    ResponseEntity<Void> addTovar(@RequestBody @Valid Product tovar,
                                  BindingResult bindingResult){
        productService.addProduct(tovar);
        return ResponseEntity.ok().build();

    }


    @GetMapping
    ResponseEntity<List<Product>> getAllTovars(){
        return ResponseEntity.ok(productService.getAllProducts());

    }


    @GetMapping("/{id}")
    ResponseEntity<Product> getTovarById(@PathVariable Long id){
        Optional<Product> tovarOptional = productService.getProductById(id);
        return tovarOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PutMapping("/{id}")
    ResponseEntity<Product> updateTovarById(@PathVariable Long id, @RequestBody Product updatedTovar) {
        Optional<Product> updatedTovarOptional = productService.putProductById(id, updatedTovar);
        return updatedTovarOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteTovarById(@PathVariable Long id){
        productService.deleteProductById(id);
        return ResponseEntity.ok().build();
    }

    //популярные товары топ 5
    @GetMapping("/top5")
    ResponseEntity<List<ProductWithCount>> getTop5Products() {
        return ResponseEntity.ok(productRepository.findTop5Products());
    }
}
