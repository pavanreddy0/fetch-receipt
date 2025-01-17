package receipt_processor.receipt_processor.Rules;

import receipt_processor.receipt_processor.models.Receipt;

public class TwoItemsRule implements ReceiptRule{
    /*
    * Points Calculation Rule
        5 points for every two items on the receipt.
    * */
    @Override
    public int calculatePoints(Receipt receipt) {
        return (receipt.getItems().size() / 2) * 5;
    }
}
