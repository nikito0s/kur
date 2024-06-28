package bykov.polikek.kursach.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "buyer")
@Entity(name = "buyer")
public class Buyer {

    @Id
    @Column(name="id_buyer")
    @GeneratedValue(generator = "id_buyer_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "id_buyer_seq", sequenceName = "id_buyer_seq", initialValue = 1, allocationSize = 1)
    private Long idBuyer;

    @NotEmpty(message = "Sorry, empty")
    @Min(value = 2, message = "Нереальное имя")
    @Max(value = 15, message = "Нереальное имя")
    @Column(name="name")
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "buyer")
    private List<Purchase> purchases;

    @NotEmpty(message = "Sorry, empty")
    @Min(value = 2, message = "Нереальное имя")
    @Max(value = 15, message = "Нереальное имя")
    @Column(name="surname")
    private String surname;

    @Min(value = 5, message = "телефон некорректный ")
    @NotEmpty(message = "Sorry, empty")
    @Column(name="telephone_number")
    private String telNumber;
}
