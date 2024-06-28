package bykov.polikek.kursach.service;

import bykov.polikek.kursach.model.Buyer;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface BuyerService {
    void addBuyer(Buyer buyer);

    Page<Buyer> getAllBuyers(int page);

    Optional<Buyer> getBuyerById(Long id);

    Optional<Buyer> putBuyerById(Long id, Buyer updatedBuyer);

    void deleteBuyerById(Long id);

}
