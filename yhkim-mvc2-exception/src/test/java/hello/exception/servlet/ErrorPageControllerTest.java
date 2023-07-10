package hello.exception.servlet;

import hello.exception.filter.LogFilter;
import org.junit.jupiter.api.Test;

class ErrorPageControllerTest {
    
    @Test
    public void 빈_테스트() throws Exception {
        LogFilter filter = new LogFilter();
        System.out.println("filter.getClass() = " + filter.getClass());
    }

}