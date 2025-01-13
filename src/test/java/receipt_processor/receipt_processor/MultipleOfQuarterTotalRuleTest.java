package receipt_processor.receipt_processor;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import receipt_processor.receipt_processor.Rules.MultipleOfQuarterTotalRule;
import receipt_processor.receipt_processor.Rules.ReceiptRule;
import receipt_processor.receipt_processor.models.Receipt;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class MultipleOfQuarterTotalRuleTest {

    ReceiptRule receiptRule = new MultipleOfQuarterTotalRule();
    @Test
    public void testCalculateMultipleOf025(){
        Receipt receipt = Receipt.builder().total(0.75).build();
        int points = receiptRule.calculatePoints(receipt);
        assertEquals(25, points);
    }

    @Test
    public void testCalculateMultipleOf025Whole(){
        Receipt receipt = Receipt.builder().total(1.00).build();
        int points = receiptRule.calculatePoints(receipt);
        assertEquals(25, points);
    }
}
