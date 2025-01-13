package receipt_processor.receipt_processor;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import receipt_processor.receipt_processor.Controller.ReceiptController;
import receipt_processor.receipt_processor.Repository.ItemRepository;
import receipt_processor.receipt_processor.Repository.ReceiptRepository;
import receipt_processor.receipt_processor.Service.ReceiptService;
import receipt_processor.receipt_processor.dto.PointsDTO;
import receipt_processor.receipt_processor.models.Item;
import receipt_processor.receipt_processor.models.Receipt;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ReceiptProcessorApplicationTests {


	@Autowired
	private ReceiptController receiptController;
	@Autowired
	private ReceiptService receiptService;
	@Autowired
	private ReceiptRepository receiptRepository;
	@Autowired
	private ItemRepository itemRepository;

	@Test
	void postReceipt(){
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

		UUID receiptId = receiptService.processReceipt(receipt);
		assertNotNull(receiptId);

//		Check if receipt exists
		Optional<Receipt> saved_receipt = receiptRepository.findById(receiptId);
		assert(saved_receipt.isPresent());

//		Check items size
		List<Item> items = itemRepository.findByReceipt(saved_receipt.get());
		assertEquals(items.size(), 5);

//		Check points
		PointsDTO pointsDTO = receiptService.getPoints(receiptId);
		assertEquals(28, pointsDTO.getPoints());

		receiptRepository.deleteAllById(Collections.singleton(receiptId));
		items.forEach(item -> itemRepository.deleteById(item.getItem_id()));
	}

}
