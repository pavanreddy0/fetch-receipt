package receipt_processor.receipt_processor.Rules;

import receipt_processor.receipt_processor.models.Receipt;

public class RoundDollarTotalRule implements ReceiptRule{
    @Override
    public int calculatePoints(Receipt receipt) {
        return receipt.getTotal() % 1 == 0 ? 50 : 0;
    }
}
