package hello.exception.resolver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class MyHandlerExceptionResolver implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        log.info("call resolver ", ex); // 에러는 {} 를 쓰지 않는다

        try {
            if (ex instanceof IllegalArgumentException) {
                log.info("IllegalArgumentException resolver to 400");
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());
                // 새로운 예외 정보를 등록
                // WAS 에서 재해석한다
                return new ModelAndView(); // null 이 아닌 경우 기존 예외를 먹는다
            }
        } catch (IOException e) {
            log.info("resolver ex", e);
            e.printStackTrace();
        }

        return null; // 기존 발생한 예외 그대로 서블릿 밖으로 던짐
    }
}
