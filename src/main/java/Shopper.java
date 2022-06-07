import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Shopper {

    private Cart cart;

    public String getCartShoppingContent() {
        return cart.getShoppingContent();
    }

    public String getCartShoppingContentTwice() {
        cart.getShoppingContent();
        return cart.getShoppingContent();
    }

    public String getCartShoppingContentByYear(Integer year) {
        return cart.getShoppingContentByYear(year);
    }

    public String getCartShoppingContent(Integer year, String month) {
        return cart.getShoppingContent(year, month);
    }

    public String getShoppingContentWithNewValue() {
        return cart.getShoppingContentWithNewValue(53);
    }

    public void getNonExistingCartContent() {
        try {
            cart.getNonExistingContent();
        } catch (Exception e) {
            throw new IllegalStateException("Non-existing content.");
        }
    }

    public void printCartShoppingContent() {
        cart.printShoppingContent();
    }
}
