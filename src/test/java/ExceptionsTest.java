import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

public class ExceptionsTest {
    @Mock
    private Cart mockedCart;

    private Shopper underTest;

    @BeforeEach
    void init() {
        openMocks(this);
        underTest = new Shopper(mockedCart);
    }


    @Test
    void doThrow_printShoppingContent() {
        doThrow(IllegalStateException.class).when(mockedCart).getNonExistingContent();

        assertThatThrownBy(() -> underTest.getNonExistingCartContent())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Non-existing content.");
    }


    @Test
    void doThrow_withExceptionStubbing() {
        when(mockedCart.getShoppingContent()).thenThrow(RuntimeException.class);
        doThrow(IllegalStateException.class).when(mockedCart).getShoppingContent(); // doesn't invoke the stubbed exception

        assertThatThrownBy(() -> underTest.getCartShoppingContent())
                .isInstanceOf(IllegalStateException.class);
    }


    @Disabled
    @Test
    void thenThrow_withExceptionStubbing() {
        when(mockedCart.getShoppingContent()).thenThrow(RuntimeException.class);
        when(mockedCart.getShoppingContent()).thenThrow(IllegalStateException.class); // invokes the stubbed exception

        assertThatThrownBy(() -> underTest.getCartShoppingContent())
                .isInstanceOf(IllegalStateException.class);
    }
}
