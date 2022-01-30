package jpa.app.shop.domain;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @ToString
public class Delivery {

	@Id
	@GeneratedValue
	@Column(name = "delivery_id")
	private Long id;

	@JoinColumn(name = "order_id")
	@OneToOne(fetch = FetchType.LAZY)
	@ToString.Exclude
	private Order order;

	@Embedded
	private Address address;

	private DeliveryStatus deliveryStatus;
}
