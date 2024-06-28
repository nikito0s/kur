package bykov.polikek.kursach.repository;

import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import bykov.polikek.kursach.model.Purchase;
import org.springframework.data.jpa.repository.Query;
import java.time.LocalDateTime;


public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

    //общее количество заказов за период
    @Query("SELECT COUNT(p) FROM purchase p WHERE p.dateAddedPurchase BETWEEN :startDate AND :endDate")
    Long countPurchasesBetweenDates(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    //сумма выручки за период
    @Query("SELECT SUM(p.product.price * p.countProductInPurchase) FROM purchase p WHERE p.dateAddedPurchase BETWEEN :startDate AND :endDate")
    Long totalRevenueBetweenDates(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
}


