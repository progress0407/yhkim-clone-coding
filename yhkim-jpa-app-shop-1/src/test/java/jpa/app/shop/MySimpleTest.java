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
