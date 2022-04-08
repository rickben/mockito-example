package domain.item;

public interface ItemRepository {

    Item create(ItemData data);

    Item get(String id);

    void update(String id, ItemData data);

    void delete(String id);
}
