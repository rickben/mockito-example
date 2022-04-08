package domain.cart;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class Cart {
    String id;
    CartData data;
}
