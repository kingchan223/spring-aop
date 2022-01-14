package hello.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Slf4j
@Aspect
public class AspectV3 {
    //hello.aop.order 패키지와 하위 패키지
    @Pointcut("execution(* hello.aop.order..*(..))")
    private void allOrder(){} //pointcut signature

    /* 추가 */
    //클래스 이름 패턴이 *Service인 부분
    @Pointcut("execution(* *..*Service.*(..))")
    private void allService(){} //pointcut signature

    @Around("allOrder()")//Pointcut
    public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable{
        log.info("[log] {}", joinPoint.getSignature()); // joinPoint 시그니처 // Advice
        return joinPoint.proceed();//타겟호출
    }

    /* 추가 */
    //hello.aop.order 패키지와 하위 패키지이면서 클르스 이름 패턴이 *Service
    @Around("allOrder() && allService()")//Pointcut
    public Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable{
        try{
            log.info("[트랜잭션 시작] {}", joinPoint.getSignature()); // joinPoint 시그니처 // Advice
            Object result = joinPoint.proceed();//타겟호출
            log.info("[트랜잭션 커밋] {}", joinPoint.getSignature()); // joinPoint 시그니처 // Advice
            return result;
        }catch(Exception e){
            log.info("[트랜잭션 롤백] {}", joinPoint.getSignature());
            throw e;
        }finally{
            log.info("[리소스 릴리즈] {}", joinPoint.getSignature());
        }
    }
}
