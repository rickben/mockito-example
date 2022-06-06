import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

public class ShopperCartOpenMocksTest {
    @Mock
    private Cart mockedCart;

    private Shopper underTest;

    @BeforeEach
    void init() {
        openMocks(this);
        underTest = new Shopper(mockedCart);
    }

    @Test
    void getShoppingContent_shouldReturn() {
        String shoppingContent = "All shopping content";
        when(mockedCart.getShoppingContent()).thenReturn(shoppingContent);

        assertThat(underTest.getCartShoppingContent()).isEqualTo(shoppingContent);
    }
}
