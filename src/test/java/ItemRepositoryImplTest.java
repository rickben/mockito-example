import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domain.item.Item;
import domain.item.ItemData;
import domain.item.ItemRepository;
import persistence.ItemRepositoryImpl;

public class ItemRepositoryImplTest extends FullContextApplicationTest {

    ItemRepository underTest;

    @BeforeEach
    void init() {
        underTest = new ItemRepositoryImpl(itemsMemory);
    }

    @Test
    void create_shouldPutItemToMemory() {
        ItemData itemData = ItemData.builder()
                .name("item")
                .description("description")
                .price("90")
                .build();

        Item result = underTest.create(itemData);

        assertThat(result.getData())
                .isEqualTo(itemData);
        assertThat(itemsMemory).containsValue(itemData);
    }
}