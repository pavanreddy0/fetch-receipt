package receipt_processor.receipt_processor.Rules;

import receipt_processor.receipt_processor.models.Receipt;

public interface ReceiptRule {
    public int calculatePoints(Receipt receipt);
}
