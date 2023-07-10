package hello.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * 테스트용으로 만든 것
 */
@Aspect
@Slf4j
public class MyAspect {

    /**
     * 내부 프레임워크 소스는 aop 가 생성되지 않는 것 같다.
     * 왜냐하면 스프링 로딩 시점에 해당 파일들이 이미 로딩이 되어있을 것으로 생각된다.
     */
//    @Around("execution(* org.springframework.test.context.support.DependencyInjectionTestExecutionListener.*(..))")
    public Object doo(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("[log - before] {}", joinPoint.getSignature());
        Object proceed = joinPoint.proceed();
        log.info("[log - after] {}", joinPoint.getSignature());
        return proceed;
    }

    @Before("execution(* hello.aop.order..*(..))")
    public Object doo2() throws Throwable {
        log.info("[log - before] {}");
        return "a";
    }
}
