package receipt_processor.receipt_processor.Service;

import lombok.AllArgsConstructor;
import lombok.Data;
import receipt_processor.receipt_processor.models.Receipt;
import receipt_processor.receipt_processor.Rules.ReceiptRule;

import java.util.List;

@Data
@AllArgsConstructor
public class ReceiptPointsService {
    private final List<ReceiptRule> rules;


    public int calculatePoints(Receipt receipt){
        /*
        * Calculates total points for the Receipt
        * */
        return rules.stream()
                .mapToInt(rule -> rule.calculatePoints(receipt))
                .sum();
    }
}
