import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Shopper {

    private Cart cart;

    public String getShoppingContent() {
        return cart.getShoppingContent();
    }
}
