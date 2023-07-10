package jpa.app.shop.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import jpa.app.shop.domain.item.Book;
import jpa.app.shop.domain.item.Item;
import jpa.app.shop.repository.ItemRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemService {

	private final ItemRepository itemRepository;

	@Transactional
	public Long save(Item item) {
		return itemRepository.save(item);
	}

	/**
	 * 이것은 마치 em.merge() 가 동작하는 방식과 같다
	 * Item mergedItem = em.merge(item);
	 * 이때 item은 준영속, mergedItem 은 영속상태이다
	 */
	@Transactional
	public Item updateItem(Long itemId, Book param) {
		Item findItem = itemRepository.findOne(itemId);
/*
		findItem.setPrice(param.getPrice());
		findItem.setName(param.getName());
		findItem.setStockQuantity(param.getStockQuantity());
*/
		findItem.changeItem(param);

		return findItem;
	}

	public List<Item> findItems() {
		return itemRepository.findAll();
	}

	public Item findOne(Long id) {
		return itemRepository.findOne(id);
	}
}
