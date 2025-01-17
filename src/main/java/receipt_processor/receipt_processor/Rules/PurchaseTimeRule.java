package receipt_processor.receipt_processor.Rules;

import receipt_processor.receipt_processor.models.Receipt;

import java.time.LocalTime;

public class PurchaseTimeRule implements ReceiptRule{
    /*
    * Points Calculation Rule
        10 points if the time of purchase is after 2:00pm and before 4:00pm.
    * */
    @Override
    public int calculatePoints(Receipt receipt) {
        LocalTime time = LocalTime.parse( receipt.getPurchaseTime());
        int hour = time.getHour();
        return hour >= 14 && hour <= 16 ? 10 : 0;
    }
}
