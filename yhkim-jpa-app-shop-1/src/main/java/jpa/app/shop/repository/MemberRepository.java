package jpa.app.shop.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import jpa.app.shop.domain.Member;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

	private final EntityManager em;

	public Long save(Member member) {
		em.persist(member);
		return member.getId();
	}

	public Member findOne(Long id) {
		return em.find(Member.class, id);
	}

	public List<Member> findAll() {
		return em.createQuery("select m from Member m", Member.class)
			.getResultList();
	}

	public List<Member> findByName(String name) {
		return em.createQuery("select m from Member m where m.name = :username", Member.class)
			.setParameter("username", name)
			.getResultList();
	}

	public long memberExistCount(Member member) {
		return em.createQuery("select count(m.name) from Member m where m.name = :username", Long.class)
			.setParameter("username", member.getName())
			.getSingleResult();
	}

	public void clear() {
		em.createQuery("delete from Member m").executeUpdate();
	}
}
