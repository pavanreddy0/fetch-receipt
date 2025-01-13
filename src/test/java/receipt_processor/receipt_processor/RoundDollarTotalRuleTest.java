package receipt_processor.receipt_processor;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import receipt_processor.receipt_processor.Rules.ReceiptRule;
import receipt_processor.receipt_processor.Rules.RoundDollarTotalRule;
import receipt_processor.receipt_processor.models.Receipt;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class RoundDollarTotalRuleTest {
    ReceiptRule receiptRule = new RoundDollarTotalRule();

    @Test
    public void testRoundDollarWithNoCents(){
        Receipt receipt = Receipt.builder().total(10.0).build();
        int points = receiptRule.calculatePoints(receipt);
        assertEquals(50, points);
    }

    @Test
    public void testRoundDollarCents(){
        Receipt receipt = Receipt.builder().total(10.5).build();
        int points = receiptRule.calculatePoints(receipt);
        assertEquals(0, points);
    }
}
