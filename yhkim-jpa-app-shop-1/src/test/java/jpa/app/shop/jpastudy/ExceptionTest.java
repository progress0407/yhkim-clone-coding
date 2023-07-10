package jpa.app.shop.jpastudy;

import static java.lang.System.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ExceptionTest {

	@AfterEach
	void tearDown() {
		out.println("@AfterEach");
	}

	@Test
	@DisplayName("이 메서드는 fail을 할 것입니다")
	public void testMethod() {
		out.println("fail !");
		out.println(1/0);
		assertThat(1 / 0).isEqualTo(0);
	}
}
