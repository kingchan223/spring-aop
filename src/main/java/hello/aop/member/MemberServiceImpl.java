package hello.aop.member;


import hello.aop.member.annotation.ClassAop;
import hello.aop.member.annotation.MethodAop;
import org.springframework.stereotype.Component;

@ClassAop
@Component
public class MemberServiceImpl implements MemberService{

    @Override
    @MethodAop("test value")
    public String hello(String param) {
        System.out.println("MemberServiceImpl.hello");
        return "ok";
    }

    public String internal(String param){
        System.out.println("MemberServiceImpl.internal");
        return "ok";
    }
}
