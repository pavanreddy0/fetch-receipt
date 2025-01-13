package receipt_processor.receipt_processor.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import receipt_processor.receipt_processor.Service.ReceiptService;
import receipt_processor.receipt_processor.dto.IdDTO;
import receipt_processor.receipt_processor.dto.PointsDTO;
import receipt_processor.receipt_processor.models.Receipt;
import receipt_processor.receipt_processor.util.ApiResponse;

import java.util.NoSuchElementException;
import java.util.UUID;

@RestController
@RequestMapping("/receipts")
@RequiredArgsConstructor
public class ReceiptController {
    @Autowired
    private ReceiptService receiptService;

    @PostMapping("/process")
    public ResponseEntity<?> addReceipt(@RequestBody Receipt receipt){
        try{
            System.out.println("receipt " + receipt);
            UUID receipt_id = receiptService.processReceipt(receipt);
            IdDTO idDTO = new IdDTO(receipt_id);
            return new ResponseEntity<>(idDTO, HttpStatus.CREATED);
        } catch (Exception e){
            ApiResponse<String> response = new ApiResponse<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}/points")
    public ResponseEntity<?> getPointsForReceipt(@PathVariable UUID id){
        try {
            PointsDTO pointsDTO = receiptService.getPoints(id);
            return new ResponseEntity<>(pointsDTO, HttpStatus.OK);
        } catch (NoSuchElementException exception){
            ApiResponse<String> response = new ApiResponse<>("Receipt with this id does not exist",  HttpStatus.NOT_FOUND, null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            ApiResponse<String> response = new ApiResponse<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
