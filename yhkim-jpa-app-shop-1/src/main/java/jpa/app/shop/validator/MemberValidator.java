package jpa.app.shop.validator;

import java.util.List;

import org.springframework.stereotype.Component;

import jpa.app.shop.domain.Member;
import jpa.app.shop.exception.MemberDuplicateException;
import jpa.app.shop.repository.MemberRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberValidator {

	public static final String MEMBER_DUPLICATE_EX_MSG = "[ERROR] 중복된 회원이 존재합니다";
	private final MemberRepository memberRepository;

	public void validateDuplicateMember(Member member) {
		List<Member> findMember = memberRepository.findByName(member.getName());
		if (!findMember.isEmpty()) {
			throw new MemberDuplicateException(MEMBER_DUPLICATE_EX_MSG);
		}
	}
}
