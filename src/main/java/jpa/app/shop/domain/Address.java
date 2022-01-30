package jpa.app.shop.domain;

import javax.persistence.Embeddable;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@AllArgsConstructor
public class Address {

	private String city;
	private String street;
	private String zipCode;

	protected Address() {
	}
}
