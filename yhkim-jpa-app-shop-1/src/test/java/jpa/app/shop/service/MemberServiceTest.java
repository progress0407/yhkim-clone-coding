package jpa.app.shop.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import jpa.app.shop.domain.Member;
import jpa.app.shop.exception.MemberDuplicateException;
import jpa.app.shop.repository.MemberRepository;


@SuppressWarnings("NonAsciiCharacters")
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

	@Autowired MemberService memberService;
	@Autowired MemberRepository memberRepository;


	@Test
	@Rollback(false)
	public void 회원가입() {
		// given
		Member member = new Member();
		member.setName("cho");

		// when
		Long savedId = memberService.join(member);

		// then
		assertEquals(member, memberRepository.findOne(savedId));
	}

	@Test
	public void 중복_회원_예외() {
		// given
		final String sameName = "same name something";
		Member userA = new Member();
		userA.setName(sameName);

		Member userB = new Member();
		userB.setName(sameName);

		// when
		memberService.join(userA);
		try {
			memberService.join(userB);
		} catch (MemberDuplicateException e) {
			return;
		}

		// then
		fail("예외가 발생하여 이 코드에 도달하지 못해야 한다");
	}

	@Test(expected = MemberDuplicateException.class)
	public void 중복_회원_예외_V2() {
		// given
		final String sameName = "same name something";
		Member userA = new Member();
		userA.setName(sameName);

		Member userB = new Member();
		userB.setName(sameName);

		// when
		memberService.join(userA);
		memberService.join(userB);

		// then
		fail("예외가 발생하여 이 코드에 도달하지 못해야 한다");
	}
}