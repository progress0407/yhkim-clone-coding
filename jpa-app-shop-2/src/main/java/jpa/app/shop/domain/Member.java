package jpa.app.shop.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

@Entity
@Getter @Setter @ToString
public class Member {

	@Id
	@GeneratedValue
	@Column(name = "member_id")
	private Long id;

	@NotEmpty
	private String name;

	@Embedded
	private Address address;

	@OneToMany(mappedBy = "member", cascade = CascadeType.PERSIST)
	@ToString.Exclude
	private List<Order> orders = new ArrayList<>();

	public void addOrders(Order... orders) {
		this.orders.addAll(List.of(orders));
		Arrays.stream(orders).forEach((Order order) -> order.setMember(this));
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;

		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o))
			return false;
		Member member = (Member)o;
		return Objects.equals(getId(), member.getId()) && Objects.equals(getName(), member.getName());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), getName());
	}
}
