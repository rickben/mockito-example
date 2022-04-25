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

        assertThat(mockedCart.getShoppingContent()).isEqualTo(shoppingContent);
    }

    @Test
    void doAnswer_inlineAnswer_printShoppingContent() {
        doAnswer(invocation -> {
            System.out.println(shoppingContent);
            return null;
        }).when(mockedCart).printShoppingContent();

        mockedCart.printShoppingContent();
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

        mockedCart.printShoppingContent();
    }

    @Test
    void thenAnswer_answerObject_getShoppingContent() {
        when(mockedCart.getShoppingContent()).thenAnswer((invocation -> {
            System.out.println(shoppingContent);
            return shoppingContent;
        }));

        assertThat(mockedCart.getShoppingContent()).isEqualTo(shoppingContent);
    }

    @Test
    void doThrow_printShoppingContent() {
        doThrow(IllegalStateException.class).when(mockedCart).getNonExistingContent();

        assertThatThrownBy(() -> mockedCart.getNonExistingContent())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Non-existing content.");
    }
}
