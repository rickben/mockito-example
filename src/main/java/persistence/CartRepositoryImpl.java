package persistence;

import static utils.Utils.createIdentifier;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import domain.CartItemRepository;
import domain.cart.Cart;
import domain.cart.CartData;
import domain.cart.CartRepository;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CartRepositoryImpl implements CartRepository {

    private final Map<String, CartData> carts = new HashMap<>();

    private final CartItemRepository cartItemRepository;

    @Override
    public Cart create(CartData data) {
        String id = createIdentifier();
        carts.put(id, data);
        Cart cart = Cart.of(id ,data);
        cartItemRepository.addCart(cart);
        return cart;
    }

    @Override
    public void delete(String id) {
        carts.remove(id);
    }
}
