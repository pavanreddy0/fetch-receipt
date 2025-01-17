package receipt_processor.receipt_processor.Rules;

import receipt_processor.receipt_processor.models.Receipt;

public class RoundDollarTotalRule implements ReceiptRule{
    /*
    * Points Calculation Rule
        50 points if the total is a round dollar amount with no cents.
    * */
    @Override
    public int calculatePoints(Receipt receipt) {
        return receipt.getTotal() % 1 == 0 ? 50 : 0;
    }
}
