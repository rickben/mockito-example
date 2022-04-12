import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ShopperCartAnnotationWithExtensionTest {
    @Mock
    private Cart mockedCart;

    private Shopper underTest;

    @BeforeEach
    void init() {
        underTest = new Shopper(mockedCart);
    }

    @Test
    void getShoppingContent_shouldReturn() {
        String shoppingContent = "All shopping content";
        when(mockedCart.getShoppingContent()).thenReturn(shoppingContent);
        assertThat(underTest.getShoppingContent()).isEqualTo(shoppingContent);
    }
}
