package hello.advanced.trace.myrefactoring;

import java.util.function.Consumer;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

/**
 *  템플릿 메서드로 리펙터링한 것
 */
@Slf4j
public class MyTemplateMethodTestV2 {

	@Test
	public void templateMethodV0() {
		logic1();
		logic2();
	}

	private void logic1() {
		ChildBusiness1 childBusiness1 = new ChildBusiness1();
		childBusiness1.commonLogic();
	}

	private void logic2() {
		ChildBusiness2 childBusiness2 = new ChildBusiness2();
		childBusiness2.commonLogic();
	}

	private static abstract class Parent {

		public void commonLogic() {
			long startTime = System.currentTimeMillis();
			// 비즈니스 로직 실행
			businessLogic();
			// 비즈니스 로직 종료
			long endTime = System.currentTimeMillis();
			long resultTime = endTime - startTime;
			log.info("resultTime={}", resultTime);
		}

		protected abstract void businessLogic();
	}

	private static class ChildBusiness1 extends Parent {

		@Override
		protected void businessLogic() {
			log.info("비즈니스 로직1 실행");
		}
	}

	private static class ChildBusiness2 extends Parent {

		@Override
		protected void businessLogic() {
			log.info("비즈니스 로직2 실행");
		}
	}
}
