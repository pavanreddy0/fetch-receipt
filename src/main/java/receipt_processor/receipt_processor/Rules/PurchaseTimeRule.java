package receipt_processor.receipt_processor.Rules;

import receipt_processor.receipt_processor.models.Receipt;

import java.time.LocalTime;

public class PurchaseTimeRule implements ReceiptRule{
    @Override
    public int calculatePoints(Receipt receipt) {
        LocalTime time = LocalTime.parse( receipt.getPurchaseTime());
        int hour = time.getHour();
        return hour >= 14 && hour <= 16 ? 10 : 0;
    }
}
