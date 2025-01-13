package receipt_processor.receipt_processor.Rules;

import receipt_processor.receipt_processor.models.Receipt;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class OddDayPurchaseRule implements ReceiptRule{
    @Override
    public int calculatePoints(Receipt receipt) {
        LocalDate date = LocalDate.parse(receipt.getPurchaseDate(), DateTimeFormatter.ISO_DATE);
        return date.getDayOfMonth() % 2 != 0 ? 6 : 0;
    }
}
