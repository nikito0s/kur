package bykov.polikek.kursach.controller;

import bykov.polikek.kursach.model.Buyer;
import bykov.polikek.kursach.service.BuyerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class BuyerControllerTest {

    @Mock
    private BuyerService buyerService;

    @InjectMocks
    private BuyerController buyerController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

//    @Test
//    void addBuyer_ReturnsOk() {
//        Buyer buyer = new Buyer();
//        ResponseEntity<Void> response = buyerController.addBuyer(buyer);
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        verify(buyerService, times(1)).addBuyer(buyer);
//    }

    @Test
    void getAllBuyers_ReturnsOk() {
        int page = 1;
        Page<Buyer> buyersPage = mock(Page.class);
        when(buyerService.getAllBuyers(page)).thenReturn(buyersPage);

        ResponseEntity<Page<Buyer>> response = buyerController.getAllBuyers(page);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(buyersPage, response.getBody());
    }

    @Test
    void getBuyerById_ReturnsOk() {
        Long id = 1L;
        Buyer buyer = new Buyer();
        when(buyerService.getBuyerById(id)).thenReturn(Optional.of(buyer));

        ResponseEntity<Buyer> response = buyerController.getBuyerById(id);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(buyer, response.getBody());
    }

    @Test
    void getBuyerById_ReturnsNotFound() {
        Long id = 1L;
        when(buyerService.getBuyerById(id)).thenReturn(Optional.empty());

        ResponseEntity<Buyer> response = buyerController.getBuyerById(id);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void updateBuyerById_ReturnsOk() {
        Long id = 1L;
        Buyer updatedBuyer = new Buyer();
        when(buyerService.putBuyerById(id, updatedBuyer)).thenReturn(Optional.of(updatedBuyer));

        ResponseEntity<Buyer> response = buyerController.updateBuyerById(id, updatedBuyer);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedBuyer, response.getBody());
    }

    @Test
    void updateBuyerById_ReturnsNotFound() {
        Long id = 1L;
        Buyer updatedBuyer = new Buyer();
        when(buyerService.putBuyerById(id, updatedBuyer)).thenReturn(Optional.empty());

        ResponseEntity<Buyer> response = buyerController.updateBuyerById(id, updatedBuyer);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void deleteBuyerById_ReturnsOk() {
        Long id = 1L;
        ResponseEntity<Void> response = buyerController.deleteBuyerById(id);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(buyerService, times(1)).deleteBuyerById(id);
    }
}