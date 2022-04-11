package domain;

import java.util.List;

import domain.cart.Cart;
import domain.item.Item;

public interface CartItemRepository {

    void addCart(String cartId);

    void removeCart(String cartId);

    void removeItemFromAllCarts(String itemId);

    void addItemToCart(String cartId, String itemId);

    void removeItemFromCart(String cartId, String itemId);

    void removeAllCartItems(String cartId);

    List<String> getAllCartItems(String cartId);

    boolean isItemInCart(String cartId, String itemId);

    int getCartItemsNumber(String cartId);
}
