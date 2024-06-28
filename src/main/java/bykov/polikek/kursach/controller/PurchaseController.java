package bykov.polikek.kursach.controller;

import baranow.polikek.kursach.model.Purchase;
import baranow.polikek.kursach.repository.PurchaseRepository;
import baranow.polikek.kursach.service.PurchaseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/purchase")

public class PurchaseController {
    private final PurchaseService purchaseService;
    private final PurchaseRepository purchaseRepository;

    @PostMapping
    ResponseEntity<Void> addPurchase(@RequestBody @Valid Purchase purchase,
                                     BindingResult bindingResult){
        purchaseService.addPurchase(purchase);
        return ResponseEntity.ok().build();

    }


    @GetMapping
    ResponseEntity<List<Purchase>> getAllPurchase(){
        return ResponseEntity.ok(purchaseService.getAllPurchase());

    }


    @GetMapping("/{id}")
    ResponseEntity<Purchase> getPurchaseById(@PathVariable Long id){
        Optional<Purchase> purchaseOptional = purchaseService.getPurchaseById(id);
        return purchaseOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PutMapping("/{id}")
    ResponseEntity<Purchase> updatePurchaseById(@PathVariable Long id, @RequestBody @Validated Purchase updatedPurchase) {
        Optional<Purchase> updatedPurchaseOptional = purchaseService.putPurchaseById(id, updatedPurchase);
        return updatedPurchaseOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deletePurchaseById(@PathVariable Long id){
        purchaseService.deletePurchaseById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/count")
    ResponseEntity<Long> countPurchasesBetweenDates(@RequestParam LocalDateTime startDate, @RequestParam LocalDateTime endDate) {
        Long count = purchaseRepository.countPurchasesBetweenDates(startDate, endDate);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/revenue")
    ResponseEntity<Long> totalRevenueBetweenDates(@RequestParam LocalDateTime startDate, @RequestParam LocalDateTime endDate) {
        Long revenue = purchaseRepository.totalRevenueBetweenDates(startDate, endDate);
        return ResponseEntity.ok(revenue);
    }




}
