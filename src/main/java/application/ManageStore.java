package application;

import java.util.List;

import org.springframework.stereotype.Component;

import domain.item.ItemData;
import domain.item.ItemRepository;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ManageStore {

    private final ItemRepository itemRepository;

    public List<String> initializeStoreItems(List<ItemData> itemDataList) {
        itemRepository.getAll().forEach(itemRepository::delete);
        itemDataList.forEach(itemRepository::create);
        return itemRepository.getAll();
    }

    public void removeStoreItems(List<String> itemIdList) {
        itemIdList.forEach(itemRepository::delete);
    }
}
