package bykov.polikek.kursach.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductWithCount {
    private Long idProduct;
    private String productName;
    private Long purchaseCount;
}