package jpa.app.shop.domain.item;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @ToString
public class Book extends Item {

	private String author;
	private String isbn;
}
