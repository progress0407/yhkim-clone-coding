package jpa.app.shop.jpastudy;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import jpa.app.shop.domain.Address;
import jpa.app.shop.domain.Member;

@SpringBootTest
public class MergeTest {

	@PersistenceContext
	private EntityManager em;

	private Member member;

	private Long savedId;

	public MergeTest(EntityManager em) {
		this.em = em;
	}

	@Transactional
	@BeforeEach
	void setUp() {
		member = new Member();
		member.setName("philz");
		member.setAddress(new Address("city a", "street a", "zipcode a"));
		em.persist(member);
		em.flush();
		savedId = member.getId();
	}

	@Test
	void mergeTest() {
		Member findMember = em.find(Member.class, savedId);
		System.out.println("findMember = " + findMember);
	}
}
