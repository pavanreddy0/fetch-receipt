package receipt_processor.receipt_processor.Rules;

import receipt_processor.receipt_processor.models.Receipt;

public class AlphaNumericRetailerRule implements ReceiptRule{
    /*
    * Points Calculation Rule
        One point for every alphanumeric character in the retailer name.
    * */
    @Override
    public int calculatePoints(Receipt receipt) {
        return (int)receipt.getRetailer()
                .chars()
                .filter(Character::isLetterOrDigit)
                .count();
    }
}
