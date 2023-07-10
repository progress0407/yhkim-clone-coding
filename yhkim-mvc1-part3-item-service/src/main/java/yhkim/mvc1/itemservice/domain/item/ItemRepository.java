package yhkim.mvc1.itemservice.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ItemRepository {

    public static final Map<Long, Item> store = new ConcurrentHashMap<>(); // static
    private static long sequence = 0L;

    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    public Item findById(Long id) {
        return store.get(id);
    }

    public Item findByName(String itemName) {
//        store.forEach((k, v) -> {
//            if(v.getItemName().equals(itemName)) {
//                return store.get(0);
//            }
//        });

        return store.get(0);
    }

    public List<Item> findAll() {
        return new ArrayList<>(store.values());
//        return store.values();
    }

    public void update(Long itemId, Item updateParam) {
//        store.replace(itemId, updateParam);
        Item findItem = findById(itemId);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }

    public void clearStore() {
        store.clear();
    }

}

