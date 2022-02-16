package jpa.app.shop.domain;

import javax.persistence.Embeddable;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@AllArgsConstructor
@Getter @Setter
public class Address {

	private String city;
	private String street;
	private String zipcode;

	protected Address() {
	}
}
