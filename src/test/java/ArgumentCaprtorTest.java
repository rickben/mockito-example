import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatcher;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ArgumentCaprtorTest {
    @Mock
    Cart mockedCart;

    Shopper underTest;

    @Captor
    ArgumentCaptor<Integer> argCaptor; // catches arguments of called methods

    @BeforeEach
    void init() {
        underTest = new Shopper(mockedCart);
    }

    @Test
    public void testMultipleValuesCaptured() {
        mockedCart.getShoppingContentByYear(2000);
        mockedCart.getShoppingContentByYear(2010);

        verify(mockedCart, times(2)).getShoppingContentByYear(argCaptor.capture());

        assertThat(argCaptor.getAllValues().get(0)).isEqualTo(2000);
        assertThat(argCaptor.getAllValues().get(1)).isEqualTo(2010);
    }

    @Test
    public void testValueWeHaveNoAccessToFromTest() {
        when(mockedCart.getShoppingContentWithNewValue(anyInt())).thenReturn("new shoes");

        underTest.getShoppingContentWithNewValue();

        verify(mockedCart).getShoppingContentWithNewValue(argCaptor.capture());
        assertThat(argCaptor.getValue()).isEqualTo(53);
    }
}