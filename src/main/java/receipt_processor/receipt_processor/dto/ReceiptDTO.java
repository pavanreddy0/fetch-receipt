package receipt_processor.receipt_processor.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReceiptDTO {
    private String retailer;
    private String purchaseDate;
    private String purchaseTime;
    private List<ItemDTO> items;
    private String total;
}
