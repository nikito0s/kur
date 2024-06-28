package baranow.polikek.kursach.service;

import baranow.polikek.kursach.exceptions.ExceptionHandler;
import baranow.polikek.kursach.model.Buyer;
import baranow.polikek.kursach.repository.BuyerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BuyerServiceImplTest {

    @Mock
    private BuyerRepository buyerRepository;

    @Mock
    private ExceptionHandler exceptionHandler;

    @InjectMocks
    private BuyerServiceImpl buyerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addBuyer() {
        Buyer buyer = new Buyer();
        buyer.setIdBuyer(1L);
        buyer.setName("John");
        buyer.setSurname("Doe");
        buyer.setTelNumber("123456789");

        // Mock the save method
        when(buyerRepository.save(buyer)).thenReturn(buyer);

        buyerService.addBuyer(buyer);

        verify(buyerRepository, times(1)).save(buyer);
    }

    @Test
    void getAllBuyers() {
        Buyer buyer1 = new Buyer();
        buyer1.setIdBuyer(1L);
        buyer1.setName("John");
        buyer1.setSurname("Doe");

        Buyer buyer2 = new Buyer();
        buyer2.setIdBuyer(2L);
        buyer2.setName("Jane");
        buyer2.setSurname("Smith");

        Page<Buyer> page = new PageImpl<>(Arrays.asList(buyer1, buyer2));

        when(buyerRepository.findAll(PageRequest.of(0, 4))).thenReturn(page);

        Page<Buyer> result = buyerService.getAllBuyers(0);

        assertNotNull(result);
        assertEquals(2, result.getTotalElements());
        verify(buyerRepository, times(1)).findAll(PageRequest.of(0, 4));
    }

    @Test
    void getBuyerById() {
        Buyer buyer = new Buyer();
        buyer.setIdBuyer(1L);
        buyer.setName("John");
        buyer.setSurname("Doe");

        when(buyerRepository.findById(1L)).thenReturn(Optional.of(buyer));

        Optional<Buyer> result = buyerService.getBuyerById(1L);

        assertTrue(result.isPresent());
        assertEquals("John", result.get().getName());
        verify(buyerRepository, times(1)).findById(1L);
    }

    @Test
    void putBuyerById() {
        Buyer existingBuyer = new Buyer();
        existingBuyer.setIdBuyer(1L);
        existingBuyer.setName("John");
        existingBuyer.setSurname("Doe");

        Buyer updatedBuyer = new Buyer();
        updatedBuyer.setName("Jane");
        updatedBuyer.setSurname("Smith");

        when(buyerRepository.findById(1L)).thenReturn(Optional.of(existingBuyer));
        when(buyerRepository.save(existingBuyer)).thenReturn(existingBuyer);

        Optional<Buyer> result = buyerService.putBuyerById(1L, updatedBuyer);

        assertTrue(result.isPresent());
        assertEquals("Jane", result.get().getName());
        assertEquals("Smith", result.get().getSurname());
        verify(buyerRepository, times(1)).findById(1L);
        verify(buyerRepository, times(1)).save(existingBuyer);
    }

    @Test
    void deleteBuyerById() {
        // Mock the deleteById method
        doNothing().when(buyerRepository).deleteById(1L);

        buyerService.deleteBuyerById(1L);

        verify(buyerRepository, times(1)).deleteById(1L);
    }
}
