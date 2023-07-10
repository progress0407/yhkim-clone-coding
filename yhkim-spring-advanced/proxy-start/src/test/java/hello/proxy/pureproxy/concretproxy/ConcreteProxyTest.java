package hello.proxy.pureproxy.concretproxy;

import org.junit.jupiter.api.Test;

import hello.proxy.pureproxy.concretproxy.code.ConcreteClient;
import hello.proxy.pureproxy.concretproxy.code.ConcreteLogic;
import hello.proxy.pureproxy.concretproxy.code.TimeProxy;

public class ConcreteProxyTest {
	
	@Test
	void noProxy() {
		ConcreteLogic concreteLogic = new ConcreteLogic();
		ConcreteClient client = new ConcreteClient(concreteLogic);
		client.execute();
	}

	@Test
	void addProxy() {
		ConcreteLogic concreteLogic = new ConcreteLogic();
		TimeProxy timeProxy = new TimeProxy(concreteLogic);
		ConcreteClient client = new ConcreteClient(timeProxy);
		client.execute();
	}
}
