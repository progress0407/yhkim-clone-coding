package jpa.app.shop.repository;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import jpa.app.shop.domain.Order;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class OrderRepository {

	private final EntityManager em;

	public void save(Order order) {
		em.persist(order);
	}

	public Order findOne(Long id) {
		return em.find(Order.class, id);
	}

	// public List<Order> findAll(OrderSearch orderSearch) {}
}
