package receipt_processor.receipt_processor.Rules;

import receipt_processor.receipt_processor.models.Receipt;

public class MultipleOfQuarterTotalRule implements ReceiptRule{
    /*
    * Points Calculation Rule
        25 points if the total is a multiple of 0.25.
    * */
    @Override
    public int calculatePoints(Receipt receipt) {
        return receipt.getTotal() % 0.25 == 0 ? 25 : 0;
    }
}
