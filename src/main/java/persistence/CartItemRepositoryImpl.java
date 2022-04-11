package persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import domain.CartItemRepository;
import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class CartItemRepositoryImpl implements CartItemRepository {

    private final Map<String, List<String>> cartIdToItemsIds;

    @Override
    public void addCart(String cartId) {
        cartIdToItemsIds.put(cartId, new ArrayList<>());
    }

    @Override
    public void removeCart(String cartId) {
        cartIdToItemsIds.remove(cartId);
    }

    @Override
    public void removeItemFromAllCarts(String itemId) {
        cartIdToItemsIds.forEach(
                (cartId, listItemIds) ->
                        listItemIds.remove(itemId)
        );
    }

    @Override
    public void addItemToCart(String cartId, String itemId) {
        if (!cartIdToItemsIds.containsKey(cartId)) {
            throw new IllegalStateException("Can not add items to non existing cart with id: " + cartId);
        }
        List<String> itemsIds = cartIdToItemsIds.get(cartId);
        itemsIds.add(itemId);
    }

    @Override
    public void removeItemFromCart(String cartId, String itemId) {
        if (!cartIdToItemsIds.containsKey(cartId)) {
            return;
        }
        List<String> itemsIds = cartIdToItemsIds.get(cartId);
        itemsIds.remove(itemId);
    }

    @Override
    public void removeAllCartItems(String cartId) {
        if (cartIdToItemsIds.containsKey(cartId)) {
            cartIdToItemsIds.put(cartId, new ArrayList<>());
        }
    }

    @Override
    public List<String> getAllCartItems(String cartId) {
        if (!cartIdToItemsIds.containsKey(cartId)) {
            return new ArrayList<>();
        }
        return cartIdToItemsIds.get(cartId);
    }

    @Override
    public boolean isItemInCart(String cartId, String itemId) {
        return getAllCartItems(cartId).contains(itemId);
    }

    @Override
    public int getCartItemsNumber(String cartId) {
        return getAllCartItems(cartId).size();
    }
}
