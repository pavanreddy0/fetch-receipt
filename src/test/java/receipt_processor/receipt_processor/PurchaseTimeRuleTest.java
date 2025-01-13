package receipt_processor.receipt_processor;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import receipt_processor.receipt_processor.Rules.PurchaseTimeRule;
import receipt_processor.receipt_processor.Rules.ReceiptRule;
import receipt_processor.receipt_processor.models.Receipt;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PurchaseTimeRuleTest {
    ReceiptRule receiptRule = new PurchaseTimeRule();

    @Test
    public void testTimeOfPurchaseInBetween(){
        Receipt receipt = Receipt.builder()
                .purchaseTime("13:01")
                .build();
        int points = receiptRule.calculatePoints(receipt);
        assertEquals(0, points);
    }

    @Test
    public void testTimeOfPurchase(){
        Receipt receipt = Receipt.builder()
                .purchaseTime("14:01")
                .build();
        int points = receiptRule.calculatePoints(receipt);
        assertEquals(10, points);
    }
}
