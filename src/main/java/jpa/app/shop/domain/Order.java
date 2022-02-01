package jpa.app.shop.domain;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "orders")
@Getter
@Setter
@ToString
public class Order {

	@Id
	@GeneratedValue
	@Column(name = "order_id")
	private Long id;

	@JoinColumn(name = "member_id")
	@ManyToOne(fetch = FetchType.EAGER)
	@ToString.Exclude
	private Member member;

	@JoinColumn(name = "delivery_id")
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@ToString.Exclude
	private Delivery delivery;

	private LocalDateTime orderDate;

	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;

}
