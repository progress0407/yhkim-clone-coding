package hello.advanced.trace.logtrace;

import org.junit.jupiter.api.Test;

import hello.advanced.trace.TraceStatus;

class ThreadLocalLogTraceTest {

	ThreadLocalLogTrace trace = new ThreadLocalLogTrace();

	@Test
	public void begin_end_level2() {
		TraceStatus status1 = trace.begin("hello1");
		TraceStatus status2 = trace.begin("hello2");
		trace.end(status2);
		trace.end(status1);
	}

	@Test
	public void begin_exception_level2() {
		TraceStatus status1 = trace.begin("hello1");
		TraceStatus status2 = trace.begin("hello2");
		trace.exception(status2, new IllegalStateException());
		trace.exception(status1, new IllegalStateException());
	}

	@Test
	public void 성우가_작성한_동시성_문제_시뮬레이션() {
		final int size = 100;

		ThreadLocalLogTrace[] traces = new ThreadLocalLogTrace[size];
		TraceStatus[] statuses = new TraceStatus[size];

		for (int i = 0; i < size; i++) {
			traces[i] = new ThreadLocalLogTrace();
			statuses[i] = traces[i].begin("hello" + i);
		}

		for (int i = size - 1; i >= 0; i--) {
			traces[i].end(statuses[i]);
		}
	}
}