package receipt_processor.receipt_processor.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import receipt_processor.receipt_processor.models.Receipt;

import java.util.UUID;

public interface ReceiptRepository extends JpaRepository<Receipt, UUID> {
}
