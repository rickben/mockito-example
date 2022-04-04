package domain.cart;

public interface CartRepository {

    Cart create(CartData data);

    void delete(String id);
}
