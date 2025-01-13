package receipt_processor.receipt_processor;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import receipt_processor.receipt_processor.Rules.ReceiptRule;
import receipt_processor.receipt_processor.Rules.TwoItemsRule;
import receipt_processor.receipt_processor.models.Item;
import receipt_processor.receipt_processor.models.Receipt;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TwoItemsRuleTest {
    ReceiptRule receiptRule = new TwoItemsRule();

    @Test
    public void testEveryTwoItemsOnTheReceiptOddSize(){
        Receipt receipt = Receipt.builder()
                .retailer("Target")
                .purchaseDate("2022-01-01")
                .purchaseTime("13:01")
                .total(Double.parseDouble("35.35"))
                .items(Arrays.asList(
                        Item.builder()
                                .shortDescription("Mountain Dew 12PK")
                                .price(Double.parseDouble("6.49"))
                                .build(),
                        Item.builder()
                                .shortDescription("Emils Cheese Pizza")
                                .price(Double.parseDouble("12.25"))
                                .build(),
                        Item.builder()
                                .shortDescription("Knorr Creamy Chicken")
                                .price(Double.parseDouble("1.26"))
                                .build(),
                        Item.builder()
                                .shortDescription("Doritos Nacho Cheese")
                                .price(Double.parseDouble("3.35"))
                                .build(),
                        Item.builder()
                                .shortDescription("Klarbrunn 12-PK 12 FL OZ")
                                .price(Double.parseDouble("12.00"))
                                .build()
                ))
                .build();
        int points = receiptRule.calculatePoints(receipt);
        assertEquals(10, points);
    }

    @Test
    public void testEveryTwoItemsOnTheReceiptEvenSize(){
        Receipt receipt = Receipt.builder()
                .retailer("Target")
                .purchaseDate("2022-01-01")
                .purchaseTime("13:01")
                .total(Double.parseDouble("35.35"))
                .items(Arrays.asList(
                        Item.builder()
                                .shortDescription("Mountain Dew 12PK")
                                .price(Double.parseDouble("6.49"))
                                .build(),
                        Item.builder()
                                .shortDescription("Emils Cheese Pizza")
                                .price(Double.parseDouble("12.25"))
                                .build(),
                        Item.builder()
                                .shortDescription("Knorr Creamy Chicken")
                                .price(Double.parseDouble("1.26"))
                                .build(),
                        Item.builder()
                                .shortDescription("Doritos Nacho Cheese")
                                .price(Double.parseDouble("3.35"))
                                .build(),
                        Item.builder()
                                .shortDescription("Klarbrunn 12-PK 12 FL OZ")
                                .price(Double.parseDouble("12.00"))
                                .build(),
                        Item.builder()
                                .shortDescription("Klarbrunn 12-PK 12 FL OZ")
                                .price(Double.parseDouble("12.00"))
                                .build()
                ))
                .build();
        int points = receiptRule.calculatePoints(receipt);
        assertEquals(15, points);
    }
}
