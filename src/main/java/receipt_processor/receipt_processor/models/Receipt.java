package receipt_processor.receipt_processor.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@Entity
@Table(name = "receipt")
@NoArgsConstructor
@AllArgsConstructor
public class Receipt {
    @Id
    @GeneratedValue(generator = "uuid2")
    private UUID receipt_id;

    @Column(nullable = false)
    private String retailer;
    @Column(nullable = false)
    private String purchaseDate;
    @Column(nullable = false)
    private String purchaseTime;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "receipt")
    private List<Item> items;
    @Column(nullable = false)
    private Double total;
    private int points;
}
