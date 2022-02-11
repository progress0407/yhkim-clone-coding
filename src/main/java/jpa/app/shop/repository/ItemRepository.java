package jpa.app.shop.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import jpa.app.shop.domain.item.Item;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

	private final EntityManager em;

	public Long save(Item item) {
		// 처음에는 id가 없다, null 유무로 신규 객체임을 알 수 있다
		if (item.getId() == null) {
			em.persist(item);
		} else {
			em.merge(item);
		}
		return item.getId();
	}

	public Item findOne(Long id) {
		return em.find(Item.class, id);
	}

	public List<Item> findAll() {
		return em.createQuery("select i from Item i", Item.class)
			.getResultList();
	}

	public List<Item> findByName(String name) {
		return em.createQuery("select i from Item i where i.name = :itemName", Item.class)
			.setParameter("itenName", name)
			.getResultList();
	}

}
