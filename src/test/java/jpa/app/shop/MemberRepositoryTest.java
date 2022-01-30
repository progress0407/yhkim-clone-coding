package jpa.app.shop;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class) // 스프링과 관련된 테스트를 할 것이다
@SpringBootTest
public class MemberRepositoryTest {

	@Autowired
	MemberRepository memberRepository;

	@Test
	@Transactional
	@Rollback(false)
	public void testMember() {
		// given
		Member member = new Member();
		member.setUsername("memberA");

		// when
		Long saveId = memberRepository.save(member);
		Member findMember = memberRepository.find(saveId);

		// then
		Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
		Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
		Assertions.assertThat(findMember).isEqualTo(member);
		System.out.println("(findMember == member) = " + (findMember == member));

		System.out.println("member hashCode = " + member.hashCode());
		System.out.println("findMember hashCode = " + findMember.hashCode());
	}

}