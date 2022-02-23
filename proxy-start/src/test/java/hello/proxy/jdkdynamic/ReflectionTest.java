package hello.proxy.jdkdynamic;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReflectionTest {

	@Test
	void reflection0() {
		Hello target = new Hello();

		// 공통 로직1 시작
		log.info("start");
		String result1 = target.callA(); // 호출하는 메서드가 다름
		log.info("result={}", result1);
		// 공통 로직1 종료

		// 공통 로직1 시작
		log.info("start");
		String result2 = target.callB(); // 호출하는 메서드가 다름
		log.info("result={}", result2);
		// 공통 로직1 종료
	}

	@Test
	void reflection() throws Exception {
		// 클래스의 메타정보 획득
		Class classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");

		Hello target = new Hello();

		// callA 메서드 정보 획득
		Method methodCallA = classHello.getMethod("callA");
		Object result1 = methodCallA.invoke(target);
		log.info("result1={}", result1);

		// callB 메서드 정보 획득
		Method methodCallB = classHello.getMethod("callB");
		Object result2 = methodCallB.invoke(target);
		log.info("result2={}", result2);
	}

	@Test
	void reflection2() throws Exception {
		// 클래스의 메타정보 획득
		Class classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");

		Hello target = new Hello();

		// callA 메서드 정보 획득
		Method methodCallA = classHello.getMethod("callA");
		dynamicCall(methodCallA, target);

		// callB 메서드 정보 획득
		Method methodCallB = classHello.getMethod("callB");
		dynamicCall(methodCallB, target);
	}

	private void dynamicCall(Method method, Object target) throws Exception {
		log.info("start");
		Object result = method.invoke(target);
		log.info("result={}", result);
	}

	/**
	 * 내가 작성한 코드들
	 */
	@Test
	void reflectionTest_self_made() throws Exception {
		Class<?> classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");

		Hello target = new Hello();

		List<String> methodStrings = new ArrayList<>(List.of("callA", "callB"));

		for (String methodString : methodStrings) {
			Method method = classHello.getMethod(methodString);
			Object result = method.invoke(target);
			log.info("result={}", result);
		}
	}

	@Test
	void lambdaCall() {
		// Hello hello = new Hello();
		// lambda(hello::callA);
		// lambda(hello::callB);

		lambda2(Hello::callA);
		lambda2(Hello::callB);
	}

	void lambda(Supplier<String> supplier) {
		// 공통 로직1 시작
		log.info("start");
		String result = supplier.get(); // 호출하는 메서드가 다름
		log.info("result={}", result);
		// 공통 로직1 종료
	}

	void lambda2(Function<Hello, String> function) {
		// 공통 로직1 시작
		log.info("start");
		String result = function.apply(new Hello()); // 호출하는 메서드가 다름
		log.info("result={}", result);
		// 공통 로직1 종료
	}



	@Slf4j
	static class Hello {

		public Hello() {
			log.info(this + ": create !");
		}

		public String callA() {
			log.info("callA");
			return "A";
		}

		public String callB() {
			log.info("callB");
			return "B";
		}
	}
}
