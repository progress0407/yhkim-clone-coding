package jpa.app.shop.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "ORDERS")
@Getter
@Setter
@ToString
public class Order {

	@Id
	@GeneratedValue
	private Long id;

	@JoinColumn(name = "MEMBER_ID")
	@ManyToOne(fetch = FetchType.LAZY)
	@ToString.Exclude
	private Member member;

	private LocalDateTime orderDate;

	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;
}
