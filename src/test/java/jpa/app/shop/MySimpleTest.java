package jpa.app.shop;

import static jpa.app.shop.validator.MemberValidator.*;
import static org.assertj.core.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import jpa.app.shop.domain.Address;
import jpa.app.shop.domain.item.Album;
import jpa.app.shop.domain.Category;
import jpa.app.shop.domain.Delivery;
import jpa.app.shop.domain.DeliveryStatus;
import jpa.app.shop.domain.Member;
import jpa.app.shop.domain.Order;
import jpa.app.shop.domain.OrderItem;
import jpa.app.shop.domain.OrderStatus;
import jpa.app.shop.exception.MemberDuplicateException;
import jpa.app.shop.repository.MemberRepository;
import jpa.app.shop.service.MemberService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MySimpleTest {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private MemberService memberService;

	@Test
	@Transactional
	@Rollback(false)
	@DisplayName("전체 도메인 테스트")
	public void first_test() {
		// given

		Address address = new Address("city a", "street a", "zip code a");

		Member member = new Member();
		member.setName("member a");
		member.setAddress(address);

		Order order = new Order();
		order.setOrderDate(LocalDateTime.now());
		order.setOrderStatus(OrderStatus.ORDER);

		member.addOrders(order);

		Delivery delivery = new Delivery();
		delivery.setAddress(address);
		delivery.setDeliveryStatus(DeliveryStatus.READY);

		Album album = new Album();
		album.setStockQuantity(1_000);
		album.setArtist("sw cho");
		album.setEtc("and so on ...");

		OrderItem orderItem = new OrderItem();
		orderItem.setOrder(order);
		orderItem.setItem(album);

		Category superCategory = new Category();
		superCategory.setName("root category");

		Category subCategoryA = new Category();
		subCategoryA.setName("sub category a");
		subCategoryA.setParent(subCategoryA);

		Category subCategoryB = new Category();
		subCategoryB.setName("sub category b");
		subCategoryB.setParent(superCategory);

		superCategory.addCategories(subCategoryA, subCategoryB);

		album.setCategories(List.of(superCategory, subCategoryB));


		em.persist(order);
		em.persist(delivery);
		em.persist(orderItem);
		em.persist(album);
		em.persist(superCategory);


		// when
		Long saveMemberId = memberRepository.save(member);
		Member findMember = memberRepository.findOne(saveMemberId);


		// then
		assertThat(findMember).isEqualTo(member);
	}

	@Test
	@Transactional
	@Rollback(false)
	@DisplayName("잊을만 하면 해보는 N + 1 테스트")
	public void nPlusOneTest() {
		// given
		Member userA = new Member();
		userA.setName("user a");

		Member userB = new Member();
		userB.setName("user b");


		Order orderA = new Order();
		Order orderB = new Order();
		Order orderC = new Order();

		userA.addOrders(orderA, orderB);
		userB.addOrders(orderC);

		// when
		System.out.println("#1 userA.getOrders().getClass() = " + userA.getOrders().getClass());
		em.persist(userA);
		em.persist(userB);

		System.out.println("#2 userA.getOrders().getClass() = " + userA.getOrders().getClass());

		em.flush();
		em.clear();

		System.out.println("#3 userA.getOrders().getClass() = " + userA.getOrders().getClass());

		em.createQuery("select o from Order as o", Order.class).getResultList();

		// then

	}

	@Test
	@Transactional
	@Rollback(false)
	public void 중복된_회원이_존재할_경우() {
		// given
		final String sameName = "same name something";
		Member userA = new Member();
		userA.setName(sameName);

		Member userB = new Member();
		userB.setName(sameName);

		// when
		memberService.join(userA);

		em.flush();
		em.clear();

		// then
		assertThatThrownBy(() -> {
			memberService.join(userB);
		}).isInstanceOf(MemberDuplicateException.class)
			.hasMessageContaining(MEMBER_DUPLICATE_EX_MSG);
	}
}
