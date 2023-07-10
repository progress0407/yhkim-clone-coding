package jpa.app.shop.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import jpa.app.shop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Category {

	@Id
	@GeneratedValue
	@Column(name = "category_id")
	private Long id;

	private String name;

	@JoinColumn(name = "parent_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Category parent;


	@OneToMany(mappedBy = "parent", cascade = CascadeType.PERSIST)
	private List<Category> children = new ArrayList<>();

	@ManyToMany
	@JoinTable(
		name = "category_item" ,
		joinColumns = @JoinColumn(name = "category_id") ,
		inverseJoinColumns = @JoinColumn(name = "item_id")
	)
	private List<Item> items = new ArrayList<>();

	public void addCategories(Category... categories) {
		this.children.addAll(List.of(categories));
		Arrays.stream(categories).forEach((Category category) -> category.setParent(this));
	}

}
