package jpa.app.shop.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @ToString
public class Member {

	@Id
	@GeneratedValue
	@Column(name = "member_id")
	private Long id;
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
}