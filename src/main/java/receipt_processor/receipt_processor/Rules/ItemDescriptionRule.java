package receipt_processor.receipt_processor.Rules;

import receipt_processor.receipt_processor.models.Item;
import receipt_processor.receipt_processor.models.Receipt;

import java.util.List;


public class ItemDescriptionRule implements ReceiptRule{
    /*
    * Points Calculation Rule
        If the trimmed length of the item description is a multiple of 3, multiply the price by 0.2 and
        * round up to the nearest integer. The result is the number of points earned.
    * */
    @Override
    public int calculatePoints(Receipt receipt) {
        List<Item> items = receipt.getItems();
        return items.stream()
                .filter(item -> item.getShortDescription().trim().length() % 3 == 0)
                .mapToInt(item -> (int) Math.ceil(item.getPrice() * 0.2))
                .sum();
    }
}
