package hello.advanced.trace.myrefactoring;

import java.util.function.Consumer;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

/**
 * 람다로 리펙터링해본 공통 메서드 추출 로직
 */
@Slf4j
public class MyTemplateMethodTest {

	@Test
	public void templateMethodV0() {
		logic1((Void v) -> System.out.println("비즈니스 로직1 실행"));
		logic1((Void v) -> log.info("비즈니스 로직2 실행"));
	}

	private void logic1(Consumer<Void> consumer) {
		long startTime = System.currentTimeMillis();
		// 비즈니스 로직 실행
		consumer.accept(null);
		// 비즈니스 로직 종료
		long endTime = System.currentTimeMillis();
		long resultTime = endTime - startTime;
		log.info("resultTime={}", resultTime);
	}

	private void logic2(Consumer<Void> consumer) {
		long startTime = System.currentTimeMillis();
		// 비즈니스 로직 실행
		consumer.accept(null);
		// 비즈니스 로직 종료
		long endTime = System.currentTimeMillis();
		long resultTime = endTime - startTime;
		log.info("resultTime={}", resultTime);
	}
}
