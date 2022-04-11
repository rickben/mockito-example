package persistence;

import static utils.Utils.createIdentifier;

import java.util.Map;

import org.springframework.stereotype.Repository;

import domain.CartItemRepository;
import domain.cart.Cart;
import domain.cart.CartData;
import domain.cart.CartRepository;
import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class CartRepositoryImpl implements CartRepository {

    private final Map<String, CartData> carts;

    private final CartItemRepository cartItemRepository;

    @Override
    public Cart create(CartData data) {
        String id = createIdentifier();
        carts.put(id, data);
        cartItemRepository.addCart(id);
        return Cart.of(id, data);
    }

    @Override
    public void delete(String id) {
        carts.remove(id);
        cartItemRepository.removeCart(id);
    }

    @Override
    public Cart get(String id) {
        return Cart.of(id, carts.get(id));
    }
}
