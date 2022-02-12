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

	//==생성 메서드==//
	public static OrderItem createOrderItem(Item item, int orderPrice, int count) {
		OrderItem orderItem = new OrderItem();
		orderItem.setItem(item);
		// item의 price와 달리 쿠폰/할인 등으로 달라진 가격이 들어올 수 있다
		orderItem.setOrderPrice(orderPrice);
		orderItem.setCount(count);

		item.removeStock(count);

		return orderItem;
	}

	//== 비즈니스 로직 ==//
	public void cancel() {
		// 재고 수량을 원복한다
		getItem().addStock(count);
	}

	/**
	 * 주문 상품 전체 가격 조회
	 */
	public int getTotalPrice() {
		return getOrderPrice() * getCount();
	}
}
