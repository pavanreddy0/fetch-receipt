package receipt_processor.receipt_processor.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import receipt_processor.receipt_processor.models.Item;
import receipt_processor.receipt_processor.models.Receipt;

import java.util.List;
import java.util.UUID;

public interface ItemRepository extends JpaRepository<Item, UUID> {
    List<Item> findByReceipt(Receipt receipt);
}
