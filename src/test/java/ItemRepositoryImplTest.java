import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import domain.CartItemRepository;
import domain.item.Item;
import domain.item.ItemData;
import domain.item.ItemRepository;
import persistence.ItemRepositoryImpl;

public class ItemRepositoryImplTest extends FullContextApplicationTest {

    ItemRepository underTest;

    @Mock
    CartItemRepository cartItemRepository;

    @BeforeEach
    void init() {
        underTest = new ItemRepositoryImpl(itemsMemory, cartItemRepository);
    }

    @Test
    void createAndGetItem_shouldPutItemToMemoryAndGetIt() {
        ItemData itemData = ItemData.builder()
                .name("item")
                .description("description")
                .price("90")
                .build();

        Item createResult = underTest.create(itemData);
        Item getResult = underTest.get(createResult.getId());

        assertThat(createResult.getData())
                .isEqualTo(itemData);
        assertThat(itemsMemory).containsValue(itemData);
        assertThat(getResult.getData())
                .isEqualTo(itemData);
    }

    @Test
    void getItem_itemDoesNotExist_shouldThrowException() {
        assertThatThrownBy(() -> underTest.get("non-existing-item"))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessageContaining("not exist.");
    }

}
