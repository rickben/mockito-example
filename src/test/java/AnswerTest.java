import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AnswerTest {
    @Mock
    private Cart mockedCart;

    private Shopper underTest;
    private final String shoppingContent = "Pink dress and blue shoes";

    @BeforeEach
    void init() {
        underTest = new Shopper(mockedCart);
    }

    @Test
    void thenReturn_getShoppingContent() {
        when(mockedCart.getShoppingContent()).thenReturn(shoppingContent);

        assertThat(underTest.getCartShoppingContent()).isEqualTo(shoppingContent);
    }

    @Test
    void doAnswer_inlineAnswer_printShoppingContent() {
        doAnswer(invocation -> {
            System.out.println(shoppingContent);
            return null;
        }).when(mockedCart).printShoppingContent();

        underTest.printCartShoppingContent();
    }

    @Test
    void doAnswer_inlineAnswer_returnValue_getShoppingContent() {
        doAnswer(invocation -> {
            System.out.println(shoppingContent);
            return "content";
        }).when(mockedCart).getShoppingContent();

        assertThat(underTest.getCartShoppingContent()).isEqualTo("content");
    }

    @Test
    void doAnswer_answerObject_printShoppingContent() {
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) {
                System.out.println(shoppingContent);
                return null;
            }
        }).when(mockedCart).printShoppingContent();

        underTest.printCartShoppingContent();
    }

    @Test
    void thenAnswer_answerObject_getShoppingContent() {
        when(mockedCart.getShoppingContent()).thenAnswer((invocation -> {
            System.out.println(shoppingContent);
            return shoppingContent;
        }));

        assertThat(underTest.getCartShoppingContent()).isEqualTo(shoppingContent);
    }

    @Test
    void thenAnswer_answerObject_withParams_getShoppingContent() {
        when(mockedCart.getShoppingContentByYear(2000)).thenAnswer((invocation -> {
            int year = invocation.getArgument(0, Integer.class);
            return "content of year: " + year;
        }));

        assertThat(underTest.getCartShoppingContentByYear(2000)).isEqualTo("content of year: 2000");
    }
}
