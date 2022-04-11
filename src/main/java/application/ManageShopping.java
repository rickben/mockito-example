package application;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import domain.CartItemRepository;
import domain.cart.Cart;
import domain.cart.CartData;
import domain.cart.CartRepository;
import domain.item.Item;
import domain.item.ItemRepository;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ManageShopping {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ItemRepository itemRepository;

    public Cart startShoppingCart(CartData data) {
        return cartRepository.create(data);
    }

    public void removeFromShoppingCart(String cartId, List<String> itemIdList) {
        itemIdList
                .forEach(itemId ->
                        cartItemRepository.removeItemFromCart(cartId, itemId)
                );
    }

    public void addToShoppingCart(String cartId, List<String> itemIdList) {
        itemIdList
                .forEach(itemId ->
                        cartItemRepository.addItemToCart(cartId, itemId)
                );
    }

    public List<Item> finishShoppingCart(String cartId) {
        List<String> itemIdList = cartItemRepository.getAllCartItems(cartId);
        List<Item> res = itemIdList
                .stream()
                .map(itemRepository::get)
                .collect(Collectors.toList());
        cartRepository.delete(cartId);
        return res;
    }
}
