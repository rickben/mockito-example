import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ShopperCartSimpleMockTest {

    private Cart mockedCart;

    private Shopper underTest;

    @BeforeEach
    void init() {
        mockedCart = Mockito.mock(Cart.class);
        underTest = new Shopper(mockedCart);
    }

    @Test
    void getShoppingContent_shouldReturn() {
        String shoppingContent = "All shopping content";
        when(mockedCart.getShoppingContent()).thenReturn(shoppingContent);

        assertThat(underTest.getCartShoppingContent()).isEqualTo(shoppingContent);
    }
}
