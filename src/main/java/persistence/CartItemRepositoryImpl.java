package persistence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import domain.CartItemRepository;
import domain.cart.Cart;
import domain.item.Item;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CartItemRepositoryImpl implements CartItemRepository {

    Map<Cart, List<Item>> cartToItems = new HashMap<>();

    @Override
    public void addCart(Cart cart) {
        cartToItems.put(cart, new ArrayList<>());
    }

    @Override
    public void addItemToCart(Cart cart, Item item) {
        if (!cartToItems.containsKey(cart)) {
            throw new IllegalStateException("Can not add items to non existing cart");
        }
        List<Item> items = cartToItems.get(cart);
        items.add(item);
    }

    @Override
    public void removeItemFromCart(Cart cart, Item item) {
        if (!cartToItems.containsKey(cart)) {
            return;
        }
        List<Item> items = cartToItems.get(cart);
        items.remove(item);
    }

    @Override
    public void removeAllCartItems(Cart cart) {
        cartToItems.put(cart, new ArrayList<>());
    }

    @Override
    public List<Item> getAllCartItems(Cart cart) {
        if (!cartToItems.containsKey(cart)) {
            return new ArrayList<>();
        }
        return cartToItems.get(cart);    }

    @Override
    public boolean isItemInCart(Cart cart, Item item) {
        return getAllCartItems(cart).contains(item);
    }

    @Override
    public int getCartItemsNumber(Cart cart) {
        return getAllCartItems(cart).size();
    }
}
