package domain.item;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemData {
    String name;
    String description;
    String price;
}
