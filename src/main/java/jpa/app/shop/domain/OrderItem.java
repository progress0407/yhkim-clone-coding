package jpa.app.shop.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import jpa.app.shop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class OrderItem {

	@Id
	@GeneratedValue
	@Column(name = "order_item_id")
	private Long id;

	@JoinColumn(name = "order_id")
	@ManyToOne(fetch = FetchType.LAZY)
	@ToString.Exclude
	private Order order;

	@JoinColumn(name = "item_id")
	@ManyToOne(fetch = FetchType.LAZY)
	@ToString.Exclude
	private Item item;

	private int orderPrice;

	private int count;

}
