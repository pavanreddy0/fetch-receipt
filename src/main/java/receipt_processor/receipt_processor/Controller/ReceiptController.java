package receipt_processor.receipt_processor.Controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import receipt_processor.receipt_processor.Service.ReceiptService;
import receipt_processor.receipt_processor.dto.ErrorResponseDTO;
import receipt_processor.receipt_processor.dto.IdDTO;
import receipt_processor.receipt_processor.dto.PointsDTO;
import receipt_processor.receipt_processor.models.Receipt;

import java.util.NoSuchElementException;
import java.util.UUID;

@RestController
@RequestMapping("/receipts")
@RequiredArgsConstructor
@Slf4j
public class ReceiptController {
    @Autowired
    private ReceiptService receiptService;

    @PostMapping("/process")
    public ResponseEntity<?> addReceipt(@RequestBody Receipt receipt){
        log.info("Adding receipt " + receipt);
        try{
            UUID receipt_id = receiptService.processReceipt(receipt);
            IdDTO idDTO = new IdDTO(receipt_id);
            log.info("Successfully saved receipt " + receipt);
            return new ResponseEntity<>(idDTO, HttpStatus.CREATED);
        } catch (Exception e){
            log.error("Error while saving receipt " + e.getMessage());

            String message = "The receipt is invalid";
            ErrorResponseDTO response = ErrorResponseDTO.builder().description(message).build();
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}/points")
    public ResponseEntity<?> getPointsForReceipt(@PathVariable UUID id){
        log.info("Get points for receipt with id " + id);
        try {
            PointsDTO pointsDTO = receiptService.getPoints(id);
            log.info("Successfully fetched points for the receipt with id " + id);
            return new ResponseEntity<>(pointsDTO, HttpStatus.OK);
        } catch (NoSuchElementException exception){
            log.error("Receipt does not exist for this id " + id);

            String message = "No receipt found for that ID.";
            ErrorResponseDTO response = ErrorResponseDTO.builder().description(message).build();
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            log.error("Error occurred while fetching receipt with id " + id);

            String message = "Internal Server Error";
            ErrorResponseDTO response = ErrorResponseDTO.builder().description(message).build();
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
