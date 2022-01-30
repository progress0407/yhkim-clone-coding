package jpa.app.shop.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @ToString
public class Member {

	@Id
	@GeneratedValue
	private Long id;
	private String name;

	@Embedded
	private Address address;

	@OneToMany(mappedBy = "member")
	@ToString.Exclude
	private List<Order> order = new ArrayList<>();

}