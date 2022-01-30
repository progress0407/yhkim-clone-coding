package jpa.app.shop.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import jpa.app.shop.domain.Member;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

	@PersistenceContext
	private final EntityManager em;

	public Long save(Member member) {
		em.persist(member);
		return member.getId();
	}

	public Member find(Long id) {
		return em.find(Member.class, id);
	}
}
