package receipt_processor.receipt_processor.Rules;

import receipt_processor.receipt_processor.models.Item;
import receipt_processor.receipt_processor.models.Receipt;

import java.util.List;


public class ItemDescriptionRule implements ReceiptRule{
    @Override
    public int calculatePoints(Receipt receipt) {
        List<Item> items = receipt.getItems();
        return items.stream()
                .filter(item -> item.getShortDescription().trim().length() % 3 == 0)
                .mapToInt(item -> (int) Math.ceil(item.getPrice() * 0.2))
                .sum();
    }
}
