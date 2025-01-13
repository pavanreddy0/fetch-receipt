package receipt_processor.receipt_processor.Rules;

import receipt_processor.receipt_processor.models.Receipt;

public class MultipleOfQuarterTotalRule implements ReceiptRule{
    @Override
    public int calculatePoints(Receipt receipt) {
        return receipt.getTotal() % 0.25 == 0 ? 25 : 0;
    }
}
