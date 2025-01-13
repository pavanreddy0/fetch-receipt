package receipt_processor.receipt_processor;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import receipt_processor.receipt_processor.Rules.AlphaNumericRetailerRule;
import receipt_processor.receipt_processor.Rules.ReceiptRule;
import receipt_processor.receipt_processor.models.Receipt;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class AlphaNumericRetailerRuleTest {
    ReceiptRule receiptRule = new AlphaNumericRetailerRule();

    @Test
    public void testCountCharacters(){
        Receipt receipt = Receipt.builder()
                .retailer("Target123")
                .build();
        int count = receiptRule.calculatePoints(receipt);
        assertEquals(9, count);
    }

    @Test
    public void testCountCharactersWithSpace(){
        Receipt receipt = Receipt.builder()
                .retailer("  Target123  ")
                .build();
        int count = receiptRule.calculatePoints(receipt);
        assertEquals(9, count);
    }
}
