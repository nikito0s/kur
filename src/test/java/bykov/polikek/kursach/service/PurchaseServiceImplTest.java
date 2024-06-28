package baranow.polikek.kursach.service;

import baranow.polikek.kursach.exceptions.ExceptionHandler;
import baranow.polikek.kursach.model.Buyer;
import baranow.polikek.kursach.model.Employee;
import baranow.polikek.kursach.model.Product;
import baranow.polikek.kursach.model.Purchase;
import baranow.polikek.kursach.repository.BuyerRepository;
import baranow.polikek.kursach.repository.EmployeeRepository;
import baranow.polikek.kursach.repository.ProductRepository;
import baranow.polikek.kursach.repository.PurchaseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PurchaseServiceImplTest {

    @Mock
    private PurchaseRepository purchaseRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private BuyerRepository buyerRepository;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private ExceptionHandler exceptionHandler;

    @InjectMocks
    private PurchaseServiceImpl purchaseService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addPurchase_success() {
        Purchase purchase = new Purchase();
        Product product = new Product();
        Buyer buyer = new Buyer();
        Employee employee = new Employee();

        purchase.setProduct(product);
        purchase.setBuyer(buyer);
        purchase.setEmployee(employee);

        when(productRepository.findById(any())).thenReturn(Optional.of(product));
        when(buyerRepository.findById(any())).thenReturn(Optional.of(buyer));
        when(employeeRepository.findById(any())).thenReturn(Optional.of(employee));

        purchaseService.addPurchase(purchase);

        verify(purchaseRepository, times(1)).save(purchase);
    }


    @Test
    void deletePurchaseById_success() {
        Long purchaseId = 1L;

        purchaseService.deletePurchaseById(purchaseId);

        verify(purchaseRepository, times(1)).deleteById(purchaseId);
    }
}
