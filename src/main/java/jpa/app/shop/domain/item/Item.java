package jpa.app.shop.domain.item;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import jpa.app.shop.domain.Category;
import jpa.app.shop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Setter
@DiscriminatorColumn
@Getter
@ToString
abstract public class Item {

	@Id
	@GeneratedValue
	@Column(name = "item_id")
	private Long id;

	private String name;

	private int stockQuantity;

	@ManyToMany(mappedBy = "items")
	@ToString.Exclude
	private List<Category> categories = new ArrayList<>();

	//== 비즈니스 로직 ==//
	public void addStock(int quantity) {
		this.stockQuantity += quantity;
	}

	public void removeStock(int quantity) {
		int restStock = this.stockQuantity - quantity;
		if (restStock < 0) {
			throw new NotEnoughStockException("need more stock");
		}

		this.stockQuantity = restStock;
	}


}
