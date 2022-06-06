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
import org.mockito.ArgumentMatcher;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class VerificationTest {

    @Mock
    private Cart mockedCart;

    private Shopper underTest;

    @BeforeEach
    void init() {
        underTest = new Shopper(mockedCart);
    }

    @Test
    void getShoppingContentTwice_shouldVerifyTwoInvocations() {
        when(mockedCart.getShoppingContent()).thenReturn("blue shirt");

        underTest.getCartShoppingContentTwice();

        verify(mockedCart, times(2)).getShoppingContent();
    }

    @Test
    void getShoppingContent_shouldVerifyTotalInvocations() {
        when(mockedCart.getShoppingContent()).thenReturn("blue shirt");

        underTest.getCartShoppingContent();

        verify(mockedCart).getShoppingContent();

        underTest.getCartShoppingContentTwice();

        verify(mockedCart, times(3)).getShoppingContent();
    }

    @Test
    void getShoppingContent_withResetMocks_shouldVerifySeparateInvocations() {
        when(mockedCart.getShoppingContent()).thenReturn("blue shirt");

        underTest.getCartShoppingContent();

        verify(mockedCart).getShoppingContent();

        reset(mockedCart);

        underTest.getCartShoppingContentTwice();

        verify(mockedCart, times(2)).getShoppingContent();
    }

    @Test
    void getShoppingContentByYear_withArgMatcher_interface() {
        ArgumentMatcher<Integer> argMatcher = new ArgumentMatcher<Integer>() {
            @Override
            public boolean matches(Integer argument) {
                return argument > 2020;
            }
        };
        when(mockedCart.getShoppingContentByYear(argThat(argMatcher))).thenReturn("Summer clothes");

        underTest.getCartShoppingContentByYear(2022);

        verify(mockedCart).getShoppingContentByYear(argThat(argMatcher));
    }

    @Test
    void getShoppingContentByYear_withArgMatcher_lambda() {
        when(mockedCart.getShoppingContentByYear(anyInt())).thenReturn("Summer clothes");

        underTest.getCartShoppingContentByYear(2022);

        verify(mockedCart).getShoppingContentByYear(argThat(year -> year > 2020));
    }

    @Test
    void getShoppingContent_withAnyAndEq_complexCase() {
        when(mockedCart.getShoppingContent(eq(2002), any())).thenReturn("Other clothes");
        when(mockedCart.getShoppingContent(eq(2002), eq("april"))).thenReturn("Winter clothes");

        String result1 = underTest.getCartShoppingContent(2002, "april");

        verify(mockedCart).getShoppingContent(eq(2002), eq("april"));

        String result2 = underTest.getCartShoppingContent(2002, "november");

        verify(mockedCart, times(2)).getShoppingContent(eq(2002), any());

        assertThat(result1).isNotEqualTo(result2); // will it be equal?
    }

    @Test
    void getShoppingContent_withOnlyEq() {
        when(mockedCart.getShoppingContent(eq(2002), eq("april"))).thenReturn("Winter clothes");

        underTest.getCartShoppingContent(2002, "april");

        verify(mockedCart).getShoppingContent(eq(2002), eq("april"));
    }

    @Test
    void getShoppingContent_withEqAndInteger() { // problem test - raw values
        when(mockedCart.getShoppingContent(2002, eq("april"))).thenReturn("Winter clothes");

        underTest.getCartShoppingContent(2002, "april");

        verify(mockedCart).getShoppingContent(eq(2002), eq("april"));
    }
}