package hello.proxy.pureproxy.decorator.code;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class MessageDecorator implements Component {

	private final Component component;

	@Override
	public String operation() {
		log.info("MessageDecorator 실행");

		// data -> ****data****
		String result = component.operation();
		String decoResult = "****" + result + "****";
		log.info("MessageDecorator 꾸미기 적용 전={}, 적용 후={}", result, decoResult);
		return decoResult;
	}
}
