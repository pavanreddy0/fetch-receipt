package receipt_processor.receipt_processor.Service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import receipt_processor.receipt_processor.models.Item;
import receipt_processor.receipt_processor.models.Receipt;
import receipt_processor.receipt_processor.Repository.ItemRepository;
import receipt_processor.receipt_processor.Repository.ReceiptRepository;
import receipt_processor.receipt_processor.Rules.*;
import receipt_processor.receipt_processor.dto.PointsDTO;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class ReceiptService {
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ReceiptRepository receiptRepository;

    @Transactional
    public UUID processReceipt(Receipt receipt_data){
        log.info("Processing receipt" + receipt_data);
        try{
            Receipt entity = Receipt.builder()
                    .retailer(receipt_data.getRetailer())
                    .purchaseDate(receipt_data.getPurchaseDate())
                    .purchaseTime(receipt_data.getPurchaseTime())
                    .total(receipt_data.getTotal())
                    .build();

            Receipt receipt = receiptRepository.save(entity);

            List<Item> items = receipt_data.getItems().stream()
                    .map(item -> Item.builder()
                            .shortDescription(item.getShortDescription())
                            .price(item.getPrice())
                            .receipt(receipt)
                            .build()
                    )
                    .toList();

            itemRepository.saveAll(items);
            receipt.setPoints(getPointsForReceipt(receipt_data));
            receiptRepository.save(receipt);

            log.info("Saved receipt" + receipt);
            return receipt.getReceipt_id();

        } catch (Exception e){
            log.error("Internal Server Error " + e.getMessage());
            throw e;
        }
    }

    public int getPointsForReceipt(Receipt receipt){
        ReceiptPointsService receiptPointsService = getReceiptPointsService();
        return receiptPointsService.calculatePoints(receipt);
    }


    public ReceiptPointsService getReceiptPointsService(){
        List<ReceiptRule> rules = List.of(
                new AlphaNumericRetailerRule(),
                new RoundDollarTotalRule(),
                new MultipleOfQuarterTotalRule(),
                new TwoItemsRule(),
                new ItemDescriptionRule(),
                new OddDayPurchaseRule(),
                new PurchaseTimeRule()
        );
        return new ReceiptPointsService(rules);
    }

    public PointsDTO getPoints(UUID id) {
        log.info("Get points for Receipt with id " + id);
        Optional<Receipt> receipt = receiptRepository.findById(id);
        Receipt receiptInstance = receipt.orElseThrow(() -> new NoSuchElementException("Receipt not found"));
        log.info("Successfully fetched Points for receipt with id " + id);
        return new PointsDTO(receiptInstance.getPoints());
    }
}
