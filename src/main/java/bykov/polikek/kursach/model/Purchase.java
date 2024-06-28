package bykov.polikek.kursach.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "purchase")
@Entity(name = "purchase")
public class Purchase {

    @Id
    @Column(name = "id_purchase")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_purchase_seq")
    @SequenceGenerator(name = "id_purchase_seq", sequenceName = "id_purchase_seq", initialValue = 1, allocationSize = 1)
    private Long idPurchase;

    @ManyToOne
    @JoinColumn(name = "id_product")
    private Product product;

    @NotEmpty(message = "Sorry, empty")
    @Column(name="count_tovar_in_purchase")
    private Long countProductInPurchase;

    @NotEmpty(message = "Sorry, empty")
    @ManyToOne
    @JoinColumn(name = "id_buyer")
    private Buyer buyer;

    @NotEmpty(message = "Sorry, empty")
    @ManyToOne
    @JoinColumn(name = "id_employee")
    private Employee employee;

    @NotEmpty(message = "Sorry, empty")
    @CreationTimestamp
    @Column(name = "date_added_purchase")
    private LocalDateTime dateAddedPurchase;
}
