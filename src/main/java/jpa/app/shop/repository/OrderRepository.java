package jpa.app.shop.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import jpa.app.shop.domain.Order;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

	private final EntityManager em;

	public void save(Order order) {
		em.persist(order);
	}

	public Order findOne(Long id) {
		return em.find(Order.class, id);
	}

	// 동적인 것은 queryDsl 로 뽑자 !
	public List<Order> findAll(OrderSearch orderSearch) {

		return em.createQuery("select o from Order o join o.member m"
				+ " where o.status := status"
				+ " and m.name = :mame", Order.class)
			.setParameter("name", orderSearch.getMemberName())
			.setParameter("status", orderSearch.getOrderStatus())
			.setMaxResults(1000) // 최대 1000건
			.getResultList();
	}
}
