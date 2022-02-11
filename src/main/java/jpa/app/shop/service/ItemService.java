package jpa.app.shop.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	public List<Item> findAll() {
		return itemRepository.findAll();
	}

	public Item findOne(Long id) {
		return itemRepository.findOne(id);
	}
}
