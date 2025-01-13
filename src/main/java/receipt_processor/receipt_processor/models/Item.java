package receipt_processor.receipt_processor.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "item")
public class Item {
    @Id
    @GeneratedValue(generator = "uuid2")
    private UUID item_id;
    @Column(nullable = false)
    private String shortDescription;
    @Column(nullable = false)
    private double price;
    @ManyToOne
    @JoinColumn(name = "receipt_id")
    private Receipt receipt;
}
