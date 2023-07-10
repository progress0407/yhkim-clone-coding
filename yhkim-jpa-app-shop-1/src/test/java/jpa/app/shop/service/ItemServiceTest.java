package jpa.app.shop.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import jpa.app.shop.domain.item.Movie;
import jpa.app.shop.repository.ItemRepository;

@SuppressWarnings("NonAsciiCharacters")
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ItemServiceTest {

	@Autowired ItemService itemService;
	@Autowired ItemRepository itemRepository;

	@Test
	@Rollback(value = false)
	public void 상품등록() {
		Movie movie = new Movie();
		movie.setName("너의 결혼식");
		movie.setActor("박보영");
		movie.setStockQuantity(3);

		Long savedId = itemService.save(movie);
		assertEquals(movie, itemService.findOne(savedId));
	}
}