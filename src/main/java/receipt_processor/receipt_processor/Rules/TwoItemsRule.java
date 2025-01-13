package receipt_processor.receipt_processor.Rules;

import receipt_processor.receipt_processor.models.Receipt;

public class TwoItemsRule implements ReceiptRule{
    @Override
    public int calculatePoints(Receipt receipt) {
        return (receipt.getItems().size() / 2) * 5;
    }
}
