package jpa.app.shop.service;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import jpa.app.shop.domain.Address;
import jpa.app.shop.domain.Member;
import jpa.app.shop.domain.Order;
import jpa.app.shop.domain.OrderStatus;
import jpa.app.shop.domain.item.Book;
import jpa.app.shop.exception.NotEnoughStockException;
import jpa.app.shop.repository.OrderRepository;

@SuppressWarnings("NonAsciiCharacters")
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderServiceTest {

	@Autowired
	EntityManager em;

	@Autowired
	OrderService orderService;

	@Autowired
	OrderRepository orderRepository;



	@Test
	public void 상품주문() {
		// given
		final int orderCount = 2;
		Member member = createMember();
		Book book = createBook("시골 JPA", 10_000, 20);

		// when
		Long savedOrderId = orderService.order(member.getId(), book.getId(), orderCount);
		Order findOrder = orderRepository.findOne(savedOrderId);

		// then
		assertEquals("상품 주문시 상태는 ORDER", OrderStatus.ORDER, findOrder.getStatus());
		assertEquals("주문한 상품의 종류 수가 정확해야 한다", 1, findOrder.getOrderItems().size());
		assertEquals("주문 가격은 가격 * 수량이다", 10_000 * orderCount, findOrder.getTotalPrice());
		assertEquals("주문 수량만큼 재고가 줄어야 한다", 20 - orderCount, book.getStockQuantity());
	}

	@Test
	public void 주문취소() {
		// given
		Member member = createMember();
		Book book = createBook("시골 JPA", 10_000, 10);
		int orderCount = 2;
		Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

		// when
		orderService.cancelOrder(orderId);

		Order findOrder = orderRepository.findOne(orderId);

		// then
		assertEquals("상품 주문시 상태는 CANCEL", OrderStatus.CANCEL, findOrder.getStatus());
		assertEquals("주문을 취소한 만큼 재고가 다시 올라야 한다", 10, book.getStockQuantity());
	}

	@Test(expected = NotEnoughStockException.class)
	public void 상품주문_재고수량초과() {
		// given
		Member member = createMember();
		Book book = createBook("시골 JPA", 10_000, 10);
		int orderCount = 20;

		// when
		Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

		// then
		fail("재고 수량 부족 예외가 발생해야 한다.");
	}

	private Member createMember() {
		Member member = new Member();
		member.setName("user a");
		member.setAddress(new Address("서울", "강가", "123-123"));
		em.persist(member);
		return member;
	}

	private Book createBook(String name, int price, int stockQuantity) {
		Book book = new Book();
		book.setName(name);
		book.setPrice(price);
		book.setStockQuantity(stockQuantity);
		em.persist(book);
		return book;
	}
}