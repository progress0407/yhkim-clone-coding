package hello.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
@Slf4j
public class AspectV6Advice {

    /**
     * ProceedingJoinPoint 는 @Around에서만 사용 가능
     */
//    @Around("hello.aop.order.aop.Pointcuts.orderAndService()")
    public Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            // @Before
            log.info("[트랜잭션 시작] {}", joinPoint.getSignature());
            Object result = joinPoint.proceed();
            // @AfterReturning
            log.info("[트랜잭션 커밋] {}", joinPoint.getSignature());
            return result;
        } catch (Exception e) {
            // @AfterThrowing
            log.info("[트랜잭션 롤백] {}", joinPoint.getSignature());
            throw e;
        } finally {
            // @After
            log.info("[리소스 릴리즈] {}", joinPoint.getSignature());
        }
    }


    /**
     * ProceedingJoinPoint 대신 JoinPoint 사용해야 함
     */
    @Before("hello.aop.order.aop.Pointcuts.orderAndService()")
    public void doBefore(JoinPoint joinPoint) {
        log.info("[before] {}", joinPoint.getSignature());
    }

    /**
     * JoinPoint 는 생략 가능
     */
//    @Before("hello.aop.order.aop.Pointcuts.orderAndService()")
    public void doBefore() {
        log.info("[before]");
    }

    /**
     * returning을 받아야 한다
     * 이때 반환값은 수정할 수 없다
     */
    @AfterReturning(value = "hello.aop.order.aop.Pointcuts.orderAndService()", returning = "result")
    public void doReturn(JoinPoint joinPoint, Object result) {
        log.info("[return] {}, return= {}", joinPoint.getSignature(), result);
    }

    @AfterReturning(value = "hello.aop.order.aop.Pointcuts.allOrder()", returning = "result")
    public void doReturn2(JoinPoint joinPoint, Integer result) {
        log.info("[return2] {}, return= {}", joinPoint.getSignature(), result);
    }

    @AfterThrowing(value = "hello.aop.order.aop.Pointcuts.orderAndService()", throwing = "ex")
    public void doThrowing(JoinPoint joinPoint, Exception ex) {
        log.info("[ex] {}, message= {}", joinPoint.getSignature(), ex.getMessage());
    }

    @After("hello.aop.order.aop.Pointcuts.orderAndService()")
    public void doAfter(JoinPoint joinPoint) {
        log.info("[after - toString] {}", joinPoint.toString());
        log.info("[after] {}", joinPoint.getSignature());
    }
}
