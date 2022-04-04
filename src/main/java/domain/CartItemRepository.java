package domain;

import java.util.List;

import domain.cart.Cart;
import domain.item.Item;

public interface CartItemRepository {

    void addCart(Cart cart);

    void addItemToCart(Cart cart, Item item);

    void removeItemFromCart(Cart cart, Item item);

    void removeAllCartItems(Cart cart);

    List<Item> getAllCartItems(Cart cart);

    boolean isItemInCart(Cart cart, Item item);

    int getCartItemsNumber(Cart cart);
}
