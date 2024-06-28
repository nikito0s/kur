package bykov.polikek.kursach.service;

import bykov.polikek.kursach.exceptions.ExceptionHandler;
import bykov.polikek.kursach.model.Buyer;
import bykov.polikek.kursach.repository.BuyerRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.sql.internal.ParameterRecognizerImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class BuyerServiceImpl implements BuyerService {

    private final BuyerRepository buyerRepository;
    private final ExceptionHandler exceptionHandler;
    @Override
    public void addBuyer(Buyer buyer) {
            buyerRepository.save(buyer);
    }

    @Override
    public Page<Buyer> getAllBuyers(int page) {
        int size = 4;
        return buyerRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public Optional<Buyer> getBuyerById(Long id) {
        return buyerRepository.findById(id);
    }

    @Override
    public Optional<Buyer> putBuyerById(Long id, Buyer updatedBuyer) {
        Optional<Buyer> existingBuyer = buyerRepository.findById(id);
        if (existingBuyer.isPresent()) {
            Buyer buyerToUpdate = existingBuyer.get();
            if (updatedBuyer.getName() != null) {
                buyerToUpdate.setName(updatedBuyer.getName());
            }
            if (updatedBuyer.getSurname() != null) {
                buyerToUpdate.setSurname(updatedBuyer.getSurname());
            }
            if (updatedBuyer.getTelNumber() != null) {
                buyerToUpdate.setTelNumber(updatedBuyer.getTelNumber());
            }
                buyerRepository.save(buyerToUpdate);
        }
        return existingBuyer;
    }


    @Override
    public void deleteBuyerById(Long id) {
        buyerRepository.deleteById(id);
    }
}
