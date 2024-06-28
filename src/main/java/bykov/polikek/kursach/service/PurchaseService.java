package bykov.polikek.kursach.service;

import bykov.polikek.kursach.model.Purchase;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;

public interface PurchaseService {


    void addPurchase( Purchase purchase);

    List<Purchase> getAllPurchase();

    Optional<Purchase> getPurchaseById(Long id);

    Optional<Purchase> putPurchaseById(Long id, Purchase updatedPurchase);

    void deletePurchaseById(Long id);

}
