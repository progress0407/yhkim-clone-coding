package jpa.app.shop.jpastudy;

import static org.assertj.core.api.Assertions.*;

import java.util.Objects;

import org.junit.jupiter.api.Test;

public class JunitEqualsTest {

	@Test
	public void assertThat은_동일성일까_동등성일까() {

		String a = "1";

		String b = new String("1");

		System.out.println("(a==b) = " + (a == b));
		// 이 테스트가 통과하면 동등성을 본 것
		assertThat(a).isEqualTo(b);
	}

	@Test
	public void assertThat은_동일성일까_동등성일까2() {

		Something a = new Something(1, "a");
		Something a2 = new Something(1, "a");

		System.out.println("(a.equals(a2)) = " + (a.equals(a2)));
		assertThat(a).isEqualTo(a2);
	}


	public static class Something {
		private int id;
		private String name;

		public Something(int id, String name) {
			this.id = id;
			this.name = name;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (o == null || getClass() != o.getClass())
				return false;
			Something something = (Something)o;
			return id == something.id && Objects.equals(name, something.name);
		}

		@Override
		public int hashCode() {
			return Objects.hash(id, name);
		}
	}
}
