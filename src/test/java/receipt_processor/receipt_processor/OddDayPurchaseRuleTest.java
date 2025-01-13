package receipt_processor.receipt_processor;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import receipt_processor.receipt_processor.Rules.OddDayPurchaseRule;
import receipt_processor.receipt_processor.Rules.ReceiptRule;
import receipt_processor.receipt_processor.models.Receipt;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class OddDayPurchaseRuleTest {
    ReceiptRule receiptRule = new OddDayPurchaseRule();

    @Test
    public void testCalculatePurchaseOddDate(){
        Receipt receipt = Receipt.builder()
                .purchaseDate("2022-01-01")
                .build();
        int points = receiptRule.calculatePoints(receipt);
        assertEquals(6, points);
    }

    @Test
    public void testCalculatePurchaseEvenDate(){
        Receipt receipt = Receipt.builder()
                .purchaseDate("2022-01-02")
                .build();
        int points = receiptRule.calculatePoints(receipt);
        assertEquals(0, points);
    }
}
