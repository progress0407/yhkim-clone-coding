package jpa.app.shop.jpastudy;

import static org.assertj.core.api.Assertions.*;

import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import jpa.app.shop.domain.Member;
import jpa.app.shop.repository.MemberRepository;
import jpa.app.shop.service.MemberService;

@SuppressWarnings("NonAsciiCharacters")
@RunWith(SpringRunner.class)
@SpringBootTest
public class JpaStudyTest {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private MemberService memberService;

	private Member member;

	@BeforeEach
	void setUp() {
		// given
		member = new Member();
		member.setName("user a");
		memberService.removeAll();
	}

	@Transactional
	@Test
	public void 저장한_객체와_꺼낸_객체는_당연히_같다() {

		// when
		Long savedId = memberService.join(member);
		Member findMember = memberService.findOne(savedId);

		// then
		assertThat(findMember).isEqualTo(member);
	}

	@Test
	public void 영속성_컨텍스트가_다를경우_객체는_서로_같지않다() {

		// when

		// 영속성 컨텍스트_시작 --->
		Long savedId = memberService.join(member);
		// <--- 영속성 컨텍스트 종료

		// 영속성 컨텍스트_시작 --->
		Member findMember = 회원_찾기(savedId);
		// <--- 영속성 컨텍스트 종료

		// then
		assertThat(findMember == member).isFalse();
		assertThat(findMember).isNotEqualTo(member);
	}

	@Transactional
	@Test
	public void 영속성_컨텍스트가_다를경우_객체는_서로_같지_않지만_동일한_트랜잭션_범위라면_서로같다() {

		// when
		Long savedId = memberService.join(member);
		Member findMember = 회원_찾기(savedId);

		// then
		assertThat(findMember == member).isTrue();
		assertThat(findMember).isEqualTo(member);
	}

	@Transactional
	public Member 회원_찾기(Long id) {
		return memberService.findOne(id);
	}

	@Transactional
	@Test
	public void _1차_캐시를_지우면_두_객체는_서로_다르다() {
		em.persist(member);
		Long savedId = member.getId();
		em.flush();
		em.clear();

		Member findMember = em.find(Member.class, savedId);
		assertThat(findMember).isNotEqualTo(member);
	}



}
