package jpa.app.shop.domain.item;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @ToString
public class Album extends Item {

	private String artist;
	private String etc;
}
