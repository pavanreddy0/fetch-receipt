package receipt_processor.receipt_processor.Rules;

import receipt_processor.receipt_processor.models.Receipt;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class OddDayPurchaseRule implements ReceiptRule{
    /*
    * Points Calculation Rule
        6 points if the day in the purchase date is odd.
    * */
    @Override
    public int calculatePoints(Receipt receipt) {
        LocalDate date = LocalDate.parse(receipt.getPurchaseDate(), DateTimeFormatter.ISO_DATE);
        return date.getDayOfMonth() % 2 != 0 ? 6 : 0;
    }
}
