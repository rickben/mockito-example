package persistence;

import static utils.Utils.createIdentifier;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Repository;

import domain.CartItemRepository;
import domain.item.Item;
import domain.item.ItemData;
import domain.item.ItemRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Repository
public class ItemRepositoryImpl implements ItemRepository {

    private final Map<String, ItemData> items;

    private final CartItemRepository cartItemRepository;

    @Override
    public Item create(ItemData data) {
        String id = createIdentifier();
        items.put(id, data);
        return Item.of(id, data);
    }

    @Override
    public Item get(String id) {
        if (!items.containsKey(id)) {
            throw new NoSuchElementException(MessageFormat.format("Item with id: {0} does not exist.", id));
        }
        return Item.of(id, items.get(id));
    }

    @Override
    public void update(String id, ItemData data) {
        if (!items.containsKey(id)) {
            throw new NoSuchElementException(MessageFormat.format("Item with id: {} doesn't exist.", id));
        }
        items.put(id, data);
    }

    @Override
    public void delete(String id) {
        items.remove(id);
        cartItemRepository.removeItemFromAllCarts(id);
    }

    @Override
    public List<String> getAll() {
        return new ArrayList<>(items.keySet());
    }
}
