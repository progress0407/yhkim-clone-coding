package jpa.app.shop.domain.item;

import javax.persistence.Entity;

import jpa.app.shop.controller.BookForm;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @ToString
public class Book extends Item {

	private String author;
	private String isbn;

	public static Book createBook(BookForm form) {
		Book book = new Book();

		book.setName(form.getName());
		book.setPrice(form.getPrice());
		book.setStockQuantity(form.getStockQuantity());
		book.setAuthor(form.getAuthor());
		book.setIsbn(form.getIsbn());

		return book;
	}
}
