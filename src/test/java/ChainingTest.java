import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ChainingTest {
    @Mock
    private Cart mockedCart;

    private Shopper underTest;

    @BeforeEach
    void init() {
        underTest = new Shopper(mockedCart);
    }

    @Test
    void getShoppingContent_chainDifferentReturnValues() {
        when(mockedCart.getShoppingContent())
                .thenReturn("Blue shoes")
                .thenReturn("Black skirt")
                .thenAnswer((invocation -> {
                    System.out.println("Pink dress");
                    return "Pinked dress";
                }))
                .thenThrow(new RuntimeException("I ran out of data"));

        assertThat(underTest.getCartShoppingContent()).isEqualTo("Blue shoes");
        assertThat(underTest.getCartShoppingContent()).isEqualTo("Black skirt");
        assertThat(underTest.getCartShoppingContent()).isEqualTo("Pinked dress");
        assertThatThrownBy(() -> underTest.getCartShoppingContent())
                .isInstanceOf(RuntimeException.class)
                .hasMessage("I ran out of data");
    }
}
