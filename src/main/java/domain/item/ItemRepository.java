package domain.item;

import java.util.List;

public interface ItemRepository {

    Item create(ItemData data);

    Item get(String id);

    void update(String id, ItemData data);

    void delete(String id);

    List<String> getAll();
}
